package model;

import java.util.Scanner;
import util.InputHelper;


public class GD extends Person implements Major {
    private double color;
    private double pts;
    private double ai;
    
    public GD(String studentId, String fullName, double color, double pts, double ai) {
        super(studentId, fullName);
        this.color = color;
        this.pts = pts;
        this.ai = ai;
    }
    
    // Getters and Setters
    public double getColor() { return color; }
    public void setColor(double color) { this.color = color; }
    public double getPts() { return pts; }
    public void setPts(double pts) { this.pts = pts; }
    public double getAi() { return ai; }
    public void setAi(double ai) { this.ai = ai; }
    
    // Calculate average score: (color + pts * 2 + ai) / 4
    @Override
    public double calculateAverageMark() {
        return (color + pts * 2 + ai) / 4;
    }
    
    // Returns the array of points
    @Override
    public double[] getAllMarks() {
        return new double[] { color, pts, ai };
    }
    
    // Input point using InputHelper
    @Override
    public void inputMarks(Scanner sc) {
        this.color = InputHelper.inputSingleMark(sc, "Enter Color mark: ");
        this.pts = InputHelper.inputSingleMark(sc, "Enter PTS mark: ");
        this.ai = InputHelper.inputSingleMark(sc, "Enter AI mark: ");
    }
    
   // Input point using InputHelper
    @Override
    public void displayInfo() {
        displayBasicInfo();
        System.out.println(", Major: GD, Color: " + color + ", PTS: " + pts + ", AI: " + ai +
                           ", Avg Mark: " + String.format("%.2f", calculateAverageMark()));
    }
}
