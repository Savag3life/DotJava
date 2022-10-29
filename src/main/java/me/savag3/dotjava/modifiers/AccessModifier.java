package me.savag3.dotjava.modifiers;

import lombok.Getter;

/**
 * @author Savag3life
 * @since 1.0
 */
public enum AccessModifier implements Modifier {

    PUBLIC("public"),
    PROTECTED("protected"),
    PRIVATE("private"),
    DEFAULT("")

    ;

    @Getter private final String modifier;

    AccessModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public StringBuilder asSource() {
        return new StringBuilder(this.modifier);
    }
}
