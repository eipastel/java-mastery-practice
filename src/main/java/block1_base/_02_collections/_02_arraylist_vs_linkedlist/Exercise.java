package block1_base._02_collections._02_arraylist_vs_linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Exercise {

    public static void main(String[] args) {

        System.out.println("=== ArrayList vs LinkedList ===");
        System.out.println();

        // -------------------------------------------------------
        // Creation and basic insertion
        // -------------------------------------------------------
        System.out.println("--- Inserting elements ---");

        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            arrayList.add("item" + i);
            linkedList.add("item" + i);
        }
        System.out.println("ArrayList  : " + arrayList);
        System.out.println("LinkedList : " + linkedList);
        System.out.println();

        // -------------------------------------------------------
        // Access by index: O(1) in ArrayList, O(n) in LinkedList
        // -------------------------------------------------------
        System.out.println("--- Access by index ---");

        String al = arrayList.get(3);   // O(1): direct array access
        String ll = linkedList.get(3);  // O(n): traverses nodes until index 3

        System.out.println("arrayList.get(3)  = " + al + "  [O(1) - direct access]");
        System.out.println("linkedList.get(3) = " + ll + "  [O(n) - traverses nodes]");
        System.out.println();

        // -------------------------------------------------------
        // Insertion in the MIDDLE: both O(n), but for different reasons
        // -------------------------------------------------------
        System.out.println("--- Insertion in the middle ---");

        arrayList.add(2, "NEW_AL");   // O(n): shifts elements
        linkedList.add(2, "NEW_LL"); // O(n): traverses to position 2, then adjusts pointers

        System.out.println("ArrayList (insert at index 2)  : " + arrayList);
        System.out.println("LinkedList (insert at index 2) : " + linkedList);
        System.out.println();

        // -------------------------------------------------------
        // Insertion/removal at the ENDS: LinkedList has a clear advantage
        // -------------------------------------------------------
        System.out.println("--- Insertion/removal at the ends (LinkedList as Deque) ---");

        LinkedList<String> deque = new LinkedList<>();
        deque.addFirst("first"); // O(1)
        deque.addLast("last");   // O(1)
        deque.addFirst("new_first"); // O(1)

        System.out.println("After addFirst/addLast: " + deque);

        String removedFromFront = deque.removeFirst(); // O(1)
        String removedFromBack  = deque.removeLast();  // O(1)

        System.out.println("removeFirst() -> " + removedFromFront);
        System.out.println("removeLast()  -> " + removedFromBack);
        System.out.println("Remaining: " + deque);
        System.out.println();

        // -------------------------------------------------------
        // Performance measurement: access by index
        // -------------------------------------------------------
        System.out.println("--- Performance measurement: get(i) ---");

        int size = 100_000;
        List<Integer> large_al = new ArrayList<>();
        List<Integer> large_ll = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            large_al.add(i);
            large_ll.add(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            large_al.get(i);
        }
        long timeAL = System.nanoTime() - start;

        start = System.nanoTime();
        // Only 1000 accesses in LinkedList to avoid taking too long
        int llAccesses = 1000;
        for (int i = 0; i < llAccesses; i++) {
            large_ll.get(i);
        }
        long timeLL = System.nanoTime() - start;

        System.out.println("ArrayList  " + size + " gets: " + timeAL + " ns");
        System.out.println("LinkedList " + llAccesses + " gets: " + timeLL
                + " ns  (extrapolated: much slower for " + size + " accesses)");
        System.out.println();

        // -------------------------------------------------------
        // Performance measurement: insertion at the ends
        // -------------------------------------------------------
        System.out.println("--- Performance measurement: insertion at the beginning ---");

        List<Integer> al_front = new ArrayList<>();
        LinkedList<Integer> ll_front = new LinkedList<>();

        int insertions = 50_000;

        start = System.nanoTime();
        for (int i = 0; i < insertions; i++) {
            al_front.add(0, i); // O(n) — shifts all elements
        }
        long timeAlFront = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < insertions; i++) {
            ll_front.addFirst(i); // O(1)
        }
        long timeLlFront = System.nanoTime() - start;

        System.out.println("ArrayList addFirst  " + insertions + "x: " + timeAlFront + " ns");
        System.out.println("LinkedList addFirst " + insertions + "x: " + timeLlFront + " ns");
        System.out.println();

        System.out.println("--- Conclusion ---");
        System.out.println("Use ArrayList: access by index, frequent reads, add at the end.");
        System.out.println("Use LinkedList/ArrayDeque: queues, stacks, many insertions at the ends.");
    }
}
