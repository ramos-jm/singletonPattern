package SoftEng1.singletonPattern;


public class CentralizedQueuingSystem {

    private static CentralizedQueuingSystem instance;
    private int currentQueueNumber;
    private int[] helpDeskStations; // Number of individuals in each help desk station[3].
    private boolean resetQueue;

    // Private constructor to prevent instantiation outside the class.
    private CentralizedQueuingSystem() {
        currentQueueNumber = 1; // Initial queue number.
        helpDeskStations = new int[3]; // For three Help Desk Stations.
        resetQueue = false;
    }

    // Get queuing system instance.
    public static synchronized CentralizedQueuingSystem getInstance() {
        if (instance == null)
            instance = new CentralizedQueuingSystem();
        return instance;
    }

    // To get current queue number.
    public synchronized int getCurrentQueueNumber() {
        return currentQueueNumber;
    }

    // To issue a new queue number for individual visits each Help Desk Station.
    public synchronized void distributeQueueNumber(int helpDeskStation) {
        if (!resetQueue) {
            System.out.println("Individual Visit for Help Desk Station #"+helpDeskStation+" with Designated Queue Number: " + currentQueueNumber);
            currentQueueNumber++;
            helpDeskStations[helpDeskStation - 1]++; // Adjust index to match array.
        } else {
            System.out.println("Reset in progress. Cannot issue new queue number.");
        }
    }

    // To display the number of individuals in each Help Desk Station.
    public synchronized void displayVisitors() {
        System.out.println("\n-------- Current List of Individual Visitors --------");
        for (int i = 0; i < helpDeskStations.length; i++) {
            System.out.println("Help Desk Station " + (i + 1) + ": " + helpDeskStations[i] + " Individuals");
        }
    }

    // To reset the queuing number based on an inputted number.
    public synchronized void resetQueuingSystem(int helpDeskStation, int newQueueNumber) {
        resetQueue = true;
        currentQueueNumber = newQueueNumber;
        helpDeskStations[helpDeskStation - 1] = 0; // To reset individuals count for the specified help desk station.
        System.out.println("\n-------- Help Desk Status --------");
        System.out.println("Queuing System has been Reset for Help Desk Station " + helpDeskStation + "!!");
        System.out.println("Latest Queuing Number: " + newQueueNumber);
        resetQueue = false;
    }



}
