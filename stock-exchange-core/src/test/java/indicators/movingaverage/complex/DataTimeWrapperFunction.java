package indicators.movingaverage.complex;

import com.google.common.base.Function;
import indicators.Signal;
import org.joda.time.DateTime;

/**
* Created by mht on 28/11/14.
*/
class DataTimeWrapperFunction implements Function<DateTime, Signal> {

    @Override
    public Signal apply(DateTime dateTime) {
        return new Signal(dateTime);
    }
}
