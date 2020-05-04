package org.webcrawler.parser;

public class NoApplicableParserException extends RuntimeException {

    public NoApplicableParserException(String contentType) {
        super("No applicable parser for content type: " + contentType);
    }
}
