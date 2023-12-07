package SoftEng1.singletonPattern;

public class OnlineMonitoringSystem {
    private QueueManagementSystem queuingSystem;

    public OnlineMonitoringSystem(QueueManagementSystem queuingSystem) {
        this.queuingSystem = queuingSystem;
    }

    // Display the real-time queue for each Help Desk
    public void displayRealTimeQueue() {
        System.out.println("---Real-time Queue Monitoring---");
        for (HelpDeskStation helpDesk : queuingSystem.getHelpDesks()) {
            int currentQueueNumber = queuingSystem.getCurrentQueueNumber(helpDesk);
            System.out.println(helpDesk.getName() + ": " + currentQueueNumber);
        }
    }
}
