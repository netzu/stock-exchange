package utils.histogram;

import java.util.Collection;


public interface HistogramCalculator<T> {

	Collection<HistogramItem<T>> calculateHistogram(final Collection<T> input);
	

}
