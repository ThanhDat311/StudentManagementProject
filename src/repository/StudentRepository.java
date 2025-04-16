package repository;

import java.util.*;
import model.Person;

public class StudentRepository {
    private HashMap<String, Person> studentMap;   
    private LinkedList<Person> studentList;         

    public StudentRepository() {
        studentMap = new HashMap<>();
        studentList = new LinkedList<>();
    }
    
    public void addStudent(Person student) {
        studentMap.put(student.getStudentId(), student);
        studentList.add(student);
    }
    
    public boolean removeStudent(String studentId) {
        Person student = studentMap.remove(studentId);
        if (student != null) {
            studentList.remove(student);
            return true;
        }
        return false;
    }
    
    public Person getStudent(String studentId) {
        return studentMap.get(studentId);
    }
    
    public List<Person> getAllStudents() {
        return studentList;
    }
}
