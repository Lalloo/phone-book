import domain.Person;
import util.HashTable;
import util.SearchUtils;
import util.SortUtils;
import util.Timer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class PhoneBookApp {
    private final String DIRECTORY_PATH = "C:\\Users\\beeav\\IdeaProjects\\project\\phone-book\\src\\main\\java\\data\\directory.txt";
    private final String FIND_PATH = "C:\\Users\\beeav\\IdeaProjects\\project\\phone-book\\src\\main\\java\\data\\find.txt";

    public void run() throws FileNotFoundException {
        List<Person> directory = fromFile(DIRECTORY_PATH);
        List<Person> find = fromFile(FIND_PATH);
        Timer timer = new Timer();

        System.out.println("Start searching (linear search)...");
        timer.start();
        int found = SearchUtils.linearSearch(directory, find);
        timer.stop();
        long linearSearchTime = timer.getTimeLapse();
        timer.printInfo(String.format("Found %d/%d entries.", found, find.size()), linearSearchTime);

        System.out.println("\nStart searching (bubble sort + jump search)...");
        timer.start();
        boolean isSorted = SortUtils.bubbleSort(directory, timer, linearSearchTime);
        timer.stop();
        long bubbleSortTime = timer.getTimeLapse();
        timer.start();
        if (isSorted) {
            int count = SearchUtils.jumpSearch(directory, find);
            timer.stop();
            long jumpSearchTime = timer.getTimeLapse();
            timer.printInfo(String.format("Found %d/%d entries.", count, find.size()), bubbleSortTime + jumpSearchTime);
            System.out.println("Sorting time: " + timer.getTimeLapseAsString(bubbleSortTime));
            System.out.println("Searching time: " + timer.getTimeLapseAsString(jumpSearchTime));
        } else {
            found = SearchUtils.linearSearch(directory, find);
            timer.stop();
            linearSearchTime = timer.getTimeLapse();
            timer.printInfo(String.format("Found %d/%d entries.", found, find.size()), linearSearchTime + bubbleSortTime);
            System.out.println("Sorting time: " + timer.getTimeLapseAsString(bubbleSortTime) + " - STOPPED, moved to linear search");
            System.out.println("Searching time: " + timer.getTimeLapseAsString(linearSearchTime));
        }

        System.out.println("\nStart searching (quick sort + binary search)...");
        directory = fromFile(DIRECTORY_PATH);
        timer.start();
        SortUtils.quickSort(directory, 0, directory.size() - 1);
        timer.stop();
        long quickSortTime = timer.getTimeLapse();
        timer.start();
        found = SearchUtils.binarySearch(directory, find);
        timer.stop();
        long binarySearchTime = timer.getTimeLapse();
        timer.printInfo(String.format("Found %d/%d entries.", found, find.size()), quickSortTime + binarySearchTime);
        System.out.println("Sorting time:" + timer.getTimeLapseAsString(quickSortTime));
        System.out.println("Searching time: " + timer.getTimeLapseAsString(binarySearchTime));

        System.out.println("\nStart searching (hash table)...");
        timer.start();
        Hashtable<String, Integer> hashtable = HashTable.createHashTable(directory);
        timer.stop();
        long creatingHashTable = timer.getTimeLapse();
        timer.start();
        found = SearchUtils.searchInHashTable(hashtable, find);
        timer.stop();
        long searchTimeInHashTable = timer.getTimeLapse();
        timer.printInfo(String.format("Found %d/%d entries.", found, find.size()), creatingHashTable + searchTimeInHashTable);
        System.out.println("Creating time: " + timer.getTimeLapseAsString(creatingHashTable));
        System.out.println("Searching time: +" + timer.getTimeLapseAsString(searchTimeInHashTable));
    }

    private List<Person> fromFile(String path) throws FileNotFoundException {
        List<Person> people = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                people.add(new Person(scanner.nextLine()));
            }
        }
        return people;
    }
}