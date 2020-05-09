package org.webcrawler.model;

import java.util.Optional;
import java.util.stream.Stream;

public interface XmlElement {

    Optional<XmlNamespace> namespace();

    Stream<XmlAttribute> attributes();
}
