package dsaa_jk.assignment_1;

public class Student {
    public int id;
    public String surname;
    public String name;
    public double grade;

    public Student(int id, String surname, String name, double grade){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.grade = grade;
    }

    public void setGrade(double grade){
        this.grade = grade;
    }

    public String toString(){
        return id + " " + surname + " " + name + " " + grade;
    }



}
