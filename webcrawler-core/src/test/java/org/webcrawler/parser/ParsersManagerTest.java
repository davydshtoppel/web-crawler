package org.webcrawler.parser;

import org.junit.jupiter.api.Test;
import org.webcrawler.model.HtmlDocument;
import org.webcrawler.model.ParsedContent;
import org.webcrawler.model.RawContent;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ParsersManagerTest {

    @Test
    void noApplicableContentParserTest() {
        final URI uri = URI.create("http://localhost");
        final String contentType = "text/html; charset=UTF-8";
        final byte[] contentData = "<html></html>".getBytes(StandardCharsets.UTF_8);
        final RawContent rawContent = new RawContent(uri, contentType, contentData);

        final ParsersManager manager = new ParsersManager(List.of());

        assertThrows(NoApplicableParserException.class, () -> manager.parse(rawContent));
    }

    @Test
    void htmlContentParseTest() {
        final URI uri = URI.create("http://localhost");
        final String contentType = "text/html; charset=UTF-8";
        final byte[] contentData = "<html></html>".getBytes(StandardCharsets.UTF_8);
        final RawContent rawContent = new RawContent(uri, contentType, contentData);

        final HtmlDocument response = mock(HtmlDocument.class);
        //noinspection unchecked
        final ContentParser<HtmlDocument> htmlParser = mock(ContentParser.class);
        when(htmlParser.isSupported(eq(rawContent)))
                .thenReturn(true);
        when(htmlParser.parse(eq(rawContent)))
                .thenReturn(response);

        final ParsersManager manager = new ParsersManager(List.of(htmlParser));


        final ParsedContent htmlContent = manager.parse(rawContent);
        assertNotNull(htmlContent);
        assertTrue(htmlContent instanceof HtmlDocument);
        assertEquals(response, htmlContent);

        verify(htmlParser, times(1)).isSupported(any());
        verify(htmlParser, times(1)).parse(any());

        verifyNoMoreInteractions(htmlParser, response);
    }
}