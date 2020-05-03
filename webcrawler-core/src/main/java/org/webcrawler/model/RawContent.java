package org.webcrawler.model;

public class RawContent {

    private final ContentType contentType;
    private final byte[] contentData;

    public RawContent(ContentType contentType, byte[] contentData) {
        this.contentType = contentType;
        this.contentData = contentData;
    }
}
