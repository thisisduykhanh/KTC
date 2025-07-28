import java.util.Scanner;

public class App {

    static final double DIEN = 3500;
    static final double TAXI = 10000;

    static double electricBill = 0;
    static double taxiFare = 0;
    static double netSalary = 0;
    static double totalExpenses = 0;
    static double remainingMoney = 0;

    public static void showMenu() {
        System.out.print(
                "1. Tien dien cuoi thang\n" +
                        "2. Tien taxi cuoi thang\n" +
                        "3. Tien luong cuoi thang\n" +
                        "4. Tong thu nhap sau khi chi tieu cuoi thang\n" +
                        "--> Moi ban chon chuc nang [1->4] hoac nhan phim khac de thoat: ");
    }

    public static void calculateElectricBill(Scanner scanner) {
        System.out.println("\nTinh tien dien cuoi thang");
        System.out.print("Nhap so dien tieu thu: ");

        double electricUsage = scanner.nextDouble();
        System.out.println("Don gia dien: " + DIEN + " VND/kWh");
        electricBill = electricUsage * DIEN;
        System.out.println("Tong tien dien cuoi thang: " + electricBill + " VND");
    }

    public static void calculateTaxiBill(Scanner scanner) {
        System.out.println("\nTinh tien taxi cuoi thang");
        System.out.print("Nhap quang duong di taxi (km): ");
        double taxiDistance = scanner.nextDouble();

        System.out.println("Don gia taxi: " + TAXI + " VND/km");

        taxiFare = taxiDistance * TAXI;

        System.out.println("Tong tien taxi cuoi thang: " + taxiFare + " VND");
    }

    public static void calculateSalary(Scanner scanner) {
        System.out.println("\nTinh tien luong cuoi thang");
        System.out.print("Nhap luong (VND): ");

        double salary = scanner.nextDouble();

        netSalary = salary * 0.85;

        System.out.printf("Thue (15%%): %.0f VND\n", salary * 0.15);
        System.out.printf("Luong nhan duoc: %.0f VND\n", salary * 0.85);
    }

    public static void calculateTotalIncome(Scanner scanner) {
        System.out.println("\nTinh tong thu nhap sau khi chi tieu cuoi thang");
        System.out.print("Nhap cac chi phi khac (VND): ");
        double expenses = scanner.nextDouble();

        totalExpenses = electricBill + taxiFare + expenses;
        remainingMoney = netSalary - totalExpenses;
        System.out.printf("Tong thu nhap: %.0f VND\n", netSalary);
        System.out.printf("Tong chi tieu: %.0f VND\n", totalExpenses);
        System.out.printf("So tien con lai: %.0f VND\n", remainingMoney);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        do {
            showMenu();

            if (!scanner.hasNextInt()) {
                System.out.println("Cam on ban da su dung chuong trinh.");
                break;
            }

            int number = scanner.nextInt();

            switch (number) {
                case 1:
                    calculateElectricBill(scanner);
                    break;
                case 2:
                    calculateTaxiBill(scanner);
                    break;
                case 3:
                    calculateSalary(scanner);
                    break;
                case 4:
                    calculateTotalIncome(scanner);
                    break;

                default:
                    System.out.println("Cam on ban da su dung chuong trinh.");
                    System.exit(0);
            }

            System.out.println("----------------------");
        } while (true);

        scanner.close();
    }
}
