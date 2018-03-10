package se.omegapoint.conferre.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private final Map<TopicName, EventTopic> topics = new HashMap<>();
    private final Map<TopicName, List<EventListener>> topicListeners = new HashMap<>();

    public EventBus() {
        TopicName.asList().forEach(topicName -> topics.put(topicName, new EventTopic()));
    }

    public void publish(TopicName topicName, Event event) {
        topics.get(topicName).publish(event);
        List<EventListener> eventListener = topicListeners.get(topicName);
        if (eventListener != null) {
            eventListener.forEach(l -> l.onNext(event));
        }
        System.out.println("topic: " + topicName + " got " + event);
    }

    public List<Event> list(TopicName topicName) {
        return topics.get(topicName).list();
    }

    public void registerListener(EventListener eventListener) {
        List<EventListener> listeners = topicListeners.computeIfAbsent(eventListener.getTopicName(), k -> new ArrayList<>());
        listeners.add(eventListener);
    }
}
