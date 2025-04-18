package com.pluralsight;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BookStore {
    private List<Book> inventory;
    private Scanner keyboard;

    public BookStore() {
        inventory = new ArrayList<>();
        keyboard = new Scanner(System.in);
        populateInventory();
    }
    private void populateInventory() {
        for (int i = 1; i <= 20; i++) {
            inventory.add(new Book(i, "ISBN" + (1000 + i), "Book Title" + i));
        }
    }
    public void showHomeScreen() {
        while (true) {
            System.out.println("\nWelcome to the Best Book Store!");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books ");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            String choice = keyboard.nextLine();

            switch (choice) {
                case "1":
                    showAvailableBooks();
                    break;
                case "2":
                    showCheckedOutBooks();
                    break;
                case "3":
                    System.out.println("Goodbye my friend!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void showAvailableBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : inventory) {
            if (!book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() + ", ISBN: " + book.getIsbn() + ", Title: " + book.getTitle());
            }
        }
        System.out.print("\nEnter the ID of the book you want to check out (or X to go back): ");
        String input = keyboard.nextLine();

        if (input.equalsIgnoreCase("X")) {
            return;
        }

        try {
            int bookId = Integer.parseInt(input);
            Book selectedBook = findBookById(bookId);
            if (selectedBook != null && !selectedBook.isCheckedOut()) {
                System.out.print("Enter your name: ");
                String name = keyboard.nextLine();
                selectedBook.checkOut(name);
                System.out.println("Book checked out successfully!");
            } else {
                System.out.println("Book is not available for checkout.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid book ID.");
        }
    }
    private void showCheckedOutBooks() {
        System.out.println("\nChecked Out Books: :");
        for (Book book : inventory) {
            if (!book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() + ", ISBN: " + book.getIsbn() + ", Title: " + book.getTitle() + ", Checked Out To: " + book.getCheckedOutTo());

            }
        }
        System.out.print("\nEnter 'C' to check in a book or 'X' to go back: ");
        String input = keyboard.nextLine();
        if (input.equalsIgnoreCase("C")) {
            checkInBook();
        } else if (input.equalsIgnoreCase("X")) {
            return;
        } else {
            System.out.println("Invalid input.");
        }
    }
    private void checkInBook()  {
        System.out.print("Enter the ID of the book you want to check in: ");
        try {
            int bookId = Integer.parseInt(keyboard.nextLine());
            Book selectedBook = findBookById(bookId);
            if (selectedBook != null && selectedBook.isCheckedOut()) {
                selectedBook.checkIn();
                System.out.println("Book checked is successfully!");
            } else {
                System.out.println("Invalid ID or the Book is not checked out.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid book ID");
        }
    }
    private Book findBookById(int id) {
        for (Book book : inventory) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
