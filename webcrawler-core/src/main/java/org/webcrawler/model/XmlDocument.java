package org.webcrawler.model;

import org.jetbrains.annotations.NotNull;

public interface XmlDocument extends ParsedContent {

    @NotNull XmlElement getRootElement();
}
