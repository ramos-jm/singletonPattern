package SoftEng1.singletonPattern;
import java.util.*;
public class QueueManagementSystem {
    private static QueueManagementSystem instance;
    private List<HelpDeskStation> helpDeskStations;

    // Private constructor to prevent instantiation outside the class
    private QueueManagementSystem() {
        helpDeskStations = new ArrayList<>();
    }

    // Get queuing system instance
    public static synchronized QueueManagementSystem getInstance() {
        if (instance == null)
            instance = new QueueManagementSystem();
        return instance;
    }

    // Reset the queue number based on input for a specific Help Desk Station
    public synchronized void resetQueueNumber(HelpDeskStation helpDesk, int newQueueNumber) {
        int index = helpDeskStations.indexOf(helpDesk);
        System.out.println("---Reset Queue Number---");
        if (index != -1) {
            helpDeskStations.get(index).setQueueNumber(newQueueNumber);
            System.out.println("Queue number for " + helpDesk.getName() + " reset to: " + newQueueNumber);
            System.out.println();
        }
    }

    // Get the current queue number for a specific Help Desk Station
    public synchronized int getCurrentQueueNumber(HelpDeskStation helpDesk) {
        int index = helpDeskStations.indexOf(helpDesk);
        return (index != -1) ? helpDeskStations.get(index).getQueueNumber() : 0;
    }

    // Generate a new queue number for a specific Help Desk Station
    public synchronized int generateQueueNumber(HelpDeskStation helpDesk) {
        int index = helpDeskStations.indexOf(helpDesk);
        if (index != -1) {
            int currentQueueNumber = helpDeskStations.get(index).getQueueNumber() + 1;
            helpDeskStations.get(index).setQueueNumber(currentQueueNumber);
            return currentQueueNumber;
        }
        return 0;
    }

    // Register a help desk station with an initial queue number
    public synchronized void registerHelpDesk(HelpDeskStation helpDesk, int initialQueueNumber) {
        helpDesk.setQueueNumber(initialQueueNumber);
        helpDeskStations.add(helpDesk);
    }

    // Get all registered help desk stations
    public List<HelpDeskStation> getHelpDesks() {
        return helpDeskStations;
    }
}
