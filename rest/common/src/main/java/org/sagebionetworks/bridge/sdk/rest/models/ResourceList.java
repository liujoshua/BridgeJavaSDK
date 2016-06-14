package org.sagebionetworks.bridge.sdk.rest.models;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by liujoshua on 6/13/16.
 */
public final class ResourceList<T> implements Iterable<T> {

    private final List<T> items;
    private final int total;

    public ResourceList(List<T> items, int total) {
        this.items = items;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    public T get(int index) {
        return items.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, total);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResourceList other = (ResourceList) obj;
        return Objects.equals(items, other.items) && total == other.total;
    }

    @Override
    public String toString() {
        return "ResourceList [items=" + items + ", total=" + total + "]";
    }

}
