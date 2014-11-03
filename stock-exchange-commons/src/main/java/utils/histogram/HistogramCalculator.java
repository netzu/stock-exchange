package utils.histogram;


import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by mht on 03/11/14.
 */
public class HistogramCalculator<T> {


    private final List<Predicate<T>> bucketPredicates;

    public HistogramCalculator(final Collection<Predicate<T>> bucketPredicates) {
        this.bucketPredicates = Lists.newArrayList(bucketPredicates);
    }

    public Collection<HistogramItem<T>> calculateHistogram(final Collection<T> input) {

        List<HistogramItem<T>>  result = setup();

        for (final T inputItem : input) {
            for (final HistogramItem<T> bucket : result) {
                if (bucket.predicate.apply(inputItem)) {
                    bucket.addItem(inputItem);
                }
            }
        }


        return result;
    }

    private List<HistogramItem<T>> setup() {
        List<HistogramItem<T>> result = Lists.newArrayList();

        for (final Predicate<T> bucketDefinition : this.bucketPredicates) {
            result.add(new HistogramItem<T>(bucketDefinition));
        }


        return result;
    }

    public static class HistogramItem<T> {

        private final List<T> items;
        private final Predicate<T> predicate;

        private HistogramItem(final Predicate<T> predicate) {
            this.items = Lists.newArrayList();
            this.predicate = predicate;
        }

        public Collection<T> getItems() {
            return Collections.unmodifiableList(items);
        }

        private void addItem(final T item) {
            items.add(item);
        }
    }
}
