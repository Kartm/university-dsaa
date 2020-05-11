package dsaa_jk.assignment_1;

public class Main
{
	
	// removed this lololo
	public static void main(String[] args)
	{
		
		double passingGrade = 3.0;
		
		Task1 t = new Task1(passingGrade);
		t.printList();
		
		System.out.println("");
		t.setGrade(2, 4.5);
		t.printList();
		
		System.out.println("");
		System.out.println("The mean of the passing students: " + t.getGradeMean());
		
		System.out.println("");
		t.printFailedStudents();
		
	}
	
}
