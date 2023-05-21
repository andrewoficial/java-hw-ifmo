package stepik.рhofmannCoding;
import java.util.*;



public class Application{
    class Node implements Comparable<Node>{//..................................Общий класс узел дерева
        final int sum;//.......................................................Суммарная частота символов в этом поддереве
        String code;//.........................................................Текущий код символа
        public Node (int sum){
            this.sum = sum;
        }
        void buildCode(String code){
            this.code = code;
        }
        @Override//............................................................Метод будет переопределен
        public int compareTo(Node other) {
            return Integer.compare(sum, other.sum);//..........................Сравнение по частотности (весу)
        }
    }

    class InternalNode extends Node{//.........................................Внутренний узел (есть два ребенка)
        Node left;
        Node right;
        public InternalNode (Node left, Node right){
            super(left.sum + right.sum);//.....................................Значение частоты детей
            this.left = left;//................................................Ссылка на ребенка слева
            this.right = right;//..............................................Ссылка на ребенка справа
        }

        @Override
        void buildCode(String code) {
            super.buildCode(code);
            left.buildCode(code + "0");
            right.buildCode(code + "1");
        }
    }



    class LeafNode extends Node{//.............................................Класс лист
        char symbol;
        public LeafNode (char symbol, int count){//............................Конструктор листа
            super(count);//....................................................sum = count;
            this.symbol = symbol;//............................................Какой именно символ обладает такой частотой
        }

        @Override
        void buildCode(String code) {//........................................Переопределен метод buildCode
            super.buildCode(code);//...........................................Вызов метода родителя
            System.out.println(symbol + ": " + code);//........................Вывод на печать (для листа, содержащего char символ и string код (строка двоичного кода)
        }
    }

    private void run(){
        Scanner scanner = new Scanner(System.in);//.............................Создание объекта сканнер
        String inputString = scanner.next();//..................................Считывание данных от пользователя
        Map<Character, Integer> frequencyMap = new HashMap<>();//...............Создание структуры данных HashMap
        for (char c : inputString.toCharArray()) {//............................Строку в массив.
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);//.........Создание частотного ряда (в Map буква и частота вхождения в строке)
        }

        Map<Character, Node> charNode = new HashMap<>();//......................Создание ноды для хранения кодов (для строки)
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();//............Создание очереди с приоритетами
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {//.Перебор frequencyMap
            LeafNode lfnode = new LeafNode(entry.getKey(), entry.getValue());//.Создания листа с Символом и частотой
            charNode.put(entry.getKey(), lfnode);//.............................Добавление символа и соответствующей ему ноды в charNode
            priorityQueue.add(lfnode);//........................................Добавление листа в приоритетную очередь
        }
        int totlaSummOfSymb = 0;
        while (priorityQueue.size() > 1) {//....................................Пока очередь с приоритетами не пута берем по два узла и склеиваем в один
            Node left = priorityQueue.poll();//.................................Первый узел (будущий ребенок слева)
            Node right = priorityQueue.poll();//................................Второй узел (будущий ребенок справа)
            Node parent = new InternalNode(left, right);//......................Создаем родителя (с суммой частот)
            totlaSummOfSymb += parent.sum;//....................................Увеличение общей суммы частот символов
            priorityQueue.add(parent);//........................................Добавление родителя в приоритетную очередь
        }


        if(frequencyMap.size() == 1){//.........................................Если в дереве только один элемент
            totlaSummOfSymb = inputString.length();//...........................Используем длину входной строки в качестве общей суммы частот символов
        }
        System.out.println(frequencyMap.size() + " " + totlaSummOfSymb);//......Вывод размера мапы и общей частотности

        Node root = priorityQueue.poll();//.....................................Извлечение корневого узла из приоритетной очереди
        if(frequencyMap.size() == 1){//.........................................Если в строке был 1 символ n раз
            root.buildCode("0");//..............................................Назначем ему код "0"
        }else{
            root.buildCode("");//...............................................Запуск рекурсивного построение кода
        }

        String encode = "";//...................................................Будущая строка с кодом
        for (char c : inputString.toCharArray()) {//............................Строку для кодирования в массив
            encode += charNode.get(c).code;//...................................Генерация строки
        }
        System.out.println(encode);//...........................................Вывод строки
    }
    public static void main(String[] args) {
        new Application().run();
    }
}
