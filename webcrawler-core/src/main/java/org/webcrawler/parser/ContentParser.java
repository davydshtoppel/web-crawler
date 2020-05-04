package org.webcrawler.parser;

import org.webcrawler.model.ParsedContent;
import org.webcrawler.model.RawContent;

public interface ContentParser<T extends ParsedContent> {

    boolean isSupported(RawContent content);

    T parse(RawContent content);
}
