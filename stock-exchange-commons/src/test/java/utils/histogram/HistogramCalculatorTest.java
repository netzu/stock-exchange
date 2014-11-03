package utils.histogram;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class HistogramCalculatorTest {


    private HistogramCalculator<Integer> histogramCalculator;


    @Test
    public void resultSizeIsSameAsSizeOfPredicates() {
        setupHistogramCalculator(0, 10, 2);

        Collection<HistogramCalculator.HistogramItem<Integer>> result = histogramCalculator.calculateHistogram(Arrays.asList(1, 2));

        assertThat(result).isNotNull();
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.size()).isEqualTo(5);


    }


    private void setupHistogramCalculator(final int start, final int end, final int step) {

        this.histogramCalculator = new HistogramCalculator<Integer>(createPredicatesBuckets(start, end, step));

    }

    private Collection<Predicate<Integer>> createPredicatesBuckets(final int start, final int end, final int step) {

        List<Predicate<Integer>> result = Lists.newArrayList();
        for (int i = start; i < end; i += step) {

            result.add(Range.closedOpen(i, i + step));

        }

        return result;
    }


}