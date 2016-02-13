package hibernate.working;

import java.util.Iterator;
import java.util.List;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CarManagement {
	
	static Scanner userInput = new Scanner(System.in);

	
	public CarManagement () {
		
	}
	
	/*
	 * Car management methods
	 */
	
	static void addCar () {
		Integer selection = null;
		String year;
		String make;
		String model;
		String color;
		
		OwnerManagement.listOwners();
		
		System.out.print("Enter the ID of the owner of the car: ");
		
		try {
			selection = Integer.parseInt(userInput.nextLine());
		} catch (NumberFormatException e) {
			System.out.println();
			System.out.println("Invalid entry. Returning to Car Menu.");
			return;
		}
		
		Owner theOwner = Model.queryOwnerById(selection);
		
		if (theOwner == null) {
			System.out.println();
			System.out.println("The ID you entered is invalid. Returning to Car Menu.");
			return;
		}
		
		System.out.println();
		System.out.print("Year: ");
		year = userInput.nextLine();
		System.out.print("Make: ");
		make = userInput.nextLine();
		System.out.print("Model: ");
		model = userInput.nextLine();
		System.out.print("Color: ");
		color = userInput.nextLine();
		
		Car carToInsert = new Car(theOwner,year,make,model,color);
		Set<Car> carSet = new HashSet<Car>();
		carSet.add(carToInsert);
		theOwner.setCars(carSet);
		
		System.out.println("Adding "+theOwner.getFullName()+"'s "+carToInsert.getMake()+" "
				+carToInsert.getModel()+" to the database.");
		Model.insertCar(carToInsert);
		
		System.out.println("Car successfully added to database!");
	}
	
	static void listAllCars () {
		List<Car> carList = Model.queryAllCars();
		
		System.out.println();
		System.out.println("Cars in Database");
		System.out.printf("%-5s%-20s%-7s%-16s%-18s%-16s\n","ID","Owner","Year","Make","Model","Color");
		System.out.printf("%-5s%-20s%-7s%-16s%-18s%-16s\n","--","-----","----","----","-----","-----");
		
		Iterator<Car> carIterator = carList.iterator();
		
		while(carIterator.hasNext()) {
			
			Car element = carIterator.next();
			System.out.printf("%-5s%-20s%-7s%-16s%-18s%-16s\n",element.getId(),element.getOwnerId().getFullName(),
					element.getYear(),element.getMake(),element.getModel(),element.getColor());
		}
	}
	
	static void updateCarInformation () {
		Integer selection = null;
		Car carToUpdate;
		
		listAllCars();
		System.out.print("Enter the ID of the car you'd like to update: ");
		
		try {
			selection = Integer.parseInt(userInput.nextLine());
		} catch (NumberFormatException e) {
			System.out.println();
			System.out.println("Invalid entry. Returning to Car Menu.");
			return;
		}
		
		carToUpdate = Model.queryCarById(selection);
		
		if (carToUpdate == null) {
			System.out.println();
			System.out.println("The ID you entered is invalid. Returning to Car Menu.");
			return;
		}
		
		System.out.println();
		System.out.println("Updating information for "+carToUpdate.getOwnerId().getFullName()
				+"'s "+carToUpdate.getYear()+" "+carToUpdate.getMake()+" "+carToUpdate.getModel());
		System.out.println("To leave the information the same, leave the field blank and press enter.");
		
		System.out.print("Year: ");
		String year = userInput.nextLine();
		System.out.print("Make: ");
		String make = userInput.nextLine();
		System.out.print("Model: ");
		String model = userInput.nextLine();
		System.out.print("Color: ");
		String color = userInput.nextLine();
		
		if(!year.isEmpty()) {
			carToUpdate.setYear(year);
		}
		if(!make.isEmpty()) {
			carToUpdate.setMake(make);
		}
		if(!model.isEmpty()) {
			carToUpdate.setModel(model);
		}
		if(!color.isEmpty()) {
			carToUpdate.setColor(color);
		}
		
		System.out.println("Updating vehicle information.");
		Model.mergeCarInformation(carToUpdate);
		
		System.out.println("Information successfully updated! Returning to Car Menu.");
	}
	
	static void removeCar () {
		Integer selection = null;
		String confirmation = null;
		Car carToRemove;
		
		listAllCars();
		System.out.println("Removing a car will also remove any maintenance records associated with it.");
		System.out.println("WARNING: This action cannot be undone!");
		System.out.print("Enter the ID of the car you'd like to remove: ");
		
		try {
			selection = Integer.parseInt(userInput.nextLine());
		} catch (NumberFormatException e) {
			System.out.println();
			System.out.println("Invalid Entry. Returning to Car Menu.");
			return;
		}
		
		carToRemove = Model.queryCarById(selection);
		
		if (carToRemove == null) {
			System.out.println();
			System.out.println("The ID you entered is invalid. Returning to Car Menu.");
			return;
		}
		
		System.out.print("Are you sure you want to remove "+carToRemove.getOwnerId().getFullName()
				+"'s "+carToRemove.getMake()+" "+carToRemove.getModel()+"from the database? (y/n): ");
		
		confirmation = userInput.nextLine();
		
		if (confirmation.equalsIgnoreCase("Y")) {
			System.out.println("Removing "+carToRemove.getOwnerId().getFullName()+"'s "
					+carToRemove.getMake()+" "+carToRemove.getModel());
			Model.deleteCarFromDatabase(carToRemove);
			System.out.println("Car successfully deleted!");
		} else if (confirmation.equalsIgnoreCase("N")) {
			System.out.println(carToRemove.getOwnerId().getFullName()+"'s "
					+carToRemove.getMake()+" "+carToRemove.getModel()+" not removed from database.");
		}
	}
	
}
