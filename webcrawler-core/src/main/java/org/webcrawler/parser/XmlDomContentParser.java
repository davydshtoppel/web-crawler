package org.webcrawler.parser;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.webcrawler.model.RawContent;
import org.webcrawler.model.XmlAttribute;
import org.webcrawler.model.XmlDocument;
import org.webcrawler.model.XmlElement;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

final class XmlDomContentParser implements ContentParser<XmlDocument> {

    @Override
    public boolean isSupported(@NotNull RawContent content) {
        final String contentType = content.getContentType();
        return contentType.startsWith("text/xml") || contentType.startsWith("application/xml");
    }

    @Override
    public @NotNull XmlDocument parse(@NotNull RawContent content) {
        final String xml = new String(content.getContentData(), StandardCharsets.UTF_8);
        final Document document;
        try {
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException e) {
            throw new ParsingException("Failed to configure parser", e);
        } catch (SAXException | IOException e) {
            throw new ParsingException(e);
        }
        return new DomXmlDocument(document, content);
    }

    private static final class DomXmlDocument implements XmlDocument {

        private final Document document;
        private final RawContent rawContent;

        private DomXmlDocument(Document document, RawContent rawContent) {
            this.document = document;
            this.rawContent = rawContent;
        }

        @Override
        public byte[] getRawData() {
            return rawContent.getContentData();
        }

        @Override
        public XmlElement getRootElement() {
            return new DomXmlElement(document.getDocumentElement());
        }
    }

    private static final class DomXmlElement implements XmlElement {

        private final Element element;

        DomXmlElement(Element element) {
            this.element = element;
        }

        @Override
        public Stream<XmlAttribute> attributes() {
            return Optional.ofNullable(element.getAttributes())
                    .stream()
                    .flatMap(it -> {
                        final int length = it.getLength();
                        final Node[] result = new Node[length];
                        for (int i = 0; i < length; i++) {
                            result[i] = it.item(i);
                        }
                        return Arrays.stream(result);
                    })
                    .map(node -> new XmlAttribute(node.getNodeName(), node.getNodeValue()));
        }
    }
}
