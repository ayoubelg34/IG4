package lab2;

import java.util.HashMap;
import java.util.Map;

class HashM {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        map.put("Fred","Wilma");
        map.put("Barney","Betty");
        map.put("Tristan","Isolde");
        map.put("Romeo","Juliette");

    //Transposition de la hashmap
    HashMap<String, String> newMap = new HashMap<>();
    for (Map.Entry<String, String> entry : map.entrySet()) {
        newMap.put(entry.getValue(), entry.getKey());
    }
    System.out.println(newMap);
    }

}