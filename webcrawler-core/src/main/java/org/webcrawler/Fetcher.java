package org.webcrawler;

import lombok.RequiredArgsConstructor;
import org.webcrawler.model.RawContent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class Fetcher {

    private final HttpClient httpClient;

    public CompletableFuture<RawContent> getRawContent(final URI uri) {
        final HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .GET()
                .uri(uri)
                .build();

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofByteArray())
                .thenApply(
                        httpResponse -> {
                            final HttpHeaders headers = httpResponse.headers();
                            final byte[] body = httpResponse.body();
                            final String contentType = headers.firstValue("Content-Type").orElseThrow();
                            return new RawContent(uri, contentType, body);
                        }
                );
    }
}
