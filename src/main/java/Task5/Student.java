package Task5;

import java.io.Serializable;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private int age;

    public Student(String name, int rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getRollNumber() { return rollNumber; }
    public void setRollNumber(int rollNumber) { this.rollNumber = rollNumber; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', rollNumber=" + rollNumber +
                ", grade='" + grade + "', age=" + age + "}";
    }
}