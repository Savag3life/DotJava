package me.savag3.dotjava.test;

import me.savag3.dotjava.JavaProject;
import me.savag3.dotjava.Primitive;
import me.savag3.dotjava.modifiers.AccessModifier;
import me.savag3.dotjava.modifiers.NonAccessModifier;
import org.junit.jupiter.api.Test;

public class ClassBuilderTest {

    public ClassBuilderTest() {
    }

    @Test
    void classBuilderTest() {
        JavaProject project = new JavaProject("MyProject", "me.savag3.dotjava.test");
        project.createClass("MyClass")
                .addMethod()
                .addModifier(AccessModifier.PUBLIC, NonAccessModifier.STATIC)
                .name("printHelloWorld")
                .returnType(Primitive.VOID)
                .addParameter("printTimes", Primitive.INT)
                .build()
                .addField()
                .addModifier(AccessModifier.PRIVATE)
                .name("myField")
                .type(Primitive.STRING)
                .defaultValue("Hello World!")
                .build();

        project.write();
    }

}
