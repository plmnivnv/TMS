package util;

import java.util.Arrays;
import java.util.List;

public class TestUtils {
    public static String getString(int wantedSize) {
        return "x".repeat(wantedSize);
    }

    public static List<String> getList(int wantedSize) {
        return Arrays.asList(new String[wantedSize]);
    }

}
