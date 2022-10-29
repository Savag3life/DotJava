package me.savag3.dotjava.structure;

import me.savag3.dotjava.source.SourceTemplate;

/**
 * @author Savag3life
 * @since 1.0
 */
@SourceTemplate(
        """
        ${package}
        ${imports}
        ${annotations}
        ${modifiers} abstract class ${name} {
        
            ${fields}
            ${constructor}
            ${methods}
            ${subclasses}
        
        }
        """
)
public class SourceAbstractClass extends SourceClass {
    public SourceAbstractClass(String className, String classPath) {
        super(className, classPath);
    }
}
