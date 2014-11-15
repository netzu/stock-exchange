package indicators;

import org.joda.time.DateTime;

/**
 * Created by mht on 11/11/14.
 */
public class Signal {

    private final DateTime date;

    public Signal(final DateTime date) {
        this.date = date;
    }


    public DateTime getDate() {
        return date;
    }
}
