package org.webcrawler.parser;

import org.webcrawler.model.ParsedContent;
import org.webcrawler.model.RawContent;

import java.util.List;

public final class ParsersManager {

    private final List<ContentParser<?>> contentParsers;

    public ParsersManager(List<ContentParser<?>> contentParsers) {
        this.contentParsers = contentParsers;
    }

    public ParsedContent parse(RawContent rawContent) {
        return contentParsers.stream()
                .filter(it -> it.isSupported(rawContent))
                .map(it -> it.parse(rawContent))
                .findAny()
                .orElseThrow(() -> new NoApplicableParserException(rawContent.getContentType()));
    }
}
