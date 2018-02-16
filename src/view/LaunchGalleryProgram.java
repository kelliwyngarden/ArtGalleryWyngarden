package view;

import java.util.List;
import java.util.Scanner;

import controller.GalleryHelper;
import model.GalleryPiece;

public class LaunchGalleryProgram {

	static Scanner in = new Scanner(System.in);
	static GalleryHelper gh = new GalleryHelper();

	private static void addAPiece() {
		// TODO Auto-generated method stub
		System.out.print("Enter the title of the artwork: ");
		String title = in.nextLine();
		System.out.print("Enter the artist's name: ");
		String artistName = in.nextLine();
		System.out.print("Enter the medium used to create the artwork: ");
		String media = in.nextLine();
		System.out.print("Enter the year of the piece: ");
		String year = in.nextLine();
		System.out.print("Enter the value of the piece in numerals: $");
		double value = 0;
		if (in.hasNextDouble()) {
			value = in.nextDouble();
		} else {
			in.nextLine();
			System.out.print("Input error. Please enter the value in numerals: $");
			value = in.nextDouble();
		}
		in.nextLine();

		GalleryPiece toAdd = new GalleryPiece(title, artistName, media, year, value);
		gh.insertPiece(toAdd);

	}

	private static void deleteAPiece() {
		// TODO Auto-generated method stub
		System.out.println("List of artwork available to delete:");
		viewTheList();
		System.out.print("Enter ID of artwork to delete: ");
		int artworkId = in.nextInt();
		in.nextLine();

		GalleryPiece toDelete = new GalleryPiece(artworkId);
		gh.deletePiece(toDelete);
		
		System.out.println("Item successfully deleted.");

	}

	private static void editAPiece() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Title");
		System.out.println("2 : Search by Artist");
		int searchBy = in.nextInt();
		in.nextLine();
		List<GalleryPiece> foundPieces;
		if (searchBy == 1) {
			System.out.print("Enter the title of the piece: ");
			String artworkTitle = in.nextLine();
			foundPieces = gh.searchForPieceByTitle(artworkTitle);

		} else {
			System.out.print("Enter the artist's name: ");
			String artist = in.nextLine();
			foundPieces = gh.searchForPiecebyArtist(artist);

		}

		if (!foundPieces.isEmpty()) {
			System.out.println("Found Results.");
			for (GalleryPiece piece : foundPieces) {
				System.out.println(piece.getId() + " : " + piece.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			GalleryPiece toEdit = gh.searchForPieceById(idToEdit);
			System.out.println("Retrieved " + toEdit.getTitle() + " by " + toEdit.getArtistName());
			System.out.println("1 : Update Title");
			System.out.println("2 : Update Artist");
			System.out.println("3 : Update Year");
			System.out.println("4 : Update Value");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Title: ");
				String newTitle = in.nextLine();
				toEdit.setTitle(newTitle);
			} else if (update == 2) {
				System.out.print("New Artist: ");
				String newArtist = in.nextLine();
				toEdit.setArtistName(newArtist);
			} else if (update == 3) {
				System.out.print("New Year: ");
				String newYear = in.nextLine();
				toEdit.setYear(newYear);
			} else if (update == 4) {
				System.out.print("New Value: ");
				double newValue = in.nextDouble();
				in.nextLine();
				toEdit.setValue(newValue);
			}

			gh.updatePiece(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to my art gallery! ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a piece");
			System.out.println("*  2 -- Edit a piece");
			System.out.println("*  3 -- Delete a piece");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAPiece();
			} else if (selection == 2) {
				editAPiece();
			} else if (selection == 3) {
				deleteAPiece();
			} else if (selection == 4) {
				viewTheList();
			} else {
				gh.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<GalleryPiece> allPieces = gh.showAllPieces();
		for (GalleryPiece piece : allPieces) {
			System.out.println(piece.returnArtworkDetails());
		}
	}

}
