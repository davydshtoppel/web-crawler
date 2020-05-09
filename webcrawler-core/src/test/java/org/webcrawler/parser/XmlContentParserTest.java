package org.webcrawler.parser;

import org.junit.jupiter.api.Test;
import org.webcrawler.model.RawContent;
import org.webcrawler.model.XmlDocument;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
