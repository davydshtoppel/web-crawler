package org.webcrawler.model;

import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.stream.Stream;

/**
 * Todo extend methods
 */
public interface HtmlDocument extends ParsedContent {

    Stream<Link> links();

    Stream<Image> images();

    Stream<Import> imports();

    final class Link {

        private final URI href;
        @Nullable
        private final String text;

        public Link(URI href, @Nullable String text) {
            this.text = text;
            this.href = href;
        }

        @Nullable
        public String getText() {
            return text;
        }

        public URI getHref() {
            return href;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "href=" + href +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

    final class Image {

        private final URI src;
        @Nullable
        private final String alt;

        public Image(URI src, @Nullable String alt) {
            this.alt = alt;
            this.src = src;
        }

        @Nullable
        public String getAlt() {
            return alt;
        }

        public URI getSrc() {
            return src;
        }

        @Override
        public String toString() {
            return "Image{" +
                    "src=" + src +
                    ", alt='" + alt + '\'' +
                    '}';
        }
    }

    final class Import {

        private final URI href;
        @Nullable
        private final String rel;

        public Import(URI href, @Nullable String rel) {
            this.rel = rel;
            this.href = href;
        }

        @Override
        public String toString() {
            return "Import{" +
                    "href=" + href +
                    ", rel='" + rel + '\'' +
                    '}';
        }
    }
}
