package org.webcrawler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webcrawler.model.RawContent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FetcherTest {

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse httpResponse;

    @InjectMocks
    private Fetcher fetcher;


    @Test
    void getRawContentSuccessful() throws ExecutionException, InterruptedException {

        final String contentType = "text/html; charset=utf-8";
        final byte[] body = new byte[]{};
        final HttpHeaders httpHeaders = HttpHeaders.of(Map.of("Content-Type", List.of(contentType)), (a, b) -> true);

        when(httpResponse.body()).thenReturn(body);
        when(httpResponse.headers()).thenReturn(httpHeaders);
        when(httpClient.sendAsync(any(HttpRequest.class), any())).thenReturn(CompletableFuture.completedFuture(httpResponse));

        final URI uri = URI.create("https://www.google.com/?client=safari");
        final RawContent expectedRawContent = new RawContent(uri, contentType, body);
        final CompletableFuture<RawContent> rawContent = fetcher.getRawContent(uri);

        assertNotNull(rawContent);
        assertEquals(expectedRawContent, rawContent.get());

    }

    @Test
    void getRawContentContentTypeNotFound() {

        final byte[] body = new byte[]{};
        final HttpHeaders httpHeaders = HttpHeaders.of(Map.of(), (a, b) -> true);

        when(httpResponse.body()).thenReturn(body);
        when(httpResponse.headers()).thenReturn(httpHeaders);
        when(httpClient.sendAsync(any(HttpRequest.class), any())).thenReturn(CompletableFuture.completedFuture(httpResponse));

        assertThrows(ExecutionException.class, () -> fetcher.getRawContent(URI.create("https://www.google.com/?client=safari")).get());

    }

}
