package me.savag3.dotjava.structure;

import lombok.Getter;
import me.savag3.dotjava.Annotative;
import me.savag3.dotjava.ComponentSet;
import me.savag3.dotjava.ObjectWrapper;
import me.savag3.dotjava.modifiers.Modifier;
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
    ${modifiers} ${type} ${name} = ${value};
    """
)
public final class SourceField implements Annotative, Source {

    @Getter private final ComponentSet<SourceAnnotation> annotations = new ComponentSet<>();
    @Getter private final ComponentSet<Modifier> modifiers = new ComponentSet<>();

    @Getter private final ObjectWrapper type;
    @Getter private final String fieldName;
    @Getter private final String defaultValue;

    public SourceField(ObjectWrapper type, String fieldName, String value) {
        this.type = type;
        this.fieldName = fieldName;
        this.defaultValue = value;
    }

    @Override
    public StringBuilder asSource() {
        StringBuilder template = new StringBuilder(getSourceTemplate());

        StringBuilder annotations = this.annotations.compile();
        this.modifiers.sort(Comparator.comparingInt(Modifier::getPriority));
        StringBuilder modifiers = this.modifiers.compile();

        replace(template, "${annotations}", annotations);
        replace(template, "${modifiers}", modifiers);
        replace(template, "${type}", type.getAsSource());
        replace(template, "${name}", fieldName);
        replace(template, "${value}", defaultValue);

        return template;
    }

    public static class SourceFieldBuilder {
        private final SourceClass parent;

        @Getter private final Set<Modifier> modifiers = new HashSet<>();

        @Getter private String name;
        @Getter private ObjectWrapper type;
        @Getter private String defaultValue;

        @Getter private final Set<SourceAnnotation> annotations = new HashSet<>();

        public SourceFieldBuilder(SourceClass parent) {
            this.parent = parent;
        }

        public SourceFieldBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SourceFieldBuilder type(ObjectWrapper type) {
            this.type = type;
            return this;
        }

        public SourceFieldBuilder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public SourceFieldBuilder addModifier(Modifier... modifier) {
            modifiers.addAll(Arrays.asList(modifier));
            return this;
        }

        public SourceFieldBuilder addAnnotation(SourceAnnotation... annotation) {
            annotations.addAll(Arrays.asList(annotation));
            return this;
        }

        public SourceClass build() {
            parent.addField(build0());
            return parent;
        }

        private SourceField build0() {
            SourceField field =  new SourceField(
                    type,
                    name,
                    defaultValue
            );

            field.getAnnotations().addAll(annotations);
            field.getModifiers().addAll(modifiers);

            return field;
        }
    }

}
