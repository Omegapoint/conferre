package se.omegapoint.conferre;

import org.springframework.lang.NonNull;

import java.util.Objects;

public abstract class Entity {

    private final Identity id;

    public Entity(@NonNull Identity id) {
        Objects.requireNonNull(id, "Entity id can't be null");

        this.id = id;
    }

    public @NonNull Identity getId() {
        return id;
    }

    @Override
    public final boolean equals(Object o) {
        return o != null && o instanceof Entity && id.equals(((Entity) o).id);
    }

    @Override
    public final int hashCode() {
        return id.hashCode();
    }
}
