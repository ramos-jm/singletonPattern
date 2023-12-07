package SoftEng1.singletonPattern;
import java.util.*;
public class CentralizedQueuingSystemApp {

    public static void main(String[] args) {

        while(true){
            Scanner userInput = new Scanner(System.in);

            System.out.println("///Centralized Queuing System for Pag-ibig Office///");
            System.out.println();

            // Instantiate the queuing system
            QueueManagementSystem queuingSystem = QueueManagementSystem.getInstance();

            // Create help desk stations
            HelpDeskStation helpDesk1 = new HelpDeskStation("Help Desk Station 1", queuingSystem);
            HelpDeskStation helpDesk2 = new HelpDeskStation("Help Desk Station 2", queuingSystem);
            HelpDeskStation helpDesk3 = new HelpDeskStation("Help Desk Station 3", queuingSystem);

            // Register help desk stations with the queuing system
            queuingSystem.registerHelpDesk(helpDesk1, 0);
            queuingSystem.registerHelpDesk(helpDesk2, 0);
            queuingSystem.registerHelpDesk(helpDesk3, 0);

            // Simulate individuals obtaining queue numbers
            int numIndividuals = 10;
            for (int i = 1; i <= numIndividuals; i++) {
                HelpDeskStation currentHelpDesk = getRandomHelpDesk(helpDesk1, helpDesk2, helpDesk3);
                int currentQueueNumber = currentHelpDesk.generateQueueNumber();
                System.out.println("Individual " + i + " in " + currentHelpDesk.getName() + " Queue Number: " + currentQueueNumber);
            }
            System.out.println();

            // Display the current queue numbers after reset
            displayCurrentQueueNumbers(helpDesk1, helpDesk2, helpDesk3);

            // Reset the queue number based on user input for each Help Desk
            resetQueueWithUserInput(helpDesk1, userInput);
            resetQueueWithUserInput(helpDesk2, userInput);
            resetQueueWithUserInput(helpDesk3, userInput);

            // Simulate an online monitoring system
            OnlineMonitoringSystem monitoringSystem = new OnlineMonitoringSystem(queuingSystem);
            monitoringSystem.displayRealTimeQueue();

            //questions the user to exit the program or not
            String exit = "";
            boolean validChoice2 = false;
            while (!validChoice2) {
                System.out.print("\nDo you still wish to use the program? 1 = yes, 2 = no: ");
                exit = userInput.nextLine();

                switch (exit){
                    case "1":
                    case "2":
                        validChoice2 = true;
                        System.out.println();
                        break;
                    default:
                        System.out.println("Invalid input choice. Please choose between \"1\" or \"2\".");
                }
            }
            if(exit.equals("2")) {
                System.out.println("Thank you for using the program!");
                System.out.println("Exiting the program...");
                // Close the scanner
                userInput.close();
                System.exit(0);
            }
        }
    }

    // Method to display the current queue numbers for each Help Desk
    private static void displayCurrentQueueNumbers(HelpDeskStation... helpDesks) {
        System.out.println("---Current Queue Number---");
        for (HelpDeskStation helpDesk : helpDesks) {
            System.out.println("Current Queue Number for " + helpDesk.getName() + ": " + helpDesk.getCurrentQueueNumber());
        }
        System.out.println();
    }

    // Random generator to choose on help desk stations for each individual
    private static HelpDeskStation getRandomHelpDesk(HelpDeskStation... helpDesks) {
        Random random = new Random();
        int randomIndex = random.nextInt(helpDesks.length);
        return helpDesks[randomIndex];
    }

    // Method to reset the queue number based on user input
    private static void resetQueueWithUserInput(HelpDeskStation helpDesk, Scanner userInput) {
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print(helpDesk.getName() + ", do you want to reset the Queue Number? (yes/no): ");
            String resetChoice = userInput.nextLine().toLowerCase();

            if (resetChoice.equals("yes")) {
                validChoice = true;
                int newQueueNumber = 0;
                boolean validNumber = false;

                while (!validNumber) {
                    try {
                        System.out.print("Queue Number Reset: ");
                        newQueueNumber = Integer.parseInt(userInput.nextLine());
                        System.out.println();
                        validNumber = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer for the queue number.");
                        System.out.println();
                    }
                }

                helpDesk.resetQueue(newQueueNumber);
            } else if (resetChoice.equals("no")) {
                validChoice = true;
                System.out.println("Queue number for " + helpDesk.getName() + " will not be reset.");
                System.out.println();
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                System.out.println();
            }
        }
    }

}
