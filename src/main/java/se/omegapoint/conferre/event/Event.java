package se.omegapoint.conferre.event;

import java.time.LocalDateTime;

public final class Event {

    private String type;
    private final Object payload;
    private final LocalDateTime timestamp;

    public Event(String type, Object payload) {
        this.type = type;
        this.payload = payload;
        this.timestamp = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public Object getPayload() {
        return payload;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", payload=" + payload +
                ", timestamp=" + timestamp +
                '}';
    }
}
