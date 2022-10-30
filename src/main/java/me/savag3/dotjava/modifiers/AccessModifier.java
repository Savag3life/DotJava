package me.savag3.dotjava.modifiers;

import lombok.Getter;

/**
 * @author Savag3life
 * @since 1.0
 */
public enum AccessModifier implements Modifier {

    PUBLIC("public", 1 << 1),
    PROTECTED("protected",  1 << 2),
    PRIVATE("private", 1 << 3),

    ;

    @Getter private final String modifier;
    @Getter private final int priority;

    AccessModifier(String modifier, int priority) {
        this.modifier = modifier;
        this.priority = priority;
    }

    @Override
    public StringBuilder asSource() {
        return new StringBuilder(this.modifier);
    }
}
