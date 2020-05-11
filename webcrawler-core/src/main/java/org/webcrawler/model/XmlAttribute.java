package org.webcrawler.model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class XmlAttribute {

    @NotNull
    private final String name;
    @NotNull
    private final String value;

    public XmlAttribute(@NotNull String name, @NotNull String value) {
        this.name = name;
        this.value = value;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XmlAttribute that = (XmlAttribute) o;
        return name.equals(that.name) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "XmlAttribute{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
