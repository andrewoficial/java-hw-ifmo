package hw015.task01;

import java.util.*;

public class UserStorage {
    // ключи - логины пользователей (String)
    // значения - пользователи (User)
    private HashMap<String, User> users;

    public UserStorage() {
        users = new HashMap<>();
    }

    public void addUser(User user){ // добавление пользователя в map
        // user не может быть null
        // import java.util.Objects
        Objects.requireNonNull(user, "Exception: user == null");
        // если пользователь с логином, как у user уже содержится в map users,
        // то пользователь не добавляется (значение не перезаписывать)
        if (!users.containsKey(user.getLogin())) {
            users.put(user.getLogin(), user);
        }
    }

    public List<User> getUsersByAge(int maxAge) {
        // метод формирует и возвращает список пользователей,
        // возраст которых меньше maxAge
        ArrayList<User> userArrayList = new ArrayList<>();
        for (User user : users.values()) {
            if(user.getAge() < maxAge){
                userArrayList.add(user);
            }
        }
        return userArrayList;
    }

    public Map<Integer, List<User>> getUsersByAge(){
        // метод формирует и возвращает map, где
        // ключи - возраст пользователей,
        // значения - списки пользователей соответствующего возраста
        HashMap<Integer, List<User>> map = new HashMap<>();
        // map.keySet() - Set ключей
        for (User user : users.values()) {
            int ageKey = user.getAge();
            if (map.containsKey(ageKey)) {
                map.get(ageKey).add(user);
            } else {
                ArrayList<User> userArrayList = new ArrayList<>();
                userArrayList.add(user);
                map.put(ageKey, userArrayList);
            }
        }
        return map;
    }

    public EnumMap<User.Role, Integer> getNumberOfUsersByRole(){
        // метод формирует и возвращает map, где
        // ключи - роли пользователей,
        // значения - количество с соответствующей ролью
        EnumMap<User.Role, Integer> enumMap = new EnumMap<>(User.Role.class);
        for (User user : users.values()) {
            User.Role roleKey = user.getRole();
            enumMap.put(roleKey, enumMap.getOrDefault(roleKey, 0) + 1);
        }
        return enumMap;
    }

}
