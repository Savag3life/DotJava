package me.savag3.dotjava;

import me.savag3.dotjava.modifiers.Modifier;
import me.savag3.dotjava.source.Source;

import java.util.HashSet;

/**
 * @author Savag3life
 * @since 1.0
 */
public class ComponentSet<T extends Source> extends HashSet<T> {

    public StringBuilder compile() {
        StringBuilder builder = new StringBuilder();

        for (T t : this) {
            if (t != null) {
                builder.append(t.asSource());
                if (t instanceof Modifier) builder.append(" ");
            } else {
                throw new NullPointerException("ComponentSet cannot contain null values!");
            }
        }

        return builder;
    }

}
