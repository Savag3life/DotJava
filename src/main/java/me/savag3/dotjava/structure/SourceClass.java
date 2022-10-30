package me.savag3.dotjava.structure;

import lombok.Getter;
import lombok.Setter;
import me.savag3.dotjava.Annotative;
import me.savag3.dotjava.ComponentSet;
import me.savag3.dotjava.modifiers.Modifier;
import me.savag3.dotjava.modifiers.Permissible;
import me.savag3.dotjava.source.Source;
import me.savag3.dotjava.source.SourceTemplate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Savag3life
 * @since 1.0
 */
@SourceTemplate(
    """
    ${package}
    
    ${imports}
    ${annotations}
    ${modifiers} class ${name} ${extends} ${implements} {
    
        ${fields}
        ${constructor}
        ${methods}
        ${subclasses}
    
    }
    """
)
public class SourceClass implements Annotative, Permissible, Source {

    @Getter private final ComponentSet<SourceField> fields = new ComponentSet<>();
    @Getter private final ComponentSet<SourceMethod> methods = new ComponentSet<>();

    @Getter private final ComponentSet<SourceClass> innerClasses = new ComponentSet<>();

    @Getter private final String className;
    @Getter private final String packageName;
    @Getter private final String classPath;
    @Getter private final ComponentSet<Modifier> modifiers = new ComponentSet<>();

    @Getter @Setter private String extendedClass;
    @Getter private final Set<String> implementedInterfaces = new HashSet<>();

    @Getter private final ComponentSet<SourceAnnotation> annotations = new ComponentSet<>();

    public SourceClass(String className, String classPath) {
        this.className = className;
        this.classPath = classPath;
        this.packageName = classPath.replace('/', '.');
    }

    public SourceField.SourceFieldBuilder addField() {
        return new SourceField.SourceFieldBuilder(this);
    }

    void addField(SourceField field) {
        fields.add(field);
    }

    public SourceMethod.SourceMethodBuilder addMethod() {
        return new SourceMethod.SourceMethodBuilder(this);
    }

    void addMethod(SourceMethod method) {
        methods.add(method);
    }

    void addSubClass(SourceClass sourceClass) {
        innerClasses.add(sourceClass);
    }

    @Override
    public StringBuilder asSource() {
        StringBuilder template = new StringBuilder(getSourceTemplate());

        StringBuilder annotations = this.annotations.compile();
        this.modifiers.sort(Comparator.comparingInt(Modifier::getPriority));
        StringBuilder modifiers = this.modifiers.compile();
        StringBuilder fields = this.fields.compile();
        StringBuilder methods = this.methods.compile();
        StringBuilder subclasses = this.innerClasses.compile();

        replace(template, "${annotations}", annotations);
        replace(template, "${modifiers}", modifiers);
        replace(template, "${fields}", fields);
        replace(template, "${methods}", methods);
        replace(template, "${subclasses}", subclasses);
        replace(template, "${name}", className);
        replace(template, "${package}", "package " + packageName + ";");
        replace(template, "${imports}", "");
        replace(template, "${constructor}", "");
        replace(template, "${extends}", extendedClass == null ? "" : "extends " + extendedClass);
        replace(template, "${implements}", implementedInterfaces.isEmpty() ? "" : "implements " + String.join(", ", implementedInterfaces));

        return template;
    }


}
