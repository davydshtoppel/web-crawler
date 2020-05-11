package org.webcrawler.parser;

public class ParsingException extends RuntimeException {

    public ParsingException(Exception cause) {
        super("Failed to parse xml document", cause);
    }

    public ParsingException(String message, Exception cause) {
        super(message, cause);
    }

    public ParsingException(String message) {
        super(message);
    }
}
