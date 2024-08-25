package Task2;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<Integer>> subjectMarks = new HashMap<>();
        System.out.println("Enter the number of subjects:");
        int numSubjects = scanner.nextInt();

        for (int i = 0; i < numSubjects; i++) {
            System.out.println("Enter the name of subject " + (i + 1) + ":");
            String subjectName = scanner.next();
            List<Integer> marks = new ArrayList<>();
            String cont = "yes";

            while (cont.equalsIgnoreCase("yes")) {
                System.out.println("Enter the marks obtained in " + subjectName + ":");
                marks.add(scanner.nextInt());
                System.out.println("Do you want to continue? yes/no");
                cont = scanner.next();
            }
            subjectMarks.put(subjectName, marks);
        }

        int totalMarks = 0;
        int totalSubjects = 0;

        for (String subject : subjectMarks.keySet()) {
            int subjectTotal = calculateTotalMarks(subjectMarks, subject);
            totalMarks += subjectTotal;
            totalSubjects += subjectMarks.get(subject).size();
            System.out.println("Total marks for " + subject + ": " + subjectTotal);
        }

        double averagePercentage = (double) totalMarks / totalSubjects;
        String grade = calculateGrade(averagePercentage);

        System.out.println("\nOverall Results:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + String.format("%.2f%%", averagePercentage));
        System.out.println("Grade: " + grade);
    }

    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) return "A+";
        if (averagePercentage >= 80) return "A";
        if (averagePercentage >= 70) return "B";
        if (averagePercentage >= 60) return "C";
        if (averagePercentage >= 50) return "D";
        return "F";
    }

    private static int calculateTotalMarks(Map<String, List<Integer>> subjectMarks, String subject) {
        return subjectMarks.get(subject).stream().mapToInt(Integer::valueOf).sum();
    }

    ;
}
