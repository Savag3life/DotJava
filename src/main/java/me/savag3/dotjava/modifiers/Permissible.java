package me.savag3.dotjava.modifiers;

import java.util.Set;

/**
 * @author Savag3life
 * @since 1.0
 */
public interface Permissible {

    Set<Modifier> getModifiers();

    default void withModifier(Modifier modifier) {
        getModifiers().add(modifier);
    }

    default void removeModifier(Modifier modifier) {
        getModifiers().remove(modifier);
    }

    default boolean hasModifier(Modifier modifier) {
        return getModifiers().contains(modifier);
    }
}
