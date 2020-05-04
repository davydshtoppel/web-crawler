package org.webcrawler.parser;

import org.junit.jupiter.api.Test;
import org.webcrawler.model.HtmlDocument;
import org.webcrawler.model.RawContent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class HtmlContentParserTest {

    @Test
    void nothing() {
        final ContentParser<HtmlDocument> parser = ContentParsers.newHtmlParser();
        final RawContent rawContent = new RawContent(
                URI.create("http://localshost:8080"), "", "<html></html>".getBytes(StandardCharsets.UTF_8));

        final HtmlDocument document = parser.parse(rawContent);
        assertNotNull(document);
    }

    @Test
    void noLinksTest() {
        final ContentParser<HtmlDocument> parser = ContentParsers.newHtmlParser();
        final RawContent rawContent = new RawContent(
                URI.create("http://localshost:8080"), "", "<html></html>".getBytes(StandardCharsets.UTF_8));

        final HtmlDocument document = parser.parse(rawContent);
        assertNotNull(document);
        assertEquals(document.links().count(), 0L);
    }

    @Test
    void parseLinksTest() throws IOException {
        final ContentParser<HtmlDocument> parser = ContentParsers.newHtmlParser();

        try(final InputStream inputStream = getClass().getResourceAsStream("/iqos.com.html")) {
            final RawContent rawContent = new RawContent(URI.create("http://localshost:8080"), "", inputStream.readAllBytes());
            final HtmlDocument document = parser.parse(rawContent);

            assertNotNull(document);
            assertEquals(document.links().count(), 109L);
        }
    }

    @Test
    void noImagesTest() {
        final ContentParser<HtmlDocument> parser = ContentParsers.newHtmlParser();
        final RawContent rawContent = new RawContent(
                URI.create("http://localshost:8080"), "", "<html></html>".getBytes(StandardCharsets.UTF_8));

        final HtmlDocument document = parser.parse(rawContent);
        assertNotNull(document);
        assertEquals(document.images().count(), 0L);
    }

    @Test
    void parseImagesTest() throws IOException {
        final ContentParser<HtmlDocument> parser = ContentParsers.newHtmlParser();

        try(final InputStream inputStream = getClass().getResourceAsStream("/iqos.com.html")) {
            final RawContent rawContent = new RawContent(URI.create("http://localshost:8080"), "", inputStream.readAllBytes());
            final HtmlDocument document = parser.parse(rawContent);

            assertNotNull(document);
            assertEquals(document.images().count(), 23L);
        }
    }

    @Test
    void noImportsTest() {
        final ContentParser<HtmlDocument> parser = ContentParsers.newHtmlParser();
        final RawContent rawContent = new RawContent(
                URI.create("http://localshost:8080"), "", "<html></html>".getBytes(StandardCharsets.UTF_8));

        final HtmlDocument document = parser.parse(rawContent);
        assertNotNull(document);
        assertEquals(document.imports().count(), 0L);
    }

    @Test
    void parseImportsTest() throws IOException {
        final ContentParser<HtmlDocument> parser = ContentParsers.newHtmlParser();

        try(final InputStream inputStream = getClass().getResourceAsStream("/iqos.com.html")) {
            final RawContent rawContent = new RawContent(URI.create("http://localshost:8080"), "", inputStream.readAllBytes());
            final HtmlDocument document = parser.parse(rawContent);

            assertNotNull(document);
            assertEquals(document.imports().count(), 9L);
        }
    }

    @Test
    void htmlIsSupportedTest() {
        final RawContent rawContent = new RawContent(URI.create("http://localshost"), "text/html", new byte[0]);
        final ContentParser<HtmlDocument> parser = ContentParsers.newHtmlParser();

        assertTrue(parser.isSupported(rawContent));
    }
}
