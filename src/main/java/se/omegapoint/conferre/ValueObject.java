package se.omegapoint.conferre;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class ValueObject {

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(7, 31, this, false, ValueObject.class);
    }

    @Override
    public final boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false, ValueObject.class);
    }
}
