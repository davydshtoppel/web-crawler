package org.webcrawler.parser;

import org.webcrawler.model.HtmlDocument;

public final class ContentParsers {

    public static ContentParser<HtmlDocument> newHtmlParser() {
        return new JsoupHtmlContentParser();
    }
}
