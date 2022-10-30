package me.savag3.dotjava.structure;

import me.savag3.dotjava.ObjectWrapper;
import me.savag3.dotjava.source.Source;

/**
 * @author Savag3life
 * @since 1.0
 */
public record SourceAnnotation(String name, String packageName, ObjectWrapper internalValueType, String internalValue) implements Source {

    public StringBuilder asSource() {
        return new StringBuilder(String.format("@%s(\"%s\")", name, internalValueType.wrap(internalValue)));
    }

}
