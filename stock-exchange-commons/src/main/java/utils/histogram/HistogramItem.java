package utils.histogram;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class HistogramItem<T> {

    private final List<T> items;

    final Predicate<T> predicate;

    HistogramItem(final Predicate<T> predicate) {
        this.items = Lists.newArrayList();
        this.predicate = predicate;
    }

    public Collection<T> getItems() {
        return Collections.unmodifiableList(items);
    }

    void addItem(final T item) {
        items.add(item);
    }

    public Predicate<T> getPredicate() {
        return predicate;
    }
}
