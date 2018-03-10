package se.omegapoint.conferre.event;

public interface EventListener {

    void onNext(Event event);

    TopicName getTopicName();
}
