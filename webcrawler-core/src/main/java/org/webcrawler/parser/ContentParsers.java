package org.webcrawler.parser;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.webcrawler.model.HtmlDocument;
import org.webcrawler.model.XmlDocument;

public final class ContentParsers {

    @Contract(value = " -> new", pure = true)
    public static @NotNull ContentParser<HtmlDocument> newHtmlParser() {
        return new JsoupHtmlContentParser();
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull ContentParser<XmlDocument> newXmlParser() {
        return new XmlDomContentParser();
    }
}
