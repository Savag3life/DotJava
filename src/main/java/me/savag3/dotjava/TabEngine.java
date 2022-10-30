package me.savag3.dotjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Savag3life
 * @since 1.0
 */
public class TabEngine {

    private static final String TAB = "    ";

    public TabEngine() { }

    /**
     * Collapse the spacing inside a given line. This makes it much easier to re-indent the line.
     * @param builder The String to collapse.
     */
    public void collapse(StringBuilder builder) {
        // remove all spaces unless inside "" or ''

        boolean insideString = false;
        boolean insideChar = false;

        int x = 0;
        for (char c : builder.toString().toCharArray()) {

            if (c == '"') {
                insideString = !insideString;
            }

            if (c == '\'') {
                insideChar = !insideChar;
            }

            if (c == ' ' && builder.charAt(x + 1) == ' ' && !insideString && !insideChar) {
                builder.deleteCharAt(x);
                x--;
            }

            x++;
        }
    }

    /**
     * Attempt to decipher the spacing of a given line. This is used to re-indent the line.
     * Essentially checking if we are inside '{}' to determine the spacing. '{' = depth + 1, '}' = depth - 1
     * @param builder The String to decipher.
     * @return Indentation StringBuilder containing all lines.
     */
    public StringBuilder correctIndentation(StringBuilder builder) {
        // correct all tabs

        int depth = 0;
        List<String> lines = getLines(builder);
        StringBuilder newBuilder = new StringBuilder();

        for (String line : lines) {
            line = line.trim();

            if (line.endsWith("{")) {
                newBuilder.append(TAB.repeat(depth))
                        .append(line)
                        .append("\n");
                depth++;
            } else if (line.endsWith("}")) {
                depth--;
                newBuilder.append(TAB.repeat(depth))
                        .append(line)
                        .append("\n");
            } else {
                newBuilder.append(TAB.repeat(depth))
                        .append(line)
                        .append("\n");
            }
        }

        return newBuilder;
    }

    private List<String> getLines(StringBuilder builder) {
        return new ArrayList<>(Arrays.asList(builder.toString().split("\n")));
    }
}
