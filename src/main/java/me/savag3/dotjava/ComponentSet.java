package me.savag3.dotjava;

import me.savag3.dotjava.modifiers.Modifier;
import me.savag3.dotjava.source.Source;

import java.util.HashSet;

/**
 * @author Savag3life
 * @since 1.0
 */
public class ComponentSet<T> extends HashSet<T> {

    public StringBuilder compile() {
        StringBuilder builder = new StringBuilder();

        for (T t : this) {
            if (t instanceof Source) {
                builder.append(((Source) t).asSource());
                if (t instanceof Modifier) builder.append(" ");
            } else {
                throw new IllegalArgumentException("Cannot compile " + t.getClass().getName() + " as it is not a \"Source\" object");
            }
        }

        removeDoubleSpacing(builder);

        return builder;
    }

    private void removeDoubleSpacing(StringBuilder builder) {
        for (int i = 0; i < builder.length(); i++) {
            if (i > builder.length() - 2) break;
            if (builder.charAt(i) == ' ' && builder.charAt(i + 1) == ' ') {
                builder.deleteCharAt(i);
            }
        }
    }

}
