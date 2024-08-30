package Task5;

import java.util.Scanner;

public class StudentManagerUI {
    private static StudentManager sms = new StudentManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getValidIntInput("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> removeStudent();
                case 4 -> searchStudent();
                case 5  -> sms.displayAllStudents();
                case 6 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                }
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Add a student");
        System.out.println("2. Edit a student");
        System.out.println("3. Remove a student");
        System.out.println("4. Search for a student");
        System.out.println("5. Display all students");
        System.out.println("6. Exit");
    }

    private static void addStudent() {
        String name = getValidStringInput("Enter student name: ");
        int rollNumber = getValidIntInput("Enter roll number: ", 1, Integer.MAX_VALUE);
        String grade = getValidStringInput("Enter grade: ");
        int age = getValidIntInput("Enter age: ", 1, 150);

        Student student = new Student(name, rollNumber, grade, age);
        sms.addStudent(student);
    }

    private static void editStudent() {
        int rollNumber = getValidIntInput("Enter roll number of student to edit: ", 1, Integer.MAX_VALUE);
        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Current student information: " + student);
            String name = getValidStringInput("Enter new name (or press Enter to keep current): ");
            if (!name.isEmpty()) student.setName(name);

            String gradeInput = getValidStringInput("Enter new grade (or press Enter to keep current): ");
            if (!gradeInput.isEmpty()) student.setGrade(gradeInput);

            String ageInput = getValidStringInput("Enter new age (or press Enter to keep current): ");
            if (!ageInput.isEmpty()) {
                int age = Integer.parseInt(ageInput);
                if (age > 0 && age <= 150) student.setAge(age);
                else System.out.println("Invalid age. Keeping current age.");
            }

            sms.editStudent(rollNumber, student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void removeStudent() {
        int rollNumber = getValidIntInput("Enter roll number of student to remove: ", 1, Integer.MAX_VALUE);
        sms.removeStudent(rollNumber);
    }

    private static void searchStudent() {
        int rollNumber = getValidIntInput("Enter roll number of student to search: ", 1, Integer.MAX_VALUE);
        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static String getValidStringInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static int getValidIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
