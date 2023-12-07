package SoftEng1.singletonPattern;

public class HelpDeskStation {
    private String name;
    private QueueManagementSystem queuingSystem;
    private int queueNumber;

    public HelpDeskStation(String name, QueueManagementSystem queuingSystem) {
        this.name = name;
        this.queuingSystem = queuingSystem;
        this.queueNumber = 0;
    }

    // Reset the queue number based on an inputted number
    public void resetQueue(int newQueueNumber) {
        queuingSystem.resetQueueNumber(this, newQueueNumber);
    }

    // Generate a new queue number
    public int generateQueueNumber() {
        return queuingSystem.generateQueueNumber(this);
    }

    // Get the current queue number
    public int getCurrentQueueNumber() {
        return queuingSystem.getCurrentQueueNumber(this);
    }

    public String getName() {
        return name;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }
}
