package me.savag3.dotjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabEngine {

    private static final String TAB = "    ";

    public TabEngine() {

    }

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
