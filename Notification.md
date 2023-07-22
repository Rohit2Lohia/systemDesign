- ALL:: https://github.com/prasadgujar/low-level-design-primer/blob/master/solutions.md
- CACHE: https://github1s.com/anomaly2104/cache-low-level-system-design/tree/master
- Document DB: https://leetcode.com/discuss/interview-question/1630474/razorpay-machine-coding-round-document-service-design
 _
-| Video Streaming
-| Parking Lot **
-| True Caller
-| Pub-Sub
 -https://github.com/Prakash-Jha-Dev/SystemDesign
Database Internals
Designing a notification system with multiple channels and handling failures requires a robust and fault-tolerant 
architecture. Here's a high-level design for the notification system:

1. **Notification Channels:**
   - Define classes/interfaces for each notification channel (e.g., EmailChannel, SMSChannel, PushNotificationChannel, etc.). 
   Each channel should have a method to send notifications.
   - Implement different notification channels based on their individual requirements and APIs.

2. **Notification Message:**
   - Define a class to represent a notification message, containing information like the recipient, content, type, etc.

3. **Notification Service:**
   - Create a central NotificationService class responsible for managing and sending notifications via available channels.
   - This class should have methods to add channels, select the best channel for sending notifications, and handle failures.

4. **Channel Selection Strategy:**
   - Implement a strategy to select the best channel for sending a notification based on the success rate of each channel.
   - This strategy could be as simple as choosing the channel with the highest success rate or implementing more complex 
   logic based on historical data or real-time monitoring.

5. **Failure Handling:**
   - Define a mechanism to handle notification failures when a channel fails to send a notification.
   - For example, you could have a fallback strategy to try another channel, store the failed notifications in a queue 
   for later retries, or notify administrators about the failure.

6. **Logging and Monitoring:**
   - Implement logging and monitoring to track the success and failure rates of notification deliveries.
   - Monitor the performance of each channel to make informed decisions on channel selection and improvements.

Here's a Java-like pseudocode representation of the design:

```java
// Interface representing a notification channel
interface NotificationChannel {
    void sendNotification(NotificationMessage message);
}

// Class representing a notification message
class NotificationMessage {
    String recipient;
    String content;
    // Other properties as required
}

// Class representing the Notification Service
class NotificationService {
    List<NotificationChannel> channels;

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public void sendNotification(NotificationMessage message) {
        NotificationChannel bestChannel = selectBestChannel();
        try {
            bestChannel.sendNotification(message);
            logSuccess(message);
        } catch (NotificationFailedException ex) {
            handleFailure(message, ex);
        }
    }

    private NotificationChannel selectBestChannel() {
        // Implement logic to select the best channel based on success rates or other criteria
        // For simplicity, let's assume the first channel is the best channel.
        return channels.get(0);
    }

    private void logSuccess(NotificationMessage message) {
        // Implement logging of successful notifications
    }

    private void handleFailure(NotificationMessage message, NotificationFailedException ex) {
        // Implement failure handling, e.g., retry with another channel, notify administrators, etc.
    }
}

// Custom exception to represent a notification failure
class NotificationFailedException extends Exception {
    // Additional properties and methods if required
}
```

Please note that the provided code is a simplified representation of the notification system's design and does not 
include detailed implementations of each channel or handling failures. The actual implementation may vary depending 
on your specific requirements and the complexity of each channel's integration.

Additionally, in a production system, you would need to consider several other aspects, such as security, scalability, 
message templates, handling large numbers of notifications, and ensuring data privacy, among others.