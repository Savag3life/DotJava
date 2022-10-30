package me.savag3.dotjava.modifiers;

import me.savag3.dotjava.source.Source;

/**
 * @author Savag3life
 * @since 1.0
 */
public interface Modifier extends Source {

    String getModifier();

    int getPriority();


}
