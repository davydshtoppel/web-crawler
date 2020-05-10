package org.webcrawler.model;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public interface XmlElement {

    @NotNull String getName();

    @NotNull Optional<XmlNamespace> namespace();

    @NotNull Stream<XmlAttribute> attributes();

    @NotNull Stream<XmlElement> children();
}
