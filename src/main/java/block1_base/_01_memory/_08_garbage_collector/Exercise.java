package block1_base._01_memory._08_garbage_collector;

import java.lang.ref.WeakReference;
import java.lang.ref.SoftReference;

public class Exercise {

    // Helper class to demonstrate the lifecycle
    static class Resource {
        String name;

        Resource(String name) {
            this.name = name;
            System.out.println("  [Resource created] " + name);
        }

        // finalize() was deprecated in Java 9, but still works for demonstration.
        // In production, use AutoCloseable + try-with-resources.
        @Override
        @SuppressWarnings("deprecation")
        protected void finalize() throws Throwable {
            System.out.println("  [finalize() called] " + name + " being collected (do not rely on this in production!)");
            super.finalize();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== Garbage Collector ===");
        System.out.println();

        // -------------------------------------------------------
        // Object without a reference: eligible for collection
        // -------------------------------------------------------
        System.out.println("--- Object becoming eligible for GC ---");

        Resource r = new Resource("ResourceA");
        System.out.println("  Active reference: r = " + r.name);

        r = null; // the reference is removed; the object on the heap has no owner
        System.out.println("  After r = null: object on the heap has no reference — eligible for GC");
        System.out.println("  System.gc() is just a HINT — the GC may ignore it.");
        System.gc(); // hint (not a guarantee)
        Thread.sleep(100); // small wait to give the GC a chance to run
        System.out.println();

        // -------------------------------------------------------
        // WeakReference: weak reference — GC can collect it whenever
        // -------------------------------------------------------
        System.out.println("--- WeakReference ---");

        Resource strong = new Resource("ResourceB");
        WeakReference<Resource> weak = new WeakReference<>(strong);

        System.out.println("  Before removing the strong reference:");
        System.out.println("  weak.get() = " + weak.get().name);

        strong = null; // we remove the only strong reference
        System.out.println("  After strong = null:");
        System.gc();
        Thread.sleep(100);

        Resource afterGC = weak.get();
        if (afterGC == null) {
            System.out.println("  weak.get() = null — object was collected by the GC");
        } else {
            System.out.println("  weak.get() = " + afterGC.name + " — GC has not collected yet");
        }
        System.out.println();

        // -------------------------------------------------------
        // SoftReference: more resistant than WeakReference
        // Only collected when the JVM needs memory
        // -------------------------------------------------------
        System.out.println("--- SoftReference (GC-resistant cache) ---");

        Resource cachedResource = new Resource("ResourceCache");
        SoftReference<Resource> soft = new SoftReference<>(cachedResource);
        cachedResource = null;

        System.gc();
        Thread.sleep(100);

        Resource stillInMemory = soft.get();
        if (stillInMemory != null) {
            System.out.println("  soft.get() = " + stillInMemory.name
                    + " — SoftReference resists GC under available memory");
        } else {
            System.out.println("  soft.get() = null — GC collected under memory pressure");
        }
        System.out.println();

        // -------------------------------------------------------
        // Demonstration: scope determines when an object becomes available for GC
        // -------------------------------------------------------
        System.out.println("--- Scope and GC eligibility ---");

        {
            // Anonymous block: object exists here
            Resource temporary = new Resource("TemporaryResource");
            System.out.println("  Inside the block: " + temporary.name);
        } // 'temporary' goes out of scope; the object on the heap becomes eligible for GC

        System.out.println("  Outside the block: 'temporary' is no longer accessible (eligible for GC)");
        System.gc();
        Thread.sleep(100);
        System.out.println();

        System.out.println("--- Conclusions ---");
        System.out.println("  1. The GC runs autonomously — it is not deterministic.");
        System.out.println("  2. System.gc() is just a hint.");
        System.out.println("  3. WeakReference: collected on the next GC run.");
        System.out.println("  4. SoftReference: collected only under memory pressure.");
        System.out.println("  5. Use try-with-resources to release resources (IO, DB) — not the GC.");
    }
}
