package se.omegapoint.conferre.event;

import se.omegapoint.conferre.Identity;

import java.time.LocalDateTime;

public final class Event {

    private String type;
    private final Object data;
    private final LocalDateTime timestamp;

    public Event(String type, Object data) {
        this.type = type;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
