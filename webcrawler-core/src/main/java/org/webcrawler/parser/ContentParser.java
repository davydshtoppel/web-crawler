package org.webcrawler.parser;

import org.jetbrains.annotations.NotNull;
import org.webcrawler.model.ParsedContent;
import org.webcrawler.model.RawContent;

public interface ContentParser<T extends ParsedContent> {

    boolean isSupported(@NotNull RawContent content);

    @NotNull T parse(@NotNull RawContent content);
}
