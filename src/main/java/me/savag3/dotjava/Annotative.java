package me.savag3.dotjava;

import me.savag3.dotjava.structure.SourceAnnotation;

import java.util.Set;

/**
 * @author Savag3life
 * @since 1.0
 */
public interface Annotative {

    Set<SourceAnnotation> getAnnotations();

    default void withAnnotation(SourceAnnotation annotation) {
        getAnnotations().add(annotation);
    }

    default void removeAnnotation(SourceAnnotation annotation) {
        getAnnotations().remove(annotation);
    }

    default boolean hasAnnotation(SourceAnnotation annotation) {
        return getAnnotations().contains(annotation);
    }

}
