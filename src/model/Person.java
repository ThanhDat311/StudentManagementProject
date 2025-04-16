package model;

/**
* Abstract class Person contains basic information about a student.
*/
public abstract class Person {
    protected String studentId;
    protected String fullName;
    
    public Person(String studentId, String fullName) {
        this.studentId = studentId;
        this.fullName = fullName;
    }
    
    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    // Abstract method to display information
    public abstract void displayInfo();
    
    // Basic information display method
    public void displayBasicInfo() {
        System.out.print("ID: " + studentId + ", Full Name: " + fullName);
    }
}
