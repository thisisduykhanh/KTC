import java.util.Scanner;

public class App {
    private ContactManager contactManager;
    private Scanner scanner;

    public App() {
        contactManager = new ContactManager();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.print(
                "CHUONG TRINH QUAN LY DANH BA\n1. Hien thi danh sach lien lac\n2. Tim kiem lien lac theo ma lien lac\n3. Them moi lien lac\n4. Sua thong tin lien lac\n5. Xoa thong tin lien lac\nMoi ban chon chuc nang [1->5] hoac nhan phim khac de thoat: ");
    }

    public void handleUserChoice(String choice) {
        switch (choice) {
            case "1":
                contactManager.displayAllContacts();
                break;
            case "2":
                contactManager.searchContactById();
                break;
            case "3":
                contactManager.addNewContact();
                break;
            case "4":
                contactManager.updateContact();
                break;
            case "5":
                contactManager.deleteContact();
                break;
            default:
                System.out.println("Cam on ban da su dung chuong trinh!");
                System.exit(0);
        }
    }

    public void run() {
        while (true) {
            displayMenu();
            String choice;
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine();
            } else {
                System.out.println("Khong co du lieu nhap vao. Thoat chuong trinh.");
                break;
            }
            handleUserChoice(choice);

            System.out.println("==============================");
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}