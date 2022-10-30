package me.savag3.dotjava.test;

import me.savag3.dotjava.ComponentSet;
import me.savag3.dotjava.modifiers.AccessModifier;
import me.savag3.dotjava.modifiers.Modifier;
import me.savag3.dotjava.modifiers.NonAccessModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

public class AccessModifierTest {

    @Test
    void testAccessModifierSorting() {
        ComponentSet<Modifier> modifiers = new ComponentSet<>() {{
            addAll(
                    Arrays.asList(
                            NonAccessModifier.STATIC,
                            AccessModifier.PROTECTED
                    )
            );
        }};

        modifiers.sort(Comparator.comparingInt(Modifier::getPriority));

        Assertions.assertArrayEquals(
                new Modifier[] {
                        AccessModifier.PROTECTED,
                        NonAccessModifier.STATIC
                },
                modifiers.toArray()
        );
    }

}
