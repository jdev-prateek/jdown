package org.example;

import java.util.List;

public class App {
    public static void main(String[] args) {
        String markdownText = """
            # Hello World
            ## Subheading
            This is **bold** text and *italic* text.
            Here is a [link](https://example.com).
            `Inline code`

            - Item 1
            - Item 2
            """;

        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(markdownText);

        MarkdownRenderer renderer = new MarkdownRenderer();
        String html = renderer.render(tokens);

        System.out.println(html);
    }
}
