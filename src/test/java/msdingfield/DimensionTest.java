package msdingfield;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static msdingfield.TestUtil.dis;
import static org.assertj.core.api.Assertions.assertThat;

public class DimensionTest
{
    @Test
    public void testCompare() {
        assertThat(dis("1 inch").compareTo(dis("1.01 inch"))).isLessThan(0);
        assertThat(dis("1 inch").compareTo(dis(".99 inch"))).isGreaterThan(0);
        assertThat(dis("1 inch").compareTo(dis("1.0 inch"))).isEqualTo(0);
        assertThat(dis("11 inch").compareTo(dis("1 foot"))).isLessThan(0);
        assertThat(dis(".99 m").compareTo(dis("3 foot"))).isGreaterThan(0);
    }

    @Test
    public void parseDimension()
    {
        Distance d;
        d = Distance.parse("3.14 inch");
        assertThat(d.getValue()).isCloseTo(3.14, Percentage.withPercentage(0.0001));
        assertThat(d.getUnit()).isEqualTo("inch");

        d = Distance.parse("3.14m");
        assertThat(d.getValue()).isCloseTo(3.14, Percentage.withPercentage(0.0001));
        assertThat(d.getUnit()).isEqualTo("m");

        d = Distance.parse("3.14foot");
        assertThat(d.getValue()).isCloseTo(3.14, Percentage.withPercentage(0.0001));
        assertThat(d.getUnit()).isEqualTo("foot");

        try {
            Distance.parse("3.14 xx");
        } catch (ParseException e) {
            assertThat(e.getMessage()).isEqualTo("Unknown unit type xx.");
        }

        try {
            Distance.parse("x3.14 m");
        } catch (ParseException e) {
            assertThat(e.getMessage()).isEqualTo("Cannot parse x3.14 m.");
        }
    }
}
