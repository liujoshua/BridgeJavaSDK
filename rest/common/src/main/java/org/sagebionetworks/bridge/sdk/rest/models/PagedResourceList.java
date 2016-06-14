package org.sagebionetworks.bridge.sdk.rest.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liujoshua on 6/13/16.
 */
public class PagedResourceList<T> {
    private static final String OFFSET_KEY_FILTER = "offsetKey";
    private final List<T> items;
    private final Integer offsetBy;
    private final int pageSize;
    private final int total;
    private final Map<String, String> filters = new HashMap<>();

    public PagedResourceList(List<T> items, Integer offsetBy, int pageSize, int total) {
        this.items = items;
        this.offsetBy = offsetBy;
        this.pageSize = pageSize;
        this.total = total;
    }


    public List<T> getItems() {
        return items;
    }

    public Integer getOffsetBy() {
        return offsetBy;
    }

    public String getOffsetKey() {
        return filters.get(OFFSET_KEY_FILTER);
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotal() {
        return total;
    }

    public Map<String, String> getFilters() {
        return Collections.unmodifiableMap(filters);
    }

    void setFilter(String key, String value) {
        if (isNotBlank(key) && isNotBlank(value)) {
            filters.put(key, value);
        }
    }

    private boolean isNotBlank(String s) {
        return (s != null) && (s.trim().length() > 0);
    }

    @Override
    public String toString() {
        return "PagedResourceList [items=" + items + ", offsetBy=" + offsetBy + ", pageSize=" + pageSize + ", total=" + total +
                ", filters=" + filters + "]";
    }
}

