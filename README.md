## DotJava

DotJava is an in-code source code generator for Java. We use DotJava to generate java source code at runtime. This could be for many reasons, but I created it to generate the boilerplate code I don't want to write everytime I create a new project.

### Usage
```java
public class ClassBuilder {
  void buildClass() {
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
```
DotJava will generate the following source code:
```java
package me.savag3.dotjava.test;

class MyClass {
    
    private String myField = "Hello World!";
    
    public static void printHelloWorld(int printTimes) {
        
    }
}
```
*Generated classes may have unexpected formatting and may not compile.*

### Missing features / Coming Soon
- [X] Indentation engine for generated code
- [ ] Support for Java 16 record classes
- [ ] Complex annotations
- [ ] Constructor creators for classes and abstract classes
- [ ] Support for Java 16 sealed classes
- [ ] Method instruction support
- [ ] Project file pre-creation (Like .idea folder for IntelliJ)
- Generator code validation
  - [ ] Access modifier validation
  - [ ] Duplicate annotation validation
  - [ ] Duplicate method validation
  - [ ] Duplicate field validation
  - [ ] Duplicate class validation

## Credits
- [Hippo](https://github.com/Hippo) - Counsel and support

## Warning
This is still a very new project & is barely usable at this point. Please don't use it in production!

[This project is licensed under GNU General Public License v3.0 (GPL-3.0)](https://choosealicense.com/licenses/gpl-3.0/)
