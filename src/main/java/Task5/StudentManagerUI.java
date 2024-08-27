package Task5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class StudentManager {
    private List<Student> students;
    private static final String FILE_NAME = "students.dat";

    public StudentManager() {
        this.students = new ArrayList<>();
        loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added: " + student.getName());
        saveStudentsToFile();
    }

    public void removeStudent(int rollNumber) {
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student removed: " + studentToRemove.getName());
            saveStudentsToFile();
        } else {
            System.out.println("Student not found with roll number: " + rollNumber);
        }
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            System.out.println("All Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void editStudent(int rollNumber, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber() == rollNumber) {
                students.set(i, updatedStudent);
                System.out.println("Student information updated.");
                saveStudentsToFile();
                return;
            }
        }
        System.out.println("Student not found with roll number: " + rollNumber);
    }

    private void saveStudentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println("Students data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving students data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStudentsFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                students = (List<Student>) ois.readObject();
                System.out.println("Students data loaded from file.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading students data: " + e.getMessage());
            }
        }
    }
}
