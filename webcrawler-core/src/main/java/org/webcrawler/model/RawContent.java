package org.webcrawler.model;

import java.net.URI;

public class RawContent {

    private final URI uri;
    private final String contentType;
    private final byte[] contentData;

    public RawContent(URI uri, String contentType, byte[] contentData) {
        this.uri = uri;
        this.contentType = contentType;
        this.contentData = contentData;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getContentData() {
        return contentData;
    }

    public URI getUri() {
        return uri;
    }
}
