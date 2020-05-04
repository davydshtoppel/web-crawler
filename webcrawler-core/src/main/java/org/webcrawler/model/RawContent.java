package org.webcrawler.model;

import java.net.URI;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RawContent {

    private final URI uri;
    private final String contentType;
    private final byte[] contentData;

}
