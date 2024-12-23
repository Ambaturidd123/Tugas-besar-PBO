import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SoccerClubManagement {

    public static void displayCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date()); // Gunakan java.util.Date untuk mendapatkan waktu sekarang
        System.out.println("Current Date: " + currentDate);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                PlayerManager manager = new PlayerManager("jdbc:mysql://localhost:3306/rawang_fc", "root", "");

                while (true) {
                    displayCurrentDate();
                    System.out.println("\nSoccer Club Management:");
                    System.out.println("1. Input Player");
                    System.out.println("2. View Players");
                    System.out.println("3. Update Player");
                    System.out.println("4. Delete Player");
                    System.out.println("5. View Total Salary");
                    System.out.println("6. Exit");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter Age: ");
                            int age = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter Position: ");
                            String position = scanner.nextLine();
                            System.out.print("Enter Asal Negara: ");
                            String asalNegara = scanner.nextLine();
                            System.out.print("Enter Status: ");
                            String status = scanner.nextLine();
                            System.out.print("Enter Grade (A/B/C): ");
                            String grade = scanner.nextLine();
                            double gaji = switch (grade) {
                                case "A" -> 5000;
                                case "B" -> 3000;
                                case "C" -> 2000;
                                default -> 1000;
                            };

                            manager.addPlayer(new Player(id, name, age, position, asalNegara, status, grade, gaji));
                        }
                        case 2 -> manager.viewPlayers();
                        case 3 -> {
                            System.out.print("Enter ID of player to update: ");
                            int id = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter New Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter New Age: ");
                            int age = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter New Position: ");
                            String position = scanner.nextLine();
                            System.out.print("Enter New Asal Negara: ");
                            String asalNegara = scanner.nextLine();
                            System.out.print("Enter New Status: ");
                            String status = scanner.nextLine();
                            System.out.print("Enter New Grade (A/B/C): ");
                            String grade = scanner.nextLine();
                            double gaji = switch (grade) {
                                case "A" -> 5000;
                                case "B" -> 3000;
                                case "C" -> 2000;
                                default -> 1000;
                            };

                            manager.updatePlayer(id, name, age, position, asalNegara, status, grade, gaji);
                        }
                        case 4 -> {
                            System.out.print("Enter ID of player to delete: ");
                            int id = scanner.nextInt();
                            manager.deletePlayer(id);
                        }
                        case 5 -> {
                            double totalSalary = manager.calculateTotalSalary();
                            System.out.println("Total Salary of all players: " + totalSalary);
                        }
                        case 6 -> {
                            System.out.println("Exiting... Goodbye!");
                            return;
                        }
                        default -> System.out.println("Invalid choice, please try again.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Database connection error: " + e.getMessage());
            }
        }
    }
}
