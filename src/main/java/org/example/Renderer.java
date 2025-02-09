package org.example;

import java.util.List;

class MarkdownRenderer {
    public String render(List<Token> tokens) {
        StringBuilder html = new StringBuilder();
        boolean inList = false;

        for (Token token : tokens) {
            if (token.type == TokenType.LIST_ITEM) {
                if (!inList) {
                    html.append("<ul>\n");
                    inList = true;
                }
                html.append(token.value).append("\n");
            } else {
                if (inList) {
                    html.append("</ul>\n");
                    inList = false;
                }
                html.append(token.value).append("\n");
            }
        }

        if (inList) {
            html.append("</ul>\n");
        }

        return html.toString();
    }
}
