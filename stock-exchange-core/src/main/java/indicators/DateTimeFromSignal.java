package indicators;

import com.google.common.base.Function;
import org.joda.time.DateTime;

/**
* Created by mht on 22/11/14.
*/
public class DateTimeFromSignal implements Function<Signal, DateTime> {

    @Override
    public DateTime apply(final Signal signal) {
        return signal.getDate();
    }
}
