package org.webcrawler.model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class XmlNamespace {

    @NotNull
    private final String prefix;
    @NotNull
    private final String uri;

    public XmlNamespace(@NotNull String prefix, @NotNull String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    public @NotNull String getPrefix() {
        return prefix;
    }

    public @NotNull String getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XmlNamespace that = (XmlNamespace) o;
        return prefix.equals(that.prefix) &&
                uri.equals(that.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, uri);
    }

    @Override
    public String toString() {
        return "XmlNamespace{" +
                "prefix='" + prefix + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
