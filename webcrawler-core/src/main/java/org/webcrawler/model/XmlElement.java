package org.webcrawler.model;

import java.util.stream.Stream;

public interface XmlElement {

    Stream<XmlAttribute> attributes();
}
