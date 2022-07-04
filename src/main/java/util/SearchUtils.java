package util;

import domain.Person;

import java.util.List;

public final class SearchUtils {
    private SearchUtils() {}

    public static int linearSearch(List<Person> people, List<Person> toFind) {
        int count = 0;
        for (Person find : toFind) {
            for (Person person : people) {
                if (person.getName().equals(find.getName())) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    private static int jumpSearch(List<Person> people, Person value) {
        int n = people.size() - 1;
        int step = (int) Math.floor(Math.sqrt(n));
        int index = 0;
        String nameToFind = value.getName();
        while (index < n) {
            String name = people.get(index).getName();
            if (name.equals(nameToFind)) {
                return index;
            } else if (name.compareTo(nameToFind) > 0) {
                int index2 = index - 1;
                while ((index2 > index - step) && index2 >= 0) {
                    if (people.get(index2).getName().equals(nameToFind)) {
                        return index2;
                    }
                    index2--;
                }
                return -1;
            }
            index += step;
        }
        int index2 = n;
        while (index2 > index - step) {
            if (people.get(index2).getName().equals(nameToFind)) {
                return index2;
            }
            index2--;
        }
        return -1;
    }

    public static int jumpSearch(List<Person> people, List<Person> toFind) {
        int count = 0;
        for (Person person : toFind) {
            if (jumpSearch(people, person) != -1) {
                count++;
            }
        }
        return count;
    }

    private static boolean binarySearch(List<Person> people, Person value) {
        int begin = 0;
        int end = people.size();
        while (begin <= end) {
            int mid = begin + ((end - begin) / 2);
            if (people.get(mid).getName().compareTo(value.getName()) < 0) {
                begin = mid + 1;
            } else if (people.get(mid).getName().compareTo(value.getName()) > 0) {
                end = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }


    public static int binarySearch(List<Person> people, List<Person> toFind) {
        int count = 0;
        for (Person person : toFind) {
            if (binarySearch(people, person)) {
                count++;
            }
        }
        return count;
    }
}
