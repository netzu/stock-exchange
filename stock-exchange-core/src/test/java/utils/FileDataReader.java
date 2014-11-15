package utils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import configuration.Share;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by mht on 15/11/14.
 */
public class FileDataReader {

    public static Function<String, Double> DOUBLE_FUNCTION = new DoubleTransformationFunction();
    public static Function<String, DateTime> DATE_FUNCTION = new DateTimeTransformationFunction();

    public static <T> List<T> readData(final File input, final Function<String, T> lineTransformationFunction) throws IOException {

        BufferedReader buffRead = null;

        try {
            buffRead = new BufferedReader(new FileReader(input));
            String line;

            final List<T> result = Lists.newArrayList();

            while ((line = buffRead.readLine()) != null) {

                result.add(lineTransformationFunction.apply(line));

            }
            return result;
        } finally {
            if (null != buffRead) {
                buffRead.close();
            }
        }


    }

    public static class DoubleTransformationFunction implements Function<String, Double> {

        private DoubleTransformationFunction() {}

        @Override
        public Double apply(final String s) {
            return Double.valueOf(s);
        }
    }

    public static class DateTimeTransformationFunction implements Function<String, DateTime> {

        private DateTimeTransformationFunction() {}

        @Override
        public DateTime apply(final String s) {
            return Share.COMMON_FORMATTER.parseDateTime(s);
        }
    }

}
