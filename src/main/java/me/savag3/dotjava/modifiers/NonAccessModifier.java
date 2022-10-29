package me.savag3.dotjava.modifiers;

import lombok.Getter;

/**
 * @author Savag3life
 * @since 1.0
 */
public enum NonAccessModifier implements Modifier {

    STATIC("static"),
    FINAL("final"),
    ABSTRACT("abstract"),
    SYNCHRONIZED("synchronized"),
    TRANSIENT("transient"),
    VOLATILE("volatile"),
    NATIVE("native"),
    STRICTFP("strictfp");

    ;

    @Getter private final String modifier;

    NonAccessModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public StringBuilder asSource() {
        return new StringBuilder(this.modifier);
    }
}
