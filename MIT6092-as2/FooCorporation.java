/*
 * Assignment 2: FooCorporation
 * Name: Arti
 * Created date: 03, 04 Apr 2017
 * Last updted: 
 */

public class FooCorporation {

	public static void main(String[] args) {
		Employee[] employees = {
									new Employee("Employee 1", 7.50, 35.0), 
									new Employee("Employee 2", 8.20, 47.0), 
									new Employee("Employee 3", 10.0, 73.0)
								};
		for(int i = 0; i < employees.length; i++)
		{
			employees[i].TotalPay = CalculatePay(employees[i].BasePay, employees[i].HoursWorked);
			
			if(employees[i].TotalPay != -1)
			{
				System.out.println("Payment for " + employees[i].Name + " is $" + employees[i].TotalPay);
			}
		}
		
	}
	
	static double CalculatePay(double basePay, double hoursWorked)
	{
		double totalPay = 0;
		
		try 
		{
			if(basePay < 8)
			{
				throw new Exception("The State of Massachusetts requires that hourly employees be paid at least $8.00 an hour.");
			}
			
			if(hoursWorked > 60)
			{
				throw new Exception("Foo Corp requires that an employee not work more than 60 hours in a week.");
			}
			
			double basicPayHours = hoursWorked;
			double overtime = 0;
			
			if(hoursWorked > 40)
			{
				basicPayHours = 40;
				overtime = hoursWorked - 40;
			}
			
			double basicPayment = basePay * basicPayHours;
			double overtimePayment = (basePay * 1.5) * overtime;
			totalPay = basicPayment + overtimePayment;
		}
		catch(Exception ex)
		{
			totalPay = -1;
			System.out.println("Error has occurred while calculating pay !!! Details: " + ex.getMessage());
		}
		
		return totalPay;
	}

}

class Employee
{
	String Name;
	double BasePay;
	double HoursWorked;
	double TotalPay;
	
	public Employee(String name, double basePay, double hoursWorked)
	{
		Name = name;
		BasePay = basePay;
		HoursWorked = hoursWorked;
	}
	
}
