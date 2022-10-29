package me.savag3.dotjava.structure;

import me.savag3.dotjava.ObjectWrapper;

/**
 * @author Savag3life
 * @since 1.0
 */
public record SourceAnnotation(String name, String packageName, ObjectWrapper internalValueType, String internalValue) {

    public String asSource() {
        return String.format("@%s(\"%s\")", name, internalValueType.wrap(internalValue));
    }

}
