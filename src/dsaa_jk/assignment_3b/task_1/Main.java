package dsaa_jk.assignment_3b.task_1;

import java.util.function.Predicate;

public class Main
{
	
	public static void main(String[] args)
	{
		
		LinkedListHead<Student> l = new LinkedListHead<>();
		l.add(new Student(1, 10));
		l.add(new Student(2, 20));
		l.add(new Student(3, 30));
		l.add(new Student(4, 40));
		l.add(new Student(5, 50));
		l.add(new Student(6, 20));
		l.add(new Student(7, 10));
		l.add(new Student(8, 40));
		l.add(new Student(9, 50));
		Predicate<Student> p = new Predicate<Student>()
		{
			
			@Override
			public boolean test(Student student)
			{
				
				return student.scholarShip < 30;
				
			}
			
		};
		// l.show();
		// l.remove(p);
		// l.show();
		UnboundedQueue<Student> q = new UnboundedQueue<>();
		q.enqueue(new Student(1, 10));
		q.enqueue(new Student(2, 20));
		q.enqueue(new Student(3, 30));
		q.enqueue(new Student(4, 40));
		q.enqueue(new Student(5, 50));
		q.enqueue(new Student(6, 20));
		q.enqueue(new Student(7, 10));
		q.enqueue(new Student(8, 40));
		q.enqueue(new Student(9, 50));
		q.print();
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		q.print();
		System.out.println(q.first());
		q.print();
		q.clear();
		q.print();
		
	}
	
}
