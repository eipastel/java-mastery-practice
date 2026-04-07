package block1_base._01_memory._01_stack_vs_heap;

public class Exercise {

    // This static field lives on the heap (class metadata area)
    static int instanceCounter = 0;

    // Instance fields live on the heap, inside the object
    String name;
    int age;

    public Exercise(String name, int age) {
        this.name = name;
        this.age = age;
        instanceCounter++;
    }

    public static void main(String[] args) {

        System.out.println("=== Stack vs Heap ===");
        System.out.println();

        // -------------------------------------------------------
        // STACK: local primitive variables
        // Each of these variables lives in a frame on the stack.
        // When the main() method ends, that frame is destroyed.
        // -------------------------------------------------------
        int x = 10;          // primitive int — lives on the stack
        double pi = 3.14;    // primitive double — lives on the stack
        boolean active = true; // primitive boolean — lives on the stack

        System.out.println("--- Primitives on the Stack ---");
        System.out.println("x      = " + x);
        System.out.println("pi     = " + pi);
        System.out.println("active = " + active);
        System.out.println();

        // -------------------------------------------------------
        // HEAP: objects created with 'new'
        // The Exercise object is created on the heap.
        // The variable 'person' is a reference that lives on the stack,
        // but points to the object that is on the heap.
        // -------------------------------------------------------
        Exercise person = new Exercise("Ana", 30);
        // 'person' (reference) --> stack
        // Exercise object { name="Ana", age=30 } --> heap

        System.out.println("--- Object on the Heap ---");
        System.out.println("person.name = " + person.name);
        System.out.println("person.age  = " + person.age);
        System.out.println();

        // -------------------------------------------------------
        // STACK: demonstrating method scope
        // The createNameOnStack() method creates a String
        // and returns. After the return, the stack frame is gone,
        // but the String object on the heap still exists because
        // the returned reference keeps it alive.
        // -------------------------------------------------------
        String returnedName = createNameOnStack("Maria");
        System.out.println("--- Method return ---");
        System.out.println("Returned name: " + returnedName);
        System.out.println();

        // -------------------------------------------------------
        // Arrays also live on the heap
        // -------------------------------------------------------
        int[] numbers = {1, 2, 3, 4, 5};
        // 'numbers' (reference to the array) --> stack
        // the int[5] array itself --> heap

        System.out.println("--- Array on the Heap ---");
        System.out.print("Array: ");
        for (int n : numbers) {
            System.out.print(n + " ");
        }
        System.out.println();
        System.out.println();

        // -------------------------------------------------------
        // Demonstrating method frames on the stack
        // Each method call pushes a new frame.
        // -------------------------------------------------------
        System.out.println("--- Method calls (frames on the stack) ---");
        int result = calculate(5, 3);
        System.out.println("calculate(5, 3) = " + result);
        System.out.println();

        System.out.println("Total instances created: " + instanceCounter);
    }

    // Variables 'a' and 'b' live in this method's frame on the stack.
    // When the method returns, the frame is destroyed and 'a'/'b' disappear.
    static int calculate(int a, int b) {
        int sum = a + b;     // 'sum' lives on the stack, in this frame
        int product = a * b; // 'product' lives on the stack, in this frame
        System.out.println("  Inside calculate(): a=" + a + ", b=" + b
                + ", sum=" + sum + ", product=" + product);
        return sum;
        // on return: frame destroyed, 'a', 'b', 'sum', 'product' vanish from the stack
    }

    // Creates a String object on the heap and returns the reference.
    // The local variable 'local' is destroyed with the frame,
    // but the String object on the heap keeps existing as long as
    // someone points to it (in this case, the caller).
    static String createNameOnStack(String prefix) {
        String local = prefix + "_created"; // object on the heap, reference on the stack
        return local;
        // 'local' (reference on the stack) disappears, but the String object survives on the heap
    }
}
