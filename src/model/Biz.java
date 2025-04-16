package model;

import java.util.Scanner;
import util.InputHelper;

/**
* The Biz class represents Business students.
*/
public class Biz extends Person implements Major {
    private double sale;
    private double marketing;
    
    public Biz(String studentId, String fullName, double sale, double marketing) {
        super(studentId, fullName);
        this.sale = sale;
        this.marketing = marketing;
    }
    
    // Getters and Setters
    public double getSale() { return sale; }
    public void setSale(double sale) { this.sale = sale; }
    public double getMarketing() { return marketing; }
    public void setMarketing(double marketing) { this.marketing = marketing; }
    
    // Calculate average score: (sale * 2 + marketing) / 3
    @Override
    public double calculateAverageMark() {
        return (sale * 2 + marketing) / 3;
    }
    
    // Returns the array of points
    @Override
    public double[] getAllMarks() {
        return new double[] { sale, marketing };
    }
    
  // Enter score using InputHelper
    @Override
    public void inputMarks(Scanner sc) {
        this.sale = InputHelper.inputSingleMark(sc, "Enter Sale mark: ");
        this.marketing = InputHelper.inputSingleMark(sc, "Enter Marketing mark: ");
    }
    
   // Display student information Biz
    @Override
    public void displayInfo() {
        displayBasicInfo();
        System.out.println(", Major: Biz, Sale: " + sale + ", Marketing: " + marketing +
                           ", Avg Mark: " + String.format("%.2f", calculateAverageMark()));
    }
}
