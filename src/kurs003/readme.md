Программа состоит из двух компонентов клиент и сервер (ThreadsClientApp, NIOServerApp)
Клиентов можно запустить несколько.
При запуске клиент формирует токен и запрашивает имя (в текстовом формате)
После получения данных он подключается к серверу и выполняет штатную команду /register  где передает данные о себе
После регистрации на сервере клиент в прослушивает порт на предмет входящих сообещние и ожидает от пользователя ввода текста
Получается своебразный групповой чат (где есть все клиенты сервера)

Пользователю доступны команды:
register, sendFile, getFileList, getSomeFile, delFile, reRegister, getName, help, exit
регистрация
отправка файла
получение списка файлов на сервере
запрос конкретного файла
удаление конкретного файла (не реализовано)
смена своего никнейма
получение имени (не реализовано)
получение списка команд
завершение работы клиента

Когда к серверу подклчено несколько клиентов логика работы такая:
    - если получено просто сообщение - то оно рассылается всем участникам
    - если получена команда то ответ на неё отправляется только пользователю

Логика хранения файлов:
    Есть мапа в которой ключи - названия, элементы объекты типа TextFile (такой тип данных хранит имя, описание, содержание, имя того кто загрузил)
    Эта мапа десериализуется в файл. Есть ощущение что объект FileHandler и следовательно объект TsextFile должны быть сингтон. Но это не точно (с)
    
Логика обработки сервером:
    Почти полностью повторяет ту что была на уроке. Есть коллекция соединений, где каждое соединение это отдельный Thread
    При получении посылки идет её десерализация
        Посылка помещается в контейнер PackageParser (класс Package хотел использовать как общий тип данных сли делать execute но передумал, т.е. тут только жва типа сообшений)
        Дальше проверяется что за ип посылки. Файл или сообщение
        Если сообщение то в нем ищется команда (вот тут бы больше подошел фабричный метод и в зависимости от команды делать executable объект, но не ватает времени)
        Если пришел файл, то его обработка и сохранение
    После завершении обработки если соединение не разовалось (от исключение или еще чего) колесо сансары делает оборот

Логика обработки клиентом:
    Клиент задумывалось делать тонким, поэтому он принимает только Message
    Аналогично серверу есть вечный цил для двух Thread один инпут второй общение с сервером

Краткое описание файлов и их назначение:
    TextFile - тип данных загружаемых на сервер. Содержит имя, описание, сам текст, информацию кто выгрузил
    ReadWrite - класс содержащий данные сетевого соединения. (На сколько я помню сейчас все методы получения переделаны и это 
        используется только в файле ClientApp)
    ParsePackage - класс, который содержит в себе объект, получившийся в результате десериализации пакета из сети. Содержит методы для проверки
        того что пришло из сети (изначально задумывался как набор утилит поэтому такое имя)
    Package - абстрактный класс, кторый планировалось сделать общим типом данных. Была идея сдедать Message и TextFile executable, но не успеваю
    Message - класс, описывающий тип данных сообщение. Содержит строку и отправителя. (Еще дату отправки)
    Connection - моё изобретение класс-обертка (вроде так называются они) содержит в себе соединение, токен клиенты с которым оно установлено
        и его имя (которое можно менять, которое человекочитаемое)
    Command - перечисление команд. Была идея для каждой команды добавить пояснение что она делает что бы по help выводить красоту, и для каждой
        команды сделать свой execute (что имеет гораздо больше смысла чем для типа сообщений)
    NOServerApp - откровенно перегруенный класс. Он в себе несет 
        - Работу с коллекцией объектов Connection
        - Работу по сериализации приходящего из сети
        - Работу по поиску команды в сроке сообщения
        - Выполение пришедшей команды
        Пояснение: выполенеие команд не вынес в отдельные методы потому что всем им казалось некрасиво передавать контекст через параметры
    ThreadClientApp - более удачная штука. Команд никакиъ не парсит. Просто отпарвляет и получает. 
    FileHandler - отвечает за сохранение и чтение файлов. Не уверен что было правильно но так же может выдать объейкт файла как строку в сообщении
        Список файлов на сервере тоже как готовое сообщение (в конце концов весь курсач про эти сообшение и мне кажется тут нету нарушения принциа
        единой ответственности) 
    ClientApp - уже и не помню почему его не удалю, вначале использовал для отладки.
    
    





Известные проблемы:
Скорее всего надо было реализовывать паттерн Commander (что бы как с игрой было красивое меню, но тут слишком мало пунктов)
Очень неудобная работа с командами сервера
Работа с файлом скорее всего не потокобезопасна. 