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
                """
                        CHUONG TRINH QUAN LY DANH BA
                        1. Hien thi danh sach lien lac
                        2. Tim kiem lien lac theo so dien thoai
                        3. Them moi lien lac
                        4. Sua thong tin lien lac
                        5. Xoa thong tin lien lac
                        Moi ban chon chuc nang [1->5] hoac nhan phim khac de thoat: """);
    }

    public void handleUserChoice(String choice) {
        switch (choice) {
            case "1" -> contactManager.displayAllContacts();
            case "2" -> contactManager.searchContactById();
            case "3" -> contactManager.addNewContact();
            case "4" -> contactManager.updateContact();
            case "5" -> contactManager.deleteContact();
            default -> {
                System.out.println("cam on ban da su dung chuong trinh!");
                System.exit(0);
            }
        }
    }

    public void run() {
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            handleUserChoice(choice);
            System.out.println("==============================");
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
        System.out.println("Chuong trinh da ket thuc.");
    }
}