package dsaa_jk.assignment_1;

import com.github.javafaker.Faker;

public class Task1 {
    Student[] array;
    double passingGrade;

    public Task1(double passingGrade) {
        int howManyStudents = 5;
        this.array = generateStudents(howManyStudents);
        this.passingGrade = passingGrade;
    }

    private Student[] generateStudents(int howManyStudents) {
        Student[] array = new Student[howManyStudents];
        for (int i = 1; i <= howManyStudents; i++) {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            array[i - 1] = new Student(i, lastName, firstName, RandomData.grade());
        }
        return array;
    }

    public void printList() {
        Iterator<Student> iterStud = new ArrayIterator<>(array);
        while (iterStud.hasNext())
            System.out.println(iterStud.next());
    }

    public void setGrade(int id, double grade) {
        Predicate<Student> pred = new Predicate<Student>() {

            @Override
            public boolean accept(Student s) {
                return s.id == id;
            }
        };

        Iterator<Student> iterStud = new ArrayIterator<Student>(array);
        Iterator<Student> filterStud = new FilterIterator<Student>(iterStud, pred);
        while (filterStud.hasNext())
            filterStud.next().setGrade(grade);
    }

    public double getGradeMean() {
        Iterator<Student> iterStud = new ArrayIterator<>(array);
        int passingStudentsTotal = 0;
        double gradeSum = 0;
        while (iterStud.hasNext()) {
            Student student = iterStud.next();
            if (student.grade >= passingGrade) {
                passingStudentsTotal++;
                gradeSum += student.grade;
            }
        }
        if (passingStudentsTotal > 0) {
            return gradeSum / passingStudentsTotal;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void printFailedStudents() {
        Iterator<Student> iterStud = new ArrayIterator<>(array);
        System.out.println("Failed students: ");
        while (iterStud.hasNext()) {
            Student student = iterStud.next();
            if (student.grade < passingGrade) {
                System.out.println(student);
            }
        }
    }
}
