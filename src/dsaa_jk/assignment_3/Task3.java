package dsaa_jk.assignment_3;

import com.github.javafaker.Faker;

public class Task3 {
    Student[] array;
    double passingGrade;

    public Task3(double passingGrade) {
        int howManyStudents = 5;
        this.array = generateStudents(howManyStudents);
        this.passingGrade = passingGrade;
    }

    public ArrayIterator<Student> getArrayIterator() {
        return new ArrayIterator<Student>(array);
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

    public void printList(ArrayIterator<Student> iter) {
        while (iter.hasNext())
            System.out.println(iter.next());
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

    public ArrayIterator<Student> addStudent(ArrayIterator<Student> arrayIterator, Student s) {
        arrayIterator.first();
        int arrayLength = 0;
        while(arrayIterator.hasNext()) {
            arrayLength++;
            arrayIterator.next();
        }
        Student[] newArray = new Student[arrayLength + 1];
        arrayIterator.first();

        int i = 0;
        while(arrayIterator.hasNext()) {
            newArray[i] = arrayIterator.next();
            i++;
        }
        newArray[i] = s;

        return new ArrayIterator<>(newArray);
    }

    public ArrayIterator<Student> deleteStudent(ArrayIterator<Student> arrayIterator, int studentId) {
        Predicate<Student> pred = new Predicate<Student>() {
            @Override
            public boolean accept(Student student) {
                return student.id != studentId;
            }
        };

        Iterator<Student> filterStud = new FilterIterator<>(arrayIterator, pred);
        filterStud.first();

        int newArrayLength = 0;
        while(filterStud.hasNext()) {
            newArrayLength++;
            filterStud.next();
        }
        Student[] newArray = new Student[newArrayLength];

        filterStud.first();
        int i = 0;
        while(filterStud.hasNext()) {
            newArray[i] = filterStud.next();
            i++;
        }
        return new ArrayIterator<>(newArray);
    }

    public ArrayIterator<Student> sortStudents(ArrayIterator<Student> arrayIterator) {
        arrayIterator.first();
        int arrayLength = 0;
        while(arrayIterator.hasNext()) {
            arrayLength++;
            arrayIterator.next();
        }

        Student[] studentArray = new Student[arrayLength];

        arrayIterator.first();

        int i = 0;
        while(arrayIterator.hasNext()) {
            studentArray[i] = arrayIterator.next();
            i++;
        }

        boolean isSwap = true;
        while(isSwap) {
            isSwap = false;
            for(i = 0; i < studentArray.length - 1; i++) {
                if(studentArray[i].grade < studentArray[i+1].grade) {
                    Student temp = studentArray[i];
                    studentArray[i] = studentArray[i + 1];
                    studentArray[i + 1] = temp;
                    isSwap = true;
                }
            }
        }

        return new ArrayIterator<>(studentArray);
    }
}
