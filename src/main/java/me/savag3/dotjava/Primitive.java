package me.savag3.dotjava;

import lombok.Getter;

import java.util.function.Function;

/**
 * @author Savag3life
 * @since 1.0
 */
public enum Primitive implements ObjectWrapper {

    BOOLEAN("boolean", s -> s),
    STRING("String", s -> "\"" + s + "\""),
    BYTE("byte", s -> s),
    CHAR("char", s -> "'" + s + "'"),
    DOUBLE("double", s -> s),
    FLOAT("float", s -> s),
    INT("int", s -> s),
    LONG("long", s -> s),
    SHORT("short", s -> s),
    VOID("void", s -> "");
    ;

    @Getter private final String name;
    @Getter private final Function<String, String> wrapper;

    Primitive(String name, Function<String, String> wrapper) {
        this.name = name;
        this.wrapper = wrapper;
    }

    @Override
    public String wrap(String object) {
        return null;
    }

    @Override
    public String getAsSource() {
        return name;
    }
}
