package se.omegapoint.conferre.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private final Map<String, EventTopic> topics = new HashMap<>();

    public EventBus() {
        topics.put("registration", new EventTopic());
    }

    public void publish(String topicName, Event event) {
        topics.get(topicName).publish(event);
        System.out.println("topic: " + topicName + " got " + event);
    }

    public List<Event> list(String topicName) {
        return topics.get(topicName).list();
    }
}
