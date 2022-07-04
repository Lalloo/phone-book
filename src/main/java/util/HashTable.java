package util;
import domain.Person;

import java.util.Hashtable;
import java.util.List;

public final class HashTable {
    private HashTable() {}
    public static Hashtable<String, Integer> createHashTable(List<Person> people) {
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        for(int i = 0, j = 0; i < people.size(); i++, j++) {
            hashtable.put(people.get(i).getName(), j);
        }
        return hashtable;
    }
}
