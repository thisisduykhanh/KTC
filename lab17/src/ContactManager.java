import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ContactManager {
    private List<Contact> contacts;
    private int nextId;

    public ContactManager() {
        contacts = new ArrayList<>();
        nextId = 1;
    }

    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Danh sach lien lac rong.");
            return;
        }

        contacts.forEach(System.out::println);
    }

    public void searchContactById() {
        System.out.print("Nhap so dien thoai lien lac can tim: ");
        String phone = new Scanner(System.in).nextLine();

        Optional<Contact> foundContact = contacts.stream()
                .filter(contact -> contact.phone().equals(phone))
                .findFirst();

        System.out.println(foundContact.map(Object::toString)
                .orElse("Khong tim thay lien lac voi so dien thoai: " + phone));

    }

    public void addNewContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ten lien lac: ");
        String name = scanner.nextLine();
        System.out.print("Nhap so dien thoai: ");
        String phone = scanner.nextLine();
        System.out.print("Nhap email: ");
        String email = scanner.nextLine();
        System.out.print("Nhap dia chi: ");
        String address = scanner.nextLine();

        try {
            Contact contact = new Contact(nextId++, name, phone, email, address);
            contacts.add(contact);
            System.out.println("Da them lien lac moi: " + contact);
        } catch (IllegalArgumentException e) {
            System.out.println("Loi: " + e.getMessage());
        }

    }

    public void updateContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap id lien lac can sua: ");
        int id = scanner.nextInt();

        contacts.stream()
                .filter(contact -> contact.id() == id)
                .findFirst()
                .ifPresentOrElse(contact -> {
                    scanner.nextLine();
                    System.out.print("Nhap ten moi: ");
                    String name = scanner.nextLine();
                    name = name.isBlank() ? contact.name() : name;
                    System.out.print("Nhap so dien thoai moi: ");
                    String phone = scanner.nextLine();
                    phone = phone.isBlank() ? contact.phone() : phone;
                    System.out.print("Nhap email moi: ");
                    String email = scanner.nextLine();
                    email = email.isBlank() ? contact.email() : email;
                    System.out.print("Nhap dia chi moi: ");
                    String address = scanner.nextLine();
                    address = address.isBlank() ? contact.address() : address;

                    try {
                        Contact updatedContact = new Contact(id, name, phone, email, address);
                        contacts.replaceAll(nullContact -> nullContact.id() == id ? updatedContact : nullContact);
                        System.out.println("Da cap nhat lien lac: " + updatedContact);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Loi: " + e.getMessage());
                    }
                }, () -> System.out.println("Khong tim thay lien lac voi id: " + id));

    }

    public void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap id lien lac can xoa: ");
        int id = scanner.nextInt();

        Optional<Contact> contactToDelete = contacts.stream()
                .filter(contact -> contact.id() == id)
                .findFirst();

        if (contactToDelete.isPresent()) {
            contacts.remove(contactToDelete.get());
            System.out.println("Da xoa lien lac: " + contactToDelete.get());
        } else {
            System.out.println("Khong tim thay lien lac voi id: " + id);
        }
    }

}
