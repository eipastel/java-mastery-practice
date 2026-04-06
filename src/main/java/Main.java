import runner.Executable;

import java.util.List;

void main() {
    List<Executable> exercises = List.of(

    );

    for (Executable exercise : exercises) {
        System.out.println("-----------------------------------");
        System.out.println("Executing: " + exercise.getName());
        exercise.execute();
    }

    System.out.println("-----------------------------------");
    System.out.println("All exercises executed.");
}
