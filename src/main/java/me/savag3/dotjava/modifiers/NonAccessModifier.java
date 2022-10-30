package me.savag3.dotjava.modifiers;

import lombok.Getter;

/**
 * @author Savag3life
 * @since 1.0
 */
public enum NonAccessModifier implements Modifier {


    FINAL("final", 1 << 4), // cannot be static
    ABSTRACT("abstract", 1 << 5), // cannot be static
    SYNCHRONIZED("synchronized", 1 << 6), // cannot be static
    STATIC("static", 1 << 7, FINAL.priority, ABSTRACT.priority, SYNCHRONIZED.priority), // Cannot be final, abstract, or synchronized
    TRANSIENT("transient", 1 << 8), // Field only
    VOLATILE("volatile", 1 << 9), // Field only
    NATIVE("native", 1 << 10, ABSTRACT.priority), // Method only, cannot be abstract
    STRICTFP("strictfp", 1 << 11) // Redundant, but still here

    ;

    @Getter private final String modifier;
    @Getter private final int priority;
    @Getter private final int[] blocked;

    NonAccessModifier(String modifier, int priority, int... blocked) {
        this.modifier = modifier;
        this.priority = priority;
        this.blocked = blocked;
    }

    @Override
    public StringBuilder asSource() {
        return new StringBuilder(this.modifier);
    }
}
