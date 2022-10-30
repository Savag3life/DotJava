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
        ${modifiers} interface ${name}${extends} {
    
            ${methods}
            
        }
        """
)
public class SourceInterface extends SourceClass {
    public SourceInterface(String className, String classPath) {
        super(className, classPath);
    }
}
