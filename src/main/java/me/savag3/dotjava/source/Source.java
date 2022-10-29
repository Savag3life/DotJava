package me.savag3.dotjava.source;

import me.savag3.dotjava.exceptions.MissingAnnotationException;

/**
 * @author Savag3life
 * @since 1.0
 */
public interface Source {

    default StringBuilder getSourceTemplate() {
        try {
            if (getClass().isAnnotationPresent(SourceTemplate.class)) {
                return new StringBuilder(getClass().getAnnotation(SourceTemplate.class).value());
            } else {
                throw new MissingAnnotationException("SourceTemplate annotation is missing from " + getClass().getName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    StringBuilder asSource();

    default void replace(StringBuilder target, String key, StringBuilder value) {
        target.replace(target.indexOf(key), target.indexOf(key) + key.length(), value.toString());
    }

    default void replace(StringBuilder target, String key, String value) {
        target.replace(target.indexOf(key), target.indexOf(key) + key.length(), value);
    }
}
