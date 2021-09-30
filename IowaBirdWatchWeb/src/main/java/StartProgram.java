import java.util.Scanner;
import java.util.List;
import controller.BirdEntryHelper;
import model.BirdEntry;

/**
 * Iowa Bird Watch Program
 * This program demonstrates the Java Persistence API which is used to 
 * map, store, update and retrieve data from a database to Java Objects.
 * This program is connected to an AWS database
 * @author Amy Miles - almiles
 * CIS 175 - Fall 2021
 * Sep 15, 2021
 */
public class StartProgram {
	
	/**
	 * global variables for StartProgram
	 */
	static Scanner in = new Scanner(System.in);
	static BirdEntryHelper beh = new BirdEntryHelper();	
	
	
	public static void main(String[] args) {
		
		runMenu();

	}
	
	/**
	 * this method prints to the console to get input from the user to interact with the database
	 */
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to Birds of Iowa ---\nEnter the county and the bird you observed!");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a sighting");
			System.out.println("*  2 -- Edit a sighting");
			System.out.println("*  3 -- Delete a sighting");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addSighting();
			} else if (selection == 2) {
				editSighting();
			} else if (selection == 3) {
				deleteSighting();
			} else if (selection == 4) {
				viewTheList();
			} else {
				beh.cleanUp();
				System.out.println("   Tweetle_doo!   ");
				goAgain = false;
			}

		}

	}

	
	/**
	 * this method adds a sighting to the database
	 * it calls the 
	 */
	private static void addSighting() {		
		System.out.print("Enter a county: ");
		String county = in.nextLine();
		System.out.print("Enter a bird: ");
		String bird = in.nextLine();
		BirdEntry toAdd = new BirdEntry(county, bird);
		beh.insertItem(toAdd);

	}
	
	/**
	 * this method deletes a sighting from the database
	 */
	private static void deleteSighting() {
		// TODO Auto-generated method stub
		System.out.print("Enter the county to delete: ");
		String county = in.nextLine();
		System.out.print("Enter the bird to delete: ");
		String bird = in.nextLine();
		BirdEntry toDelete = new BirdEntry(county, bird);
		beh.deleteSighting(toDelete);

	}
	
	/**
	 * This method edits a sighting from the database
	 */
	private static void editSighting() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by county");
		System.out.println("2 : Search by bird");
		int searchBy = in.nextInt();
		in.nextLine();
		List<BirdEntry> foundSightings;
		if (searchBy == 1) {
			System.out.print("Enter the county name: ");
			String countyName = in.nextLine();
			foundSightings = beh.searchForItemByCounty(countyName);
			
		} else {
			System.out.print("Enter the bird: ");
			String birdName = in.nextLine();
			foundSightings = beh.searchForItemByBird(birdName);

		}

		if (!foundSightings.isEmpty()) {
			System.out.println("Found Results.");
			for (BirdEntry l : foundSightings) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			BirdEntry toEdit = beh.searchForSightingById(idToEdit);
			System.out.println("Retrieved " + toEdit.getBird() + " from " + toEdit.getCounty());
			System.out.println("1 : Update county");
			System.out.println("2 : Update bird");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New county: ");
				String newCounty = in.nextLine();
				toEdit.setCounty(newCounty);
			} else if (update == 2) {
				System.out.print("New bird: ");
				String newBird = in.nextLine();
				toEdit.setBird(newBird);
			}

			beh.updateSighting(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}
	
	/**
	 * this method prints the contents of the database to the console
	 */
	private static void viewTheList() {
		List<BirdEntry> allSightings = beh.showAllSightings();
		for(BirdEntry entry: allSightings) {
			System.out.println(entry.returnBirdDetails());
		}
		

	}

}
