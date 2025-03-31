import java.io.*;
import java.util.*;

class Contact {
    String name;
    String phoneNumber;
    String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }

    public String toCsvFormat() {
        return name + "," + phoneNumber + "," + email;
    }
}

public class ContactManagement {

    private static final String FILE_NAME = "contacts.csv";
    private static List<Contact> contactList = new ArrayList<>();

    public static void main(String[] args) {
        // Load contacts from the file when the program starts
        loadContactsFromFile();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nContact Manager");
            System.out.println("1. Add New Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addNewContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    editContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    saveContactsToFile();
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addNewContact(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        Contact newContact = new Contact(name, phoneNumber, email);
        contactList.add(newContact);
        System.out.println("Contact added successfully!");
    }

    private static void viewContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            System.out.println("\nContact List:");
            for (Contact contact : contactList) {
                System.out.println(contact);
            }
        }
    }

    private static void editContact(Scanner scanner) {
        System.out.print("Enter the name of the contact to edit: ");
        String nameToEdit = scanner.nextLine();
        Contact contactToEdit = null;

        // Find the contact to edit
        for (Contact contact : contactList) {
            if (contact.name.equalsIgnoreCase(nameToEdit)) {
                contactToEdit = contact;
                break;
            }
        }

        if (contactToEdit != null) {
            System.out.print("Enter new phone number (or press Enter to keep the same): ");
            String newPhoneNumber = scanner.nextLine();
            System.out.print("Enter new email address (or press Enter to keep the same): ");
            String newEmail = scanner.nextLine();

            // Update the contact details if new values are provided
            if (!newPhoneNumber.isEmpty()) {
                contactToEdit.phoneNumber = newPhoneNumber;
            }
            if (!newEmail.isEmpty()) {
                contactToEdit.email = newEmail;
            }

            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter the name of the contact to delete: ");
        String nameToDelete = scanner.nextLine();
        boolean removed = false;

        Iterator<Contact> iterator = contactList.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.name.equalsIgnoreCase(nameToDelete)) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void loadContactsFromFile() {
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 3) {
                        Contact contact = new Contact(data[0], data[1], data[2]);
                        contactList.add(contact);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading contacts from file: " + e.getMessage());
            }
        }
    }

    private static void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contactList) {
                writer.write(contact.toCsvFormat());
                writer.newLine();
            }
            System.out.println("Contacts saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }
}
