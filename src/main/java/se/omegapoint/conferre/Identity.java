package se.omegapoint.conferre;

import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.UUID;

public class Identity extends ValueObject {

    private final UUID uuid;

    private Identity(@NonNull UUID uuid) {
        Objects.requireNonNull(uuid, "Identity uuid can't be null");

        this.uuid = uuid;
    }

    public static Identity of(String uuidString) {
        return of(UUID.fromString(uuidString));
    }

    public static Identity of(UUID uuid) {
        return new Identity(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }
}
