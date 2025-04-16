package management;

import java.util.*;
import model.*;
import repository.StudentRepository;

public class StudentManagement {
    private final StudentRepository repository;

    public StudentManagement() {
        repository = new StudentRepository();
    }
    
    
    public boolean isValidNewStudent(Person student) {
        if (student == null || student.getStudentId() == null || !student.getStudentId().matches("[A-Z]{2}\\d{3}")) {
            System.out.println("Student ID is not valid!");
            return false;
        }
        if (repository.getStudent(student.getStudentId()) != null) {
            System.out.println("Student ID already exists!");
            return false;
        }
        if (student.getFullName() == null || student.getFullName().trim().isEmpty()) {
            System.out.println("Full name cannot be empty!");
            return false;
        }
        if (!areMarksValid(student)) {
            System.out.println("❌ Marks must be between 0 and 10!");
            return false;
        }
        return true;
    }
    
    private boolean areMarksValid(Person student) {
        if (!(student instanceof Major)) return true;
        for (double mark : ((Major) student).getAllMarks()) {
            if (mark < 0 || mark > 10) return false;
        }
        return true;
    }
    
    public void addStudent(Person student) {
        if (!isValidNewStudent(student)) {
            System.out.println("Add student failed because data is not valid.");
            return;
        }
        repository.addStudent(student);
        System.out.println("Add student success!");
    }
    
    public void displayAll() {
        List<Person> students = repository.getAllStudents();
        if (checkListEmpty(students)) return;
        for (Person student : students)
            displayStudentWithRanking(student);
    }
    
    public void binarySearchByName(String name) {
        List<Person> list = new ArrayList<>(repository.getAllStudents());
        if (checkListEmpty(list)) return;
        list.sort(Comparator.comparing(p -> p.getFullName().toLowerCase()));
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            String midName = list.get(mid).getFullName().toLowerCase();
            int cmp = midName.compareTo(name.toLowerCase());
            if (cmp == 0) {
                displayStudentWithRanking(list.get(mid));
                return;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("Student not found: " + name);
    }
    
    public void searchById(String studentId) {
        Person student = repository.getStudent(studentId);  // HashMap lookup
        if (student != null) {
            displayStudentWithRanking(student);
        } else {
            System.out.println("Student ID not found: " + studentId);
        }
    }

    
    public void sortByName() {
        sortAndDisplay(repository.getAllStudents(), Comparator.comparing(p -> p.getFullName().toLowerCase()), false);
    }
    
    public void sortByAverageMark() {
        sortAndDisplay(repository.getAllStudents(), Comparator.comparing(p -> ((Major)p).calculateAverageMark()), true);
    }
    
    private void sortAndDisplay(List<Person> list, Comparator<Person> comparator, boolean withRanking) {
        if (checkListEmpty(list)) return;
        list.sort(comparator);
        for (Person student : list) {
            if (withRanking)
                displayStudentWithRanking(student);
            else
                student.displayInfo();
        }
    }
    
    public void updateStudent(String studentId, Scanner sc) {
        Person toUpdate = repository.getStudent(studentId);
        if (toUpdate == null) {
            System.out.println("Student ID not found: " + studentId);
            return;
        }
        System.out.print("Enter new full name: ");
        sc.nextLine(); // clear newline
        toUpdate.setFullName(sc.nextLine());
        if (toUpdate instanceof Major) {
            ((Major) toUpdate).inputMarks(sc);
        }
        System.out.println("Student information updated successfully!");
    }
    
    public void removeStudent(String studentId) {
        if (repository.removeStudent(studentId))
            System.out.println("Student removed successfully!");
        else
            System.out.println("Student ID not found: " + studentId);
    }
    
    private void displayStudentWithRanking(Person student) {
        double avg = (student instanceof Major) ? ((Major) student).calculateAverageMark() : 0;
        student.displayInfo();
        System.out.println("Ranking: " + getRanking(avg));
    }
    
    public String getRanking(double averageMark) {
        return (averageMark < 5) ? "Fail" :
               (averageMark < 6.5) ? "Medium" :
               (averageMark < 7.5) ? "Good" :
               (averageMark < 9.0) ? "Very Good" :
               (averageMark <= 10) ? "Excellent" : "Invalid mark";
    }
    
    private boolean checkListEmpty(List<Person> list) {
        if (list.isEmpty()) {
            System.out.println("Student list is empty.");
            return true;
        }
        return false;
    }
}
