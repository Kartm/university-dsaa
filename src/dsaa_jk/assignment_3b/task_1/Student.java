package dsaa_jk.assignment_3b.task_1;

public class Student {
    int id;
    double scholarShip;

    public Student(int id, double value) {
        this.id = id;
        scholarShip = value;
    }

    public void increaseScholarship(double value) {
        scholarShip += value;
    }

    @Override
    public String toString() {
        return String.format("%6d %8.2f", id, scholarShip);
    }

    public boolean equals(Student stud) {
        return id == stud.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return equals((Student) obj);
    }

}

