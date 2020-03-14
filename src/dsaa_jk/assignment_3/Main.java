package dsaa_jk.assignment_3;

public class Main {

    public static void main(String[] args) {
        double passingGrade = 3.0;

        Task3 t = new Task3(passingGrade);

        ArrayIterator<Student> it = t.getArrayIterator();
        it = t.addStudent(it, new Student(6, "Kowalski", "Jan", 3.5));
        t.printList(it);

        System.out.println("");
        it = t.deleteStudent(it, 2);
        t.printList(it);

        System.out.println("");
        it = t.sortStudents(it);
        t.printList(it);
    }


}
