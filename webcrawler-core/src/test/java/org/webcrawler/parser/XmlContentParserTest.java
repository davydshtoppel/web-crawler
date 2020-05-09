package org.webcrawler.parser;

import org.junit.jupiter.api.Test;
import org.webcrawler.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class XmlContentParserTest {

    @Test
    void nothing() {
        final ContentParser<XmlDocument> parser = ContentParsers.newXmlParser();
        final RawContent rawContent = new RawContent(
                URI.create("http://localshost:8080"), "", "<html></html>".getBytes(StandardCharsets.UTF_8));

        final XmlDocument document = parser.parse(rawContent);
        assertNotNull(document);
    }

    @Test
    void parseDocumentTest() throws IOException {
        final ContentParser<XmlDocument> parser = ContentParsers.newXmlParser();

        try(final InputStream inputStream = getClass().getResourceAsStream("/test1.xml")) {
            final RawContent rawContent = new RawContent(URI.create("http://localshost:8080"), "", inputStream.readAllBytes());
            final XmlDocument document = parser.parse(rawContent);

            assertNotNull(document);
        }
    }

    @Test
    void rootElementTest() throws IOException {
        final ContentParser<XmlDocument> parser = ContentParsers.newXmlParser();

        try(final InputStream inputStream = getClass().getResourceAsStream("/test1.xml")) {
            final RawContent rawContent = new RawContent(URI.create("http://localshost:8080"), "", inputStream.readAllBytes());
            final XmlDocument document = parser.parse(rawContent);

            assertNotNull(document);
            final XmlElement rootElement = document.getRootElement();
            assertNotNull(rootElement);
            final Stream<XmlAttribute> attributes = rootElement.attributes();
            assertEquals(2L, attributes.count());
        }
    }

    @Test
    void testXmlIsSupported() {
        final ContentParser<XmlDocument> parser = ContentParsers.newXmlParser();

        assertTrue(parser.isSupported(new RawContent(URI.create("http://localshost:8080"), "text/xml", new byte[0])));
    }

    @Test
    void applicationXmlIsSupported() {
        final ContentParser<XmlDocument> parser = ContentParsers.newXmlParser();

        assertTrue(parser.isSupported(new RawContent(URI.create("http://localshost:8080"), "application/xml", new byte[0])));
    }
}
