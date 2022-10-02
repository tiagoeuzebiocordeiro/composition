package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) {
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
	
		System.out.print("Enter department name: ");
		String department = sc.nextLine();
		
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		
		System.out.print("Level: ");
		String workerLevel = sc.nextLine();
		
		System.out.print("Base salary: ");
		double baseSalary = sc.nextDouble();
		
		Worker worker = new Worker(name, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(department));
		
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		
		for (int i=1; i<=n; i++) {
			System.out.println("Enter contract #" + i + " data:");
			
			System.out.print("Date (DD/MM/YYYY): ");
			LocalDate contractDate = LocalDate.parse(sc.next(), fmt);
			
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(contract);
		}
		
		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String monthAndYear = sc.next();
		
		int month = Integer.parseInt(monthAndYear.substring(0, 2)); // recortando string a partir da pos 0 até 1 (sempre soma +1) tbm
		int year = Integer.parseInt(monthAndYear.substring(3)); // recortando string 3 e diante
		
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + monthAndYear + ": " + String.format("$%.2f", worker.income(year, month)));
		
		sc.close();
		
	}

}
