package org.example;

enum TokenType {
    HEADER, BOLD, ITALIC, LINK, INLINE_CODE, PARAGRAPH, LIST_ITEM, UNKNOWN
}

class Token {
    TokenType type;
    String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + ": " + value;
    }
}
