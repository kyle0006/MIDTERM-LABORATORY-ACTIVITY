package Library.hosts;

import Library.business.Book;
import Library.business.Library;
import Library.business.LibraryItem;
import Library.business.Loan;
import Library.business.Magazine;
import Library.business.Member;
import Library.data.SampleData;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final Library library = new Library();

    public static void main(String[] args) { new Main().start(); }

    private void start() {
        SampleData.load(library);
        while (true) {
            System.out.println("\n=== COMMUNITY LIBRARY SYSTEM ===");
            System.out.println("1. View all items");
            System.out.println("2. Search item by ID");
            System.out.println("3. Register member");
            System.out.println("4. View members");
            System.out.println("5. Borrow item");
            System.out.println("6. Return item");
            System.out.println("7. View active loans");
            System.out.println("8. Add library item");
            System.out.println("9. Exit");
            String choice = ask("Choose an option: ");
            try {
                switch (choice) 
                {
                    case "1": viewItems(); break;
                    case "2": searchItem(); break;
                    case "3": registerMember(); break;
                    case "4": viewMembers(); break;
                    case "5": borrowItem(); break;
                    case "6": returnItem(); break;
                    case "7": viewActiveLoans(); break;
                    case "8": addItem(); break;
                    case "9": System.out.println("Goodbye!"); return;
                    default: System.out.println("Invalid menu option. Choose 1-9.");
                }
            } catch (IllegalArgumentException | IllegalStateException error) {
                System.out.println("Error: " + error.getMessage());
            }
        }
    }

    private String ask(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            System.out.println("\nInput ended. Goodbye!");
            System.exit(0);
        }
        return scanner.nextLine().trim();
    }

    private String askRequired(String prompt) {
        String value = ask(prompt);
        if (value.isBlank()) throw new IllegalArgumentException("Input cannot be empty.");
        return value;
    }

    private void viewItems() {
        for (LibraryItem item : library.getItems()) System.out.println(item.getDescription());
    }

    private void searchItem() {
        LibraryItem item = library.findItem(askRequired("Item ID: "));
        System.out.println(item == null ? "Item not found." : item.getDescription());
    }

    private void registerMember() {
        String id = askRequired("Member ID: ");
        String name = askRequired("Member name: ");
        library.registerMember(new Member(id, name));
        System.out.println("Member registered successfully.");
    }

    private void viewMembers() {
        for (Member member : library.getMembers()) {
            System.out.println(member.getMemberId() + " | " + member.getName());
        }
    }

    private void borrowItem() {
        String itemId = askRequired("Item ID: ");
        String memberId = askRequired("Member ID: ");
        Loan loan = library.borrow(itemId, memberId);
        System.out.println("Borrowed successfully. Due date: " + loan.getDueDate());
    }

    private void returnItem() {
        Loan loan = library.returnItem(askRequired("Item ID to return: "));
        System.out.println("Returned successfully: " + loan.getItem().getTitle());
    }

    private void viewActiveLoans() {
        if (library.getActiveLoans().isEmpty()) {
            System.out.println("No active loans.");
            return;
        }
        for (Loan loan : library.getActiveLoans()) {
            System.out.println(loan.getItem().getId() + " | " + loan.getItem().getTitle()
                    + " | " + loan.getMember().getName() + " | Due: " + loan.getDueDate());
        }
    }

    private void addItem() {
        String type = askRequired("Enter B for Book or M for Magazine: ");
        if (!type.equalsIgnoreCase("B") && !type.equalsIgnoreCase("M")) {
            throw new IllegalArgumentException("Choose B or M.");
        }
        String id = askRequired("Item ID: ");
        String title = askRequired("Title: ");
        if (type.equalsIgnoreCase("B")) {
            String author = askRequired("Author: ");
            library.addItem(new Book(id, title, author));
        } else {
            String issueInput = askRequired("Issue number (positive integer): ");
            int issue;
            try { issue = Integer.parseInt(issueInput); }
            catch (NumberFormatException error) { throw new IllegalArgumentException("Issue number must be an integer."); }
            library.addItem(new Magazine(id, title, issue));
        }
        System.out.println("Library item added successfully.");
    }
}
