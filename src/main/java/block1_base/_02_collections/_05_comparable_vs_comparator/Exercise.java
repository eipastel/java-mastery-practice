package block1_base._02_collections._05_comparable_vs_comparator;

import java.util.*;

public class Exercise {

    // -------------------------------------------------------
    // Product class: implements Comparable for natural order (price)
    // -------------------------------------------------------
    static class Product implements Comparable<Product> {
        private String name;
        private double price;
        private int stock;

        Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public String getName()  { return name; }
        public double getPrice() { return price; }
        public int    getStock() { return stock; }

        // Natural order: by price ascending
        @Override
        public int compareTo(Product other) {
            return Double.compare(this.price, other.price);
        }

        @Override
        public String toString() {
            return String.format("Product{name='%s', price=%.2f, stock=%d}", name, price, stock);
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Comparable vs Comparator ===");
        System.out.println();

        List<Product> products = new ArrayList<>();
        products.add(new Product("Keyboard",  250.0, 15));
        products.add(new Product("Mouse",      80.0, 50));
        products.add(new Product("Monitor",   900.0,  5));
        products.add(new Product("Headset",   150.0, 20));
        products.add(new Product("Webcam",    180.0, 10));

        // -------------------------------------------------------
        // Comparable: natural order (price ascending)
        // -------------------------------------------------------
        System.out.println("--- Comparable: natural order (price ascending) ---");

        List<Product> byPrice = new ArrayList<>(products);
        Collections.sort(byPrice); // uses compareTo() from the Product class

        for (Product p : byPrice) {
            System.out.println("  " + p);
        }
        System.out.println();

        // -------------------------------------------------------
        // Comparator: order by name (alphabetical)
        // -------------------------------------------------------
        System.out.println("--- Comparator: by name (alphabetical) ---");

        Comparator<Product> byName = Comparator.comparing(Product::getName);

        List<Product> listByName = new ArrayList<>(products);
        listByName.sort(byName);

        for (Product p : listByName) {
            System.out.println("  " + p);
        }
        System.out.println();

        // -------------------------------------------------------
        // Comparator: order by price descending
        // -------------------------------------------------------
        System.out.println("--- Comparator: by price descending ---");

        Comparator<Product> byPriceDesc = Comparator.comparingDouble(Product::getPrice).reversed();

        List<Product> listPriceDesc = new ArrayList<>(products);
        listPriceDesc.sort(byPriceDesc);

        for (Product p : listPriceDesc) {
            System.out.println("  " + p);
        }
        System.out.println();

        // -------------------------------------------------------
        // Comparator: chaining — name desc, then price asc
        // -------------------------------------------------------
        System.out.println("--- Comparator: chained (name desc, then price asc) ---");

        Comparator<Product> chained = Comparator
                .comparing(Product::getName).reversed()
                .thenComparingDouble(Product::getPrice);

        List<Product> listChained = new ArrayList<>(products);
        listChained.sort(chained);

        for (Product p : listChained) {
            System.out.println("  " + p);
        }
        System.out.println();

        // -------------------------------------------------------
        // Comparator: by stock ascending using lambda
        // -------------------------------------------------------
        System.out.println("--- Comparator with lambda: by stock ascending ---");

        products.sort((a, b) -> Integer.compare(a.getStock(), b.getStock()));

        for (Product p : products) {
            System.out.println("  " + p);
        }
        System.out.println();

        // -------------------------------------------------------
        // TreeSet with external Comparator
        // -------------------------------------------------------
        System.out.println("--- TreeSet with Comparator by name ---");

        TreeSet<Product> treeSet = new TreeSet<>(Comparator.comparing(Product::getName));
        treeSet.add(new Product("Keyboard", 250.0, 15));
        treeSet.add(new Product("Mouse",     80.0, 50));
        treeSet.add(new Product("Monitor",  900.0,  5));

        for (Product p : treeSet) {
            System.out.println("  " + p);
        }
        System.out.println();

        System.out.println("--- Comparator.naturalOrder() and reverseOrder() ---");
        List<Integer> nums = Arrays.asList(5, 2, 8, 1, 9, 3);
        nums.sort(Comparator.naturalOrder());
        System.out.println("naturalOrder:  " + nums);
        nums.sort(Comparator.reverseOrder());
        System.out.println("reverseOrder:  " + nums);
    }
}
