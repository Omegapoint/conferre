package se.omegapoint.conferre.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private final Map<String, EventTopic> topics = new HashMap<>();
    private final Map<String, EventListener> topicListeners = new HashMap<>();

    public EventBus() {
        topics.put("registration", new EventTopic());
        topics.put("registration_external", new EventTopic());
    }

    public void publish(String topicName, Event event) {
        topics.get(topicName).publish(event);
        EventListener eventListener = topicListeners.get(topicName);
        if (eventListener != null) {
            eventListener.onNext(event);
        }
        System.out.println("topic: " + topicName + " got " + event);
    }

    public List<Event> list(String topicName) {
        return topics.get(topicName).list();
    }

    public void registerListener(EventListener eventListener) {
        topicListeners.put(eventListener.getTopicName(), eventListener);
    }
}
