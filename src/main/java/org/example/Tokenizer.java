package org.example;

import java.util.*;
import java.util.regex.*;

class Tokenizer {
    private static final Pattern HEADER_PATTERN = Pattern.compile("^(#{1,6})\\s*(.+)$");
    private static final Pattern BOLD_PATTERN = Pattern.compile("\\*\\*(.*?)\\*\\*");
    private static final Pattern ITALIC_PATTERN = Pattern.compile("\\*(.*?)\\*");
    private static final Pattern LINK_PATTERN = Pattern.compile("\\[(.*?)\\]\\((.*?)\\)");
    private static final Pattern INLINE_CODE_PATTERN = Pattern.compile("`([^`]+)`");
    private static final Pattern LIST_PATTERN = Pattern.compile("^[-*+]\\s+(.+)$");

    public List<Token> tokenize(String markdown) {
        List<Token> tokens = new ArrayList<>();
        String[] lines = markdown.split("\n");

        for (String line : lines) {
            Matcher headerMatcher = HEADER_PATTERN.matcher(line);
            Matcher listMatcher = LIST_PATTERN.matcher(line);

            if (headerMatcher.matches()) {
                int level = headerMatcher.group(1).length(); // Number of #
                tokens.add(new Token(TokenType.HEADER, "<h" + level + ">" + headerMatcher.group(2) + "</h" + level + ">"));
            } else if (listMatcher.matches()) {
                tokens.add(new Token(TokenType.LIST_ITEM, "<li>" + listMatcher.group(1) + "</li>"));
            } else {
                // Process inline formatting (bold, italic, links, inline code)
                String formattedLine = processInlineFormatting(line);
                tokens.add(new Token(TokenType.PARAGRAPH, "<p>" + formattedLine + "</p>"));
            }
        }

        return tokens;
    }

    private String processInlineFormatting(String line) {
        line = BOLD_PATTERN.matcher(line).replaceAll("<strong>$1</strong>");
        line = ITALIC_PATTERN.matcher(line).replaceAll("<em>$1</em>");
        line = LINK_PATTERN.matcher(line).replaceAll("<a href=\"$2\">$1</a>");
        line = INLINE_CODE_PATTERN.matcher(line).replaceAll("<code>$1</code>");
        return line;
    }
}
