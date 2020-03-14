package dsaa_jk.assignment_2;

import java.util.ArrayList;

import static dsaa_jk.assignment_2.Utilities.roundToOnePlace;

public class Student {
    public int id;
    public String surname;
    public String name;
    public ArrayList<Double> grades = new ArrayList<Double>();

    public Student(int id, String surname, String name){
        this.id = id;
        this.surname = surname;
        this.name = name;
    }

    public void addGrade(double grade){
        this.grades.add(grade);
    }

    public double getAverageGrade() {
        if(grades.size() > 0) {
            double sum = 0;
            for(Double g: grades) {
                sum += g;
            }
            return sum/grades.size();
        }
        return -1;
    }

    public String getGrades() {
        StringBuilder result = new StringBuilder("{");
        for(Double g: grades) {
            result.append(g).append(", ");
        }
        result.append("}");
        return result.toString();
    }

    public String toString(){
        return id + " " + surname + " " + name + " " + getGrades() + ", avg: " + roundToOnePlace(getAverageGrade());
    }



}
