package dsaa_jk.assignment_2;

public class Main {

    public static void main(String[] args) {
        double passingGrade = 3.0;

        Task2 t = new Task2(passingGrade);
        t.printList();

        System.out.println("");
        t.addGrade(2, 5.5);
        t.printList();

        System.out.println("");
        System.out.println("The mean of the passing students: " + t.getGradeMean());

        System.out.println("");
        t.printFailedStudents();
    }


}
