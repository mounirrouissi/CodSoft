package Task5;

import javax.swing.*;
import java.awt.*;

public class StudentManagementSystemGUI extends JFrame {
    private StudentManager sms;
    private JTextField nameField, rollNumberField, gradeField, ageField;
    private JTextArea displayArea;
    private JButton addButton, editButton, removeButton, searchButton, displayAllButton;

    public StudentManagementSystemGUI() {
        sms = new StudentManager();
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        nameField = new JTextField(20);
        rollNumberField = new JTextField(10);
        gradeField = new JTextField(5);
        ageField = new JTextField(5);

        addButton = new JButton("Add Student");
        editButton = new JButton("Edit Student");
        removeButton = new JButton("Remove Student");
        searchButton = new JButton("Search Student");
        displayAllButton = new JButton("Display All Students");

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);

        // Create panels
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll Number:"));
        inputPanel.add(rollNumberField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayAllButton);

        // Add components to the frame
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(e -> addStudent());
        editButton.addActionListener(e -> editStudent());
        removeButton.addActionListener(e -> removeStudent());
        searchButton.addActionListener(e -> searchStudent());
        displayAllButton.addActionListener(e -> displayAllStudents());
    }

    private void addStudent() {
        try {
            String name = nameField.getText().trim();
            int rollNumber = Integer.parseInt(rollNumberField.getText().trim());
            String grade = gradeField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());

            if (name.isEmpty() || grade.isEmpty()) {
                throw new IllegalArgumentException("Name and grade cannot be empty");
            }

            Student student = new Student(name, rollNumber, grade, age);
            sms.addStudent(student);
            displayArea.setText("Student added successfully");
            clearFields();
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid roll number or age. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            displayArea.setText(e.getMessage());
        }
    }

    private void editStudent() {
        try {
            int rollNumber = Integer.parseInt(rollNumberField.getText().trim());
            Student student = sms.searchStudent(rollNumber);
            if (student != null) {
                String name = nameField.getText().trim();
                String grade = gradeField.getText().trim();
                String ageStr = ageField.getText().trim();

                if (!name.isEmpty()) student.setName(name);
                if (!grade.isEmpty()) student.setGrade(grade);
                if (!ageStr.isEmpty()) {
                    int age = Integer.parseInt(ageStr);
                    if (age > 0 && age <= 150) student.setAge(age);
                    else throw new IllegalArgumentException("Invalid age");
                }

                sms.editStudent(rollNumber, student);
                displayArea.setText("Student updated successfully");
                clearFields();
            } else {
                displayArea.setText("Student not found");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid roll number or age. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            displayArea.setText(e.getMessage());
        }
    }

    private void removeStudent() {
        try {
            int rollNumber = Integer.parseInt(rollNumberField.getText().trim());
            sms.removeStudent(rollNumber);
            displayArea.setText("Student removed successfully");
            clearFields();
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid roll number. Please enter a valid number.");
        }
    }

    private void searchStudent() {
        try {
            int rollNumber = Integer.parseInt(rollNumberField.getText().trim());
            Student student = sms.searchStudent(rollNumber);
            if (student != null) {
                displayArea.setText("Student found: " + student);
            } else {
                displayArea.setText("Student not found");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid roll number. Please enter a valid number.");
        }
    }

    private void displayAllStudents() {
        displayArea.setText(sms.getAllStudentsAsString());
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
        ageField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementSystemGUI().setVisible(true));
    }
}
