import java.util.Scanner;

public class GPAManager {

    // Max number of courses the student can enter
    public static final int MAX = 50;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Arrays to store course data
        String[] courseNames = new String[MAX];
        int[] creditHours = new int[MAX];
        String[] letterGrades = new String[MAX];
        int count = 0;  // how many courses stored

        int choice;

        do {
            printMenu();
            choice = input.nextInt();
            input.nextLine();  // clear newline

            switch (choice) {

                case 1:
                    if (count < MAX) {
                        count = addCourse(input, courseNames, creditHours, letterGrades, count);
                    } else {
                        System.out.println("Course list is full. Cannot add more.");
                    }
                    break;

                case 2:
                    listCourses(courseNames, creditHours, letterGrades, count);
                    break;

                case 3:
                    computeGPA(creditHours, letterGrades, count);
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        input.close();
    }

    // ------------------- MENU ---------------------
    public static void printMenu() {
        System.out.println("\n===== GPA MANAGER MENU =====");
        System.out.println("1. Add a Course");
        System.out.println("2. List Courses");
        System.out.println("3. Compute GPA");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    // ------------------- OPTION 1: ADD COURSE ---------------------
    public static int addCourse(Scanner input, String[] names, int[] credits, String[] grades, int count) {
        System.out.print("Enter course name: ");
        names[count] = input.nextLine();

        System.out.print("Enter credit hours: ");
        credits[count] = input.nextInt();
        input.nextLine();

        System.out.print("Enter letter grade (A, B, C, D, F): ");
        grades[count] = input.nextLine().toUpperCase();

        System.out.println("Course added successfully!");
        return count + 1;
    }

    // ------------------- OPTION 2: LIST COURSES ---------------------
    public static void listCourses(String[] names, int[] credits, String[] grades, int count) {

        if (count == 0) {
            System.out.println("No courses added yet.");
            return;
        }

        System.out.println("\n----- COURSE LIST -----");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + names[i] +
                    " | Credits: " + credits[i] +
                    " | Grade: " + grades[i]);
        }
    }

    // ------------------- OPTION 3: COMPUTE GPA ---------------------
    public static void computeGPA(int[] credits, String[] grades, int count) {

        if (count == 0) {
            System.out.println("No courses available for GPA calculation.");
            return;
        }

        double totalQualityPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < count; i++) {
            double gradePoints = convertLetterToPoints(grades[i]);
            totalQualityPoints += gradePoints * credits[i];
            totalCredits += credits[i];
        }

        double gpa = totalQualityPoints / totalCredits;
        System.out.printf("Your GPA is: %.2f\n", gpa);
    }

    // ------------------- HELPER: CONVERT LETTER GRADE ---------------------
    public static double convertLetterToPoints(String grade) {
        switch (grade) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            case "F": return 0.0;
            default:  return 0.0;
        }
    }
}
