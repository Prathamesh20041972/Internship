import java.util.*;

class Contact {
    String name;
    String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

public class ContactBook {

    static ArrayList<Contact> contacts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Preload 10 contacts
        contacts.add(new Contact("Rahul", "9876543210"));
        contacts.add(new Contact("Amit", "9876543211"));
        contacts.add(new Contact("Sneha", "9876543212"));
        contacts.add(new Contact("Priya", "9876543213"));
        contacts.add(new Contact("Karan", "9876543214"));
        contacts.add(new Contact("Neha", "9876543215"));
        contacts.add(new Contact("Rohit", "9876543216"));
        contacts.add(new Contact("Anjali", "9876543217"));
        contacts.add(new Contact("Vikas", "9876543218"));
        contacts.add(new Contact("Pooja", "9876543219"));

        while (true) {
            System.out.println("\n--- CONTACT BOOK ---");
            System.out.println("1. Display Contacts");
            System.out.println("2. Add Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Search Contact");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();  // clear buffer

            switch (choice) {
                case 1:
                    displayContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    searchContact();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("\n--- Contact List ---");
        for (Contact c : contacts) {
            System.out.println("Name: " + c.name + " | Phone: " + c.phone);
        }
    }

    static void addContact() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        contacts.add(new Contact(name, phone));
        System.out.println("Contact added successfully!");
    }

    static void deleteContact() {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine();

        Iterator<Contact> iterator = contacts.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Contact c = iterator.next();
            if (c.name.equalsIgnoreCase(name)) {
                iterator.remove();
                found = true;
                System.out.println("Contact deleted successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Contact not found!");
        }
    }

    static void searchContact() {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Contact c : contacts) {
            if (c.name.equalsIgnoreCase(name)) {
                System.out.println("Found -> Name: " + c.name + " | Phone: " + c.phone);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Contact not found!");
        }
    }
}
