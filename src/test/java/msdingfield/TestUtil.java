package msdingfield;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtil {

    public static Box newBox(double h, double w, double l) {
        return new Box(
                new Distance(h, "inch"),
                new Distance(w, "inch"),
                new Distance(l, "inch")
        );
    }

    public static Box newBox(String h, String w, String l) {
        return new Box(
                dis(h),
                dis(w),
                dis(l)
        );
    }

    public static Distance dis(String s) {
        return Distance.parse(s);
    }

}
