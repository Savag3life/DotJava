package me.savag3.dotjava.structure;

import lombok.Getter;
import me.savag3.dotjava.Annotative;
import me.savag3.dotjava.ComponentSet;
import me.savag3.dotjava.ObjectWrapper;
import me.savag3.dotjava.modifiers.Modifier;
import me.savag3.dotjava.modifiers.Permissible;
import me.savag3.dotjava.source.Source;
import me.savag3.dotjava.source.SourceTemplate;

import java.util.*;

/**
 * @author Savag3life
 * @since 1.0
 */
@SourceTemplate(
    """
    ${annotations}
    ${modifiers}${returnType} ${name}(${parameters}) {
    
    }
    """
)
public final class SourceMethod implements Annotative, Permissible, Source {

    @Getter private final ComponentSet<SourceAnnotation> annotations = new ComponentSet<>();
    @Getter private final ComponentSet<Modifier> modifiers = new ComponentSet<>();

    @Getter private final String methodName;
    @Getter private final ObjectWrapper returnType;
    @Getter private final HashMap<String, ObjectWrapper> parameters = new HashMap<>();

    public SourceMethod(String methodName, ObjectWrapper returnType) {
        this.methodName = methodName;
        this.returnType = returnType;
    }

    @Override
    public StringBuilder asSource() {
        StringBuilder template = new StringBuilder(getSourceTemplate());

        StringBuilder annotations = this.annotations.compile();
        this.modifiers.sort(Comparator.comparingInt(Modifier::getPriority));
        StringBuilder modifiers = this.modifiers.compile();
        StringBuilder parameters = compileParameters();

        replace(template, "${annotations}", annotations);
        replace(template, "${modifiers}", modifiers);
        replace(template, "${returnType}", returnType.getAsSource());
        replace(template, "${name}", methodName);
        replace(template, "${parameters}", parameters);

        return template;
    }

    private StringBuilder compileParameters() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, ObjectWrapper> parameter : parameters.entrySet()) {
            builder.append(parameter.getValue().getAsSource()).append(" ").append(parameter.getKey()).append(", ");
        }

        return builder.replace(builder.length() - 2, builder.length(), "");
    }

    public static class SourceMethodBuilder {
        private final SourceClass parent;

        @Getter private final Set<Modifier> modifiers = new HashSet<>();

        @Getter private String name;
        @Getter private ObjectWrapper returnType;
        @Getter private final HashMap<String, ObjectWrapper> parameters = new HashMap<>();

        @Getter private final Set<SourceAnnotation> annotations = new HashSet<>();

        public SourceMethodBuilder(SourceClass parent) {
            this.parent = parent;
        }

        public SourceMethodBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SourceMethodBuilder returnType(ObjectWrapper returnType) {
            this.returnType = returnType;
            return this;
        }

        public SourceMethodBuilder addModifier(Modifier... modifier) {
            modifiers.addAll(Arrays.asList(modifier));
            return this;
        }

        public SourceMethodBuilder addAnnotation(SourceAnnotation... annotation) {
            annotations.addAll(Arrays.asList(annotation));
            return this;
        }

        public SourceMethodBuilder addParameter(String name, ObjectWrapper type) {
            parameters.put(name, type);
            return this;
        }

        public SourceClass build() {
            this.parent.addMethod(build0());
            return this.parent;
        }

        private SourceMethod build0() {
            SourceMethod method = new SourceMethod(name, returnType);
            method.getModifiers().addAll(modifiers);
            method.getAnnotations().addAll(annotations);
            method.getParameters().putAll(parameters);
            return method;
        }
    }
}
