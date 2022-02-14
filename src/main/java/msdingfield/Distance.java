package msdingfield;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A distance measurement
 *
 * Represent a distance with unit.
 */
@Data
public class Distance implements Comparable<Distance> {
    /**
     * Value of distance
     */
    private final double value;

    /**
     * Unit of distance
     */
    private final String unit;

    /**
     * Supported unit types with number of units per meter
     */
    private static Map<String, Double> units = Map.of(
            "inch", 39.37008,
            "foot", 3.28084,
            "cm", 100.0,
            "mm", 1000.0,
            "m", 1.0
    );

    /**
     * Constructs a distance.
     *
     * @param value Distance value
     * @param unit Distance unit
     * @throws ParseException if unit is invalid
     */
    public Distance(double value, String unit) {
        this.value = value;
        this.unit = unit.toLowerCase();
        if (!units.containsKey(this.unit)) {
            throw new ParseException("Unknown unit type " + unit + ".");
        }
    }

    /**
     * Return distance in meters.
     * @return Distance value converted to meters
     */
    public double asMeters() {
        return value / units.get(unit);
    }

    /**
     * Parse string containing value and unit.
     *
     * @param s String to parse
     * @return Parsed distance
     * @throws ParseException if string does not contain parsable value and unit
     */
    public static Distance parse(String s) {
        Pattern p = Pattern.compile("\\s*([0-9.]*)\\s*(\\w*)");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            throw new ParseException("Cannot parse "+s+".");
        }
        String valueStr = m.group(1);
        String unit = m.group(2);

        if (StringUtils.isEmpty(valueStr)) {
            throw new ParseException("No value found in " + s + ".");
        }

        if (StringUtils.isEmpty(unit)) {
            throw new ParseException("No unit found in " + s + ".");
        }

        double value;
        try {
            value = Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid value found in " + s + ".");
        }

        return new Distance(value, unit);
    }

    public String toString() {
        return String.format("%f %s", value, unit);
    }

    @Override
    public int compareTo(Distance other) {
        return Double.compare(this.asMeters(), other.asMeters());
    }

}
