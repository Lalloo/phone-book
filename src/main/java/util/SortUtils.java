package util;

import domain.Person;

import java.util.List;

public final class SortUtils {
    private SortUtils() {
    }

    public static boolean bubbleSort(List<Person> people, Timer timer, long linearSortTime) {
        linearSortTime *= 10;
        for (int i = 0; i < people.size(); i++) {
            for (int j = 0; j < people.size() - 1; j++) {
                Person person1 = people.get(j);
                Person person2 = people.get(j + 1);
                if (person1.getName().compareTo(person2.getName()) > 0) {
                    people.set(j, person2);
                    people.set(j + 1, person1);
                }
                timer.stop();
                if (timer.getTimeLapse() > linearSortTime) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void quickSort(List<Person> people, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(people, begin, end);

            quickSort(people, begin, partitionIndex - 1);
            quickSort(people, partitionIndex + 1, end);
        }
    }

    private static int partition(List<Person> people, int begin, int end) {
        Person pivot = people.get(end);
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (pivot.getName().compareTo(people.get(j).getName()) > 0) {
                i++;
                Person swapTemp = people.get(i);
                people.set(i, people.get(j));
                people.set(j, swapTemp);
            }
        }
        Person swapTemp = people.get(i + 1);
        people.set(i + 1, people.get(end));
        people.set(end, swapTemp);
        return i + 1;
    }
}