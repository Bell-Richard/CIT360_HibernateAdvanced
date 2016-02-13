
package hibernate.working;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class OwnerManagement {

	static Scanner userInput = new Scanner(System.in);
	
	/*
	 * Owner management methods
	 */
	
	/*
	 * addOwner method
	 * This method gathers the information for a new owner object
	 * and then passes it to the insertOwner method for processing.
	 */
	static void addOwner() {
		String firstName;
		String lastName;
		String gender;
		
		System.out.println();
		System.out.print("First Name: ");
		firstName = userInput.nextLine();
		System.out.print("Last Name: ");
		lastName = userInput.nextLine();
		System.out.print("Gender: ");
		gender = userInput.nextLine();
		
		System.out.println("Adding "+firstName+" "+lastName+" to the database.");
		Owner ownerToInsert = new Owner(firstName,lastName,gender);
		Model.insertOwner(ownerToInsert);
		
		System.out.println(firstName+" "+lastName+" successfully added!");
		
	}
	
	/* 
	 * listOwners method
	 * This method calls the queryAllOwners method to gather
	 * owner information from the database.
	 * A table is then printed and displayed to the user.
	 */
	static void listOwners() {
		List<Owner> ownerList = Model.queryAllOwners();
		
		System.out.println();
		System.out.println("Owners in Database");
		System.out.printf("%-5s%-16s%-16s%-20s\n","ID","First Name","Last Name","Gender");
		System.out.printf("%-5s%-16s%-16s%-20s\n","--","----------","---------","------");
		
		Iterator<Owner> ownerIterator = ownerList.iterator();
		
		while(ownerIterator.hasNext()) {
			
			Owner element = ownerIterator.next();
			System.out.printf("%-5s%-16s%-16s%-20s\n",element.getId(),element.getFirstName(),
					element.getLastName(),element.getGender());
			
		}
		
	}
	
	/*
	 * updateOwnerInformation
	 * This method calls the listOwners method to display
	 * a list of owners in the database. The user is then
	 * prompted to enter the ID of the owner they'd like to
	 * update. The database is then queried for that specific
	 * user (queryOwnerByID method). The user is prompted
	 * to update the owner information and the object is passed
	 * to the mergeOwnerInformation method for processing. 
	 */
	static void updateOwnerInformation() {
		Integer selection = null;
		Owner ownerToUpdate;
		
		listOwners();
		System.out.print("Enter the ID of the owner you'd like to update: ");
		
		try {
			selection = Integer.parseInt(userInput.nextLine());
		} catch (NumberFormatException e) {
			System.out.println();
			System.out.println("Invalid entry. Returning to Owner Menu.");
			return;
		}
		
		ownerToUpdate = Model.queryOwnerById(selection);
		
		if (ownerToUpdate == null) {
			System.out.println();
			System.out.println("The ID you entered is invalid. Returning to Owner Menu.");
			return;
		}
		
		System.out.println();
		System.out.println("Updating "+ownerToUpdate.getFullName()+"'s information.");
		System.out.println("To leave the information the same, leave the field blank and press enter.");
		
		System.out.print("First Name: ");
		String firstName = userInput.nextLine();
		System.out.print("Last Name: ");
		String lastName = userInput.nextLine();
		System.out.print("Gender: ");
		String gender = userInput.nextLine();
		
		if(!firstName.isEmpty()) {
			ownerToUpdate.setFirstName(firstName);
		}
		if(!lastName.isEmpty()) {
			ownerToUpdate.setLastName(lastName);
		}
		if(!gender.isEmpty()) {
			ownerToUpdate.setGender(gender);
		}
		
		System.out.println("Updating information for "+ownerToUpdate.getFullName());
		Model.mergeOwnerInformation(ownerToUpdate);
		
		System.out.println("Information successfully updated! Returning to Owner Menu.");
		
	}
	
	/*
	 * listOwnerCars
	 * This method starts by listing the owners in the database.
	 * The user is then prompted to select one of the owners,
	 * and the method lists all cars owned by that owner.
	 */
	static void listOwnerCars() {
		Integer selection = null;
		Owner theOwner;
		
		listOwners();
		System.out.print("Enter the ID of the owner: ");
		
		try {
			selection = Integer.parseInt(userInput.nextLine());
		} catch (NumberFormatException e) {
			System.out.println();
			System.out.println("Invalid Entry. Returning to previous menu.");
			return;
		}
		
		theOwner = Model.queryOwnerById(selection);
		System.out.println("Retrieving "+theOwner.getFullName()+"'s cars.");
		
		List<Car> listOfCars = Model.queryCarsByOwner(theOwner);
		
		System.out.println();
		System.out.println("Cars in Database");
		System.out.printf("%-5s%-20s%-7s%-16s%-18s%-16s\n","ID","Owner","Year","Make","Model","Color");
		System.out.printf("%-5s%-20s%-7s%-16s%-18s%-16s\n","--","-----","----","----","-----","-----");
		
		Iterator<Car> carIterator = listOfCars.iterator();
		
		while(carIterator.hasNext()) {
			
			Car element = carIterator.next();
			System.out.printf("%-5s%-20s%-7s%-18s%-16s%-16s\n",element.getId(),element.getOwnerId().getFullName(),
					element.getYear(),element.getMake(),element.getModel(),element.getColor());
		}
		
		
	}
	
	/*
	 * removeOwner
	 * This method removes an owner from the database.
	 * We start by listing the owners in the database
	 * and prompting the user to select one of the owners.
	 * The user is asked for confirmation before deleting
	 * an owner, as the action can't be undone.
	 */
	static void removeOwner() {
		Integer selection = null;
		String confirmation = null;
		Owner ownerToRemove;
		
		listOwners();
		System.out.println("Removing an owner will also remove all their cars.");
		System.out.println("WARNING: This action cannot be undone!");
		System.out.print("Enter the ID of the owner you'd like to remove: ");
		
		try {
			selection = Integer.parseInt(userInput.nextLine());
		} catch (NumberFormatException e) {
			System.out.println();
			System.out.println("Invalid Entry. Returning to Owner Menu.");
			return;
		}
		
		ownerToRemove = Model.queryOwnerById(selection);
		
		if (ownerToRemove == null) {
			System.out.println();
			System.out.println("The ID you entered is invalid. Returning to Owner Menu.");
			return;
		}
		
		System.out.print("Are you sure you want to remove "+ownerToRemove.getFullName()
				+" from the database? (y/n): ");
		
		confirmation = userInput.nextLine();
		
		if (confirmation.equalsIgnoreCase("Y")) {
			System.out.println("Removing owner "+ownerToRemove.getFullName());
			Model.deleteOwnerFromDatabase(ownerToRemove);
			System.out.println("Owner successfully deleted!");
		} else if (confirmation.equalsIgnoreCase("N")) {
			System.out.println(ownerToRemove.getFullName()+" not removed from database.");
		}
		
		System.out.println("Returning to Owner Menu.");
		
	}
}
