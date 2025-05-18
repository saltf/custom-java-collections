package set;

import java.util.Iterator;

public class CustomHashSetTest {
    public static void main(String[] args) {
        CustomHashSet<String> set = new CustomHashSet<>();
        set.add("Java");
        set.add("Python");
        set.add("C++");
        set.add("Java"); // duplicate — ignored

        System.out.println("Set: " + set); // [Java, Python, C++]
        System.out.println("Size: " + set.size()); // 3
        System.out.println("Contains Python? " + set.contains("Python")); // true

        set.remove("Python");
        System.out.println("After remove: " + set); // [Java, C++]
        System.out.println("Is empty? " + set.isEmpty()); // false
        System.out.println("\ntoString() " + set.toString());

        System.out.println("\nIterating with Iterator:");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Visited: " + element);
        }

        System.out.println("\nIterating with for-each loop:");
        for (String lang : set) {
            System.out.println("Visited: " + lang);
        }

        set.clear();
        System.out.println("\nAfter clear: " + set); // []
        System.out.println("Size: " + set.size()); // 0

    }
}
