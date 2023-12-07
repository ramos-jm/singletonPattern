package SoftEng1.singletonPattern;

import java.util.*;

public class PagIbigApp {
        static CentralizedQueuingSystem centralizedQueuingSystem = CentralizedQueuingSystem.getInstance();
        static Scanner scan = new Scanner(System.in);
        static Random random = new Random();
    public static void main(String[] args) {
        System.out.println("\n" +
                " _    _      _                            _         ______                  _ _     _         _____  __  __ _          _ \n" +
                "| |  | |    | |                          | |        | ___ \\                (_) |   (_)       |  _  |/ _|/ _(_)        | |\n" +
                "| |  | | ___| | ___ ___  _ __ ___   ___  | |_ ___   | |_/ /_ _  __ _ ______ _| |__  _  __ _  | | | | |_| |_ _  ___ ___| |\n" +
                "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  |  __/ _` |/ _` |______| | '_ \\| |/ _` | | | | |  _|  _| |/ __/ _ \\ |\n" +
                "\\  /\\  /  __/ | (_| (_) | | | | | |  __/ | || (_) | | | | (_| | (_| |      | | |_) | | (_| | \\ \\_/ / | | | | | (_|  __/_|\n" +
                " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  \\_|  \\__,_|\\__, |      |_|_.__/|_|\\__, |  \\___/|_| |_| |_|\\___\\___(_)\n" +
                "                                                                __/ |                  __/ |                             \n" +
                "                                                               |___/                  |___/                              \n");

        while (true) {

                Scanner choice = new Scanner(System.in);
                System.out.println("Service offered:");
                System.out.println("1.) Pag-IBIG queueing app ");
                System.out.println("2.) Exit App");
                System.out.print("Enter Preferred Service: ");
                Integer service = choice.nextInt();


                switch (service) {
                    case 1:
                        System.out.println("\n☺------------- Current Status of Help Desk Stations -------------☺");
                        Integer maxVisitors = 10;
                        for (int i = 0; i < maxVisitors; i++) {
                            Integer helpDesk = random.nextInt(3) + 1;
                            centralizedQueuingSystem.distributeQueueNumber(helpDesk);
                        }

                        System.out.println();


                        System.out.println("--- !!REAL TIME!! ---");
                        System.out.println("Current Queued Number: " + centralizedQueuingSystem.getCurrentQueueNumber());
                        centralizedQueuingSystem.displayVisitors();
                        System.out.println();

                        // Prompt the user if there is a problem.
                        System.out.println("Do you want to reset the Queueing service?");
                        System.out.print("Enter your choice (yes = 1 | no = 2): ");
                        Integer option  = scan.nextInt();

                        if (option == 1){
                            queue();
                        }
                        else if (option == 2) {
                            System.out.println("\n---Returning you to Main Menu↩--\n");
                            break;
                        }
                        else{
                            System.out.println("\nWrong Number!! Returning you to Main Menu\n");
                        }

                    case 2:
                        System.out.println("\nExiting Program!!");
                        System.out.println("Goodbye!! Thank you for Using our Office App!!");
                        System.out.println("\n--!!App Shutting Down!!--");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid Number/Character!! Please Try Again!!\n");

                }
            }

        }

        public static void queue (){
            Scanner choice = new Scanner(System.in);
            final int MAX_RESETS = 3;
            Integer helpDeskStation = 1;
            Integer newQueuingNumber = 0;
            for (int resetCount = 1; resetCount <= MAX_RESETS; resetCount++, helpDeskStation++) {
                System.out.print("\nDo you want to reset Help Desk #"+helpDeskStation+" (1 = yes | 2 = no)?: ");
                Integer hds = choice.nextInt();

                if(hds == 1) {
                    System.out.println("\nResetting Queue for Help Desk Station #" + helpDeskStation + "!");
                    System.out.print("Enter preferred new queuing number: ");
                    newQueuingNumber = scan.nextInt();
                    centralizedQueuingSystem.resetQueuingSystem(helpDeskStation, newQueuingNumber);

                } else{}
            }

            System.out.println("\n☺------------- New Status of Help Desk Stations -------------☺");
            Integer maxVisitors = 10;
            for (int i = 0; i < maxVisitors; i++) {
                Integer helpDesk = random.nextInt(3) + 1;
                centralizedQueuingSystem.distributeQueueNumber(helpDesk);
            }
            System.out.println();

            System.out.println("--- !!REAL TIME!! ---");
            System.out.println("Current Queued Number: " + centralizedQueuingSystem.getCurrentQueueNumber());
            centralizedQueuingSystem.displayVisitors();
            System.out.println();
        }
    }
