package org.webcrawler.parser;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.webcrawler.model.*;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Stream;

final class JsoupHtmlContentParser implements ContentParser<HtmlDocument> {

    @Override
    public boolean isSupported(@NotNull RawContent content) {
        final String contentType = content.getContentType();
        return contentType.startsWith("text/html");
    }

    @Override
    public @NotNull HtmlDocument parse(@NotNull RawContent content) {
        final URI baseUri = content.getUri();
        final String html = new String(content.getContentData(), StandardCharsets.UTF_8);
        final Document jsoupDocument = Jsoup.parse(html, baseUri.toASCIIString());
        return new JsoupHtmlDocument(content, jsoupDocument);
    }

    private static final class JsoupHtmlDocument implements HtmlDocument {

        private final RawContent rawContent;
        private final Document jsoupDocument;

        public JsoupHtmlDocument(RawContent rawContent, Document jsoupDocument) {
            this.rawContent = rawContent;
            this.jsoupDocument = jsoupDocument;
        }

        @Override
        public Stream<Link> links() {
            final Elements links = jsoupDocument.select("a[href]");
            return links.stream()
                    .map(link -> {
                        final String href = URLEncoder.encode(link.attr("abs:href"), StandardCharsets.UTF_8);
                        return new Link(URI.create(href), link.text());
                    });
        }

        @Override
        public Stream<Image> images() {
            final Elements media = jsoupDocument.select("img[src]");
            return media.stream()
                    .map(img -> {
                        final String src = URLEncoder.encode(img.attr("abs:src"), StandardCharsets.UTF_8);
                        return new Image(URI.create(src), img.attr("alt"));
                    });
        }

        @Override
        public Stream<Import> imports() {
            final Elements imports = jsoupDocument.select("link[href]");
            return imports.stream()
                    .map(link -> {
                        final String href = URLEncoder.encode(link.attr("abs:href"), StandardCharsets.UTF_8);
                        return new Import(URI.create(href), link.attr("rel"));
                    });
        }

        @Override
        public byte[] getRawData() {
            return rawContent.getContentData();
        }

        @Override
        public XmlElement getRootElement() {
            return new JsoupXmlElement(jsoupDocument.child(0));
        }
    }

    private static final class JsoupXmlElement implements XmlElement {

        private final Element jsoupElement;

        private JsoupXmlElement(Element jsoupElement) {
            this.jsoupElement = jsoupElement;
        }

        @Override
        public Optional<XmlNamespace> namespace() {
            return Optional.empty();
        }

        @Override
        public Stream<XmlAttribute> attributes() {
            return Optional.ofNullable(jsoupElement.attributes())
                    .stream()
                    .flatMap(it -> it.asList().stream())
                    .map(attr -> new XmlAttribute(attr.getKey(), attr.getValue()));

        }
    }
}
