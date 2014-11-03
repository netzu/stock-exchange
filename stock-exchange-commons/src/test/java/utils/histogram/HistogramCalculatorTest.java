package utils.histogram;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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


    @Test
    public void emptyInputDataCauseAllBucketsAreEmpty() {
        setupHistogramCalculator(0,10,2);

        Collection<HistogramCalculator.HistogramItem<Integer>> result = histogramCalculator.calculateHistogram(Collections.<Integer>emptyList());


        assertThat(result.isEmpty()).isFalse();
        assertThat(result.size()).isEqualTo(5);

        for (final HistogramCalculator.HistogramItem<Integer> resultItem : result) {
            assertThat(resultItem.getItems().isEmpty()).isTrue();
        }
    }

    @Test
    public void emptyBucketsCauseEmptyResult() {
        final HistogramCalculator<Integer> histogramCalculator = new HistogramCalculator<Integer>(Collections.<Predicate<Integer>>emptyList());

        Collection<HistogramCalculator.HistogramItem<Integer>> result = histogramCalculator.calculateHistogram(Arrays.asList(1, 2, 3, 4, 5));

         assertThat(result.isEmpty()).isTrue();

    }

    @Test
    public void allBucketsHasSingleElement() {
        setupHistogramCalculator(0,10,2);
        Collection<HistogramCalculator.HistogramItem<Integer>> result = histogramCalculator.calculateHistogram(Arrays.asList(0, 3, 5, 6, 8));

        assertThat(result.size()).isEqualTo(5);

        assertBucketSize(0,1,result);
        assertBucketSize(3,1,result);
        assertBucketSize(5,1,result);
        assertBucketSize(6,1,result);
        assertBucketSize(8,1,result);


    }

    @Test
    public void multipleValuesInSameBucket() {
        setupHistogramCalculator(0,10,2);
        Collection<HistogramCalculator.HistogramItem<Integer>> result = histogramCalculator.calculateHistogram(Arrays.asList(0, 3, 1,4, 5, 6 ,8,9));

        assertThat(result.size()).isEqualTo(5);

        assertBucketSize(0,2,result);
        assertBucketSize(1,2,result);
        assertBucketSize(3,1,result);
        assertBucketSize(4,2,result);
        assertBucketSize(5,2,result);
        assertBucketSize(6,1,result);
        assertBucketSize(8,2,result);
        assertBucketSize(9,2,result);
    }

    @Test
    public void itemsRejectedByAllBuckets() {

        setupHistogramCalculator(0,10,2);
        Collection<HistogramCalculator.HistogramItem<Integer>> result = histogramCalculator.calculateHistogram(Arrays.asList(0, 3, 1,4, 5, 6 ,8,9, 15 ,11,90));

        assertThat(result.size()).isEqualTo(5);

        assertBucketSize(0,2,result);
        assertBucketSize(1,2,result);
        assertBucketSize(3,1,result);
        assertBucketSize(4,2,result);
        assertBucketSize(5,2,result);
        assertBucketSize(6,1,result);
        assertBucketSize(8,2,result);
        assertBucketSize(9,2,result);
    }


    @Test
    public void alItemsNotMatchedToAnyBucket() {

        setupHistogramCalculator(0,10,2);
        Collection<HistogramCalculator.HistogramItem<Integer>> result = histogramCalculator.calculateHistogram(Arrays.asList(33,55,66,78,123,90));

        assertThat(result.size()).isEqualTo(5);

        for (final HistogramCalculator.HistogramItem<Integer> resultItem : result) {
            assertThat(resultItem.getItems().isEmpty()).isTrue();
        }

    }



    private void assertBucketSize(final Integer itemValue, final int expectedBucketSize, final Collection<HistogramCalculator.HistogramItem<Integer>> inputResult) {
        for (final HistogramCalculator.HistogramItem<Integer> resultItem : inputResult) {
            if (resultItem.getPredicate().apply(itemValue)) {
                assertThat(resultItem.getItems()).contains(itemValue);
                assertThat(resultItem.getItems().size()).isEqualTo(expectedBucketSize);
            }
        }

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