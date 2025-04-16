package model;

import java.util.Scanner;

/**
* The Major interface defines how to enter scores, calculate the average score, and get all scores.
*/
public interface Major {
    void inputMarks(Scanner sc);
    double calculateAverageMark();
    double[] getAllMarks();
}
