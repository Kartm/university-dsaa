package dsaa_jk.assignment_2;

import com.github.javafaker.Faker;

public class Task2 {
    Student[] array;
    double passingGrade;

    public Task2(double passingGrade) {
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
            Student student = new Student(i, lastName, firstName);
            student.addGrade(RandomData.grade());
            student.addGrade(RandomData.grade());
            student.addGrade(RandomData.grade());
            array[i - 1] = student;
        }
        return array;
    }

    public void printList() {
        Iterator<Student> iterStud = new ArrayIterator<>(array);
        while (iterStud.hasNext())
            System.out.println(iterStud.next());

    }

    public void addGrade(int id, double grade) {
        Predicate<Student> pred = s -> s.id == id;

        Iterator<Student> iterStud = new ArrayIterator<Student>(array);
        Iterator<Student> filterStud = new FilterIterator<Student>(iterStud, pred);
        while (filterStud.hasNext())
            filterStud.next().addGrade(grade);
    }

    public double getGradeMean() {
        Iterator<Student> iterStud = new ArrayIterator<>(array);
        int passingStudentsTotal = 0;
        double gradeSum = 0;
        while (iterStud.hasNext()) {
            Student student = iterStud.next();
            if (student.getAverageGrade() >= passingGrade) {
                passingStudentsTotal++;
                gradeSum += student.getAverageGrade();
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
            if (student.getAverageGrade() < passingGrade) {
                System.out.println(student);
            }
        }
    }
}
