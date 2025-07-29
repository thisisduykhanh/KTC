import java.util.ArrayList;
import java.util.List;
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
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void searchContactById() {
        System.out.print("Nhap id lien lac can tim: ");
        int id = new Scanner(System.in).nextInt();
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                System.out.println(contact);
                return;
            }
        }
        System.out.println("Khong tim thay lien lac voi ma: " + id);

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

        Contact contact = new Contact(nextId++, name, phone, email, address);
        contacts.add(contact);
        System.out.println("Da them lien lac moi: " + contact);

    }

    public void updateContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap id lien lac can sua: ");
        int id = scanner.nextInt();
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                scanner.nextLine();
                System.out.print("Nhap ten moi: ");
                contact.setName(scanner.nextLine());
                System.out.print("Nhap so dien thoai moi: ");
                contact.setPhone(scanner.nextLine());
                System.out.print("Nhap email moi: ");
                contact.setEmail(scanner.nextLine());
                System.out.print("Nhap dia chi moi: ");
                contact.setAddress(scanner.nextLine());
                System.out.println("Da cap nhat lien lac: " + contact);

                return;
            }
        }
        System.out.println("Khong tim thay lien lac voi ma: " + id);
    }

    public void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap id lien lac can xoa: ");
        int id = scanner.nextInt();
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                contacts.remove(contact);
                System.out.println("Da xoa lien lac: " + contact);
                return;
            }
        }
        System.out.println("Khong tim thay lien lac voi id: " + id);
    }

}
