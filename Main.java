import com.example.reservation.model.Reservation;
import com.example.reservation.model.User;
import com.example.reservation.service.ReservationService;
import com.example.reservation.service.UserService;
import java.util.Scanner;

public class Main {
    private UserService userService = new UserService(); // Use proper DI if using Spring
    private ReservationService reservationService = new ReservationService(); // Use proper DI if using Spring

    public static void main(String[] args) {
        Main app = new Main();
        app.mainMenu();
    }

    private void mainMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to Online Reservation System");
            System.out.println("Please Login to Continue");

            // Login
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (login(username, password)) {
                boolean continueApp = true;
                while (continueApp) {
                    System.out.println("\nMain Menu:");
                    System.out.println("1. Create Reservation");
                    System.out.println("2. Cancel Reservation");
                    System.out.println("3. Exit");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            createReservation(scanner);
                            break;
                        case 2:
                            cancelReservation(scanner);
                            break;
                        case 3:
                            continueApp = false;
                            System.out.println("Exiting System.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid credentials");
                System.out.println("Exiting System.");
            }
        }
    }

    private boolean login(String username, String password) {
        User user = userService.authenticate(username, password);
        return user != null; // Return true if user is not null (valid credentials), false otherwise
    }

    private void createReservation(Scanner scanner) {
        System.out.println("Enter Train Number: ");
        String trainNumber = scanner.nextLine();
        System.out.println("Enter Train Name: ");
        String trainName = scanner.nextLine();
        System.out.println("Enter Class Type: ");
        String classType = scanner.nextLine();
        System.out.println("Enter Journey Date (YYYY-MM-DD): ");
        String journeyDate = scanner.nextLine();
        System.out.println("Enter From Place: ");
        String fromPlace = scanner.nextLine();
        System.out.println("Enter To Destination: ");
        String toDestination = scanner.nextLine();
        System.out.println("Enter PNR Number: ");
        String pnrNumber = scanner.nextLine();

        Reservation reservation = new Reservation(trainNumber, trainName, classType, journeyDate, fromPlace, toDestination, pnrNumber);
        reservationService.makeReservation(reservation);

        System.out.println("Reservation created successfully.");
    }

    private void cancelReservation(Scanner scanner) {
        System.out.println("Enter PNR Number to cancel: ");
        String pnrNumber = scanner.nextLine();
        reservationService.cancelReservation(pnrNumber);
        System.out.println("Reservation cancelled successfully.");
    }
}
