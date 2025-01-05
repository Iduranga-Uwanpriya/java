import java.io.OptionalDataException;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Plane_mangemet {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("Welcome to the Plane Management application");
        System.out.println("===========================================");
        System.out.println();
        System.out.println("***********");
        System.out.println("Seat System");
        System.out.println("***********");
        System.out.println();
        System.out.println("   1 2 3 4 5 6 7 8 9 10 11 12 13 14");
        System.out.println("A  0 0 0 0 0 0 0 0 0  0  0  0  0  0");
        System.out.println("B  0 0 0 0 0 0 0 0 0  0  0  0  ");
        System.out.println("C  0 0 0 0 0 0 0 0 0  0  0  0  ");
        System.out.println("D  0 0 0 0 0 0 0 0 0  0  0  0  0  0");
        System.out.println();
        System.out.println("Prices = 1-4=200$");
        System.out.println("Prices = 6-9=150$");
        System.out.println("Prices = 10-14=180$");
        int[][] plane_seats = new int[4][];
        plane_seats[0] = new int[14];
        plane_seats[1] = new int[12];// declaring array with seats
        plane_seats[2] = new int[12];
        plane_seats[3] = new int[14];


        Scanner input = new Scanner(System.in);
        int selction;
        while (true) {
            try {

                System.out.println();
                System.out.println("********************************************");
                System.out.println("*                Menu Options              *");
                System.out.println("********************************************");
                System.out.println();
                System.out.println("Please select a Option");
                System.out.println();
                System.out.println("1) Buy a seat");
                System.out.println("2) Cancel a seat");
                System.out.println("3) Find first Available seat");
                System.out.println("4) Show Seating Plan");
                System.out.println("5) Print Ticket information and total sales");
                System.out.println("6) Search Ticket");
                System.out.println("0) Quit");

                System.out.println("Please enter your choice");
                System.out.println();
                selction = input.nextInt();
                input.nextLine();


                switch (selction) {
                    case 1:
                        buy_seat(input, plane_seats);
                        break;
                    case 2:
                        cancel_seat(input, plane_seats);
                        break;
                    case 3:
                        find_first_available(input,plane_seats);
                        break;
                    case 4:
                        show_seating(input,plane_seats);
                        break;
                }


            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option");
                input.next();

            }
        }

    }


    public static void buy_seat(Scanner input, int[][] plane_seats) {
        int rowIndex ;

        // Loop until a valid row is entered
        while (true) {
            System.out.println("Enter the row letter (A-D): ");
            String rowInput = input.nextLine().toUpperCase();

            if (rowInput.length() == 1) {
                char rowLetter = rowInput.charAt(0);
                rowIndex = rowLetter - 'A';

                if (rowIndex >= 0 && rowIndex < plane_seats.length) {
                    break; // Valid row entered
                }
            }

            System.out.println("Invalid row. Please enter a letter between A and D.");
        }

        int seatNumber ;

        // Loop until a valid seat number is entered
        while (true) {
            System.out.println("Enter the seat number (1-" + plane_seats[rowIndex].length + "): ");
            try {
                seatNumber = input.nextInt();
                input.nextLine(); // Clear the leftover newline from the buffer

                if (seatNumber >= 1 && seatNumber <= plane_seats[rowIndex].length) {
                    break; // Valid seat number entered
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                input.nextLine(); // Clear the invalid input
            }

            System.out.println("Invalid seat number. Please enter a number within the valid range.");
        }

        // Check seat availability and mark as sold
        if (plane_seats[rowIndex][seatNumber - 1] == 1) {
            System.out.println("Sorry, seat " + (char) (rowIndex + 'A') + seatNumber + " is already sold.");
        } else {
            plane_seats[rowIndex][seatNumber - 1] = 1;
            System.out.println("Seat " + (char) (rowIndex + 'A') + seatNumber + " has been successfully sold.");
        }
    }

    public static void cancel_seat(Scanner input, int[][] plane_seats) {
        while (true) {
            System.out.println("Enter the row letter (A-D): ");
            char letter = input.nextLine().toUpperCase().charAt(0);
            int rowIndex = letter - 'A';

            // Validate row input
            if (rowIndex < 0 || rowIndex >= plane_seats.length) {
                System.out.println("Invalid row. Please enter a valid Row Letter.");
                continue; // Go back to prompt user again
            }

            System.out.println("Enter the seat number (1-" + plane_seats[rowIndex].length + "): ");
            int seatNumber;
            try {
                seatNumber = input.nextInt();
                input.nextLine(); // Clear the buffer

                // Validate seat number input
                if (seatNumber < 1 || seatNumber > plane_seats[rowIndex].length) {
                    System.out.println("Invalid seat number. Please enter a valid seat number.");
                    continue; // Go back to prompt user again
                }

                // Check if the seat is booked
                if (plane_seats[rowIndex][seatNumber - 1] == 1) {
                    plane_seats[rowIndex][seatNumber - 1] = 0; // Delete the booking
                    System.out.println("Seat " + letter + seatNumber + " has been successfully canceled.");
                    break; // Exit the loop after successful cancellation
                } else {
                    System.out.println("Seat " + letter + seatNumber + " is not booked.");
                }break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                input.nextLine(); // Clear invalid input
            }
        }
    }
    public static void find_first_available(Scanner input, int[][] plane_seats) {
        for (int i = 0; i < plane_seats.length; i++) {
            for (int j = 0; j < plane_seats[i].length; j++) {
                if (plane_seats[i][j] == 0) {
                    System.out.println("First available seat is " + (char) (i + 'A') + (j + 1));
                    return;
                }
            }
        }
        System.out.println("All seats are reserved.");
    }
    public static void show_seating(Scanner input, int[][] plane_seats) {
        System.out.println("Seating Plan :");
        for (int i = 0; i < plane_seats.length; i++) {
            System.out.print((char) (i + 'A') + " ");
            for (int j = 0; j < plane_seats[i].length; j++) {
                // Use 'O' for available (0) and 'X' for reserved (1)
                System.out.print((plane_seats[i][j] == 0 ? "O" : "X") + " ");
            }
            System.out.println();
        }
    }


}




