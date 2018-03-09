package se.omegapoint.conferre.event;

import org.springframework.beans.factory.annotation.Autowired;

public interface EventListener {

    void onNext(Event event);

    String getTopicName();
}
