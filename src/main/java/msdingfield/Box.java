package msdingfield;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A box container
 */
@Data
@AllArgsConstructor
@ToString
class Box {
    /** Height of box */
    private Distance height;

    /** Width of box */
    private Distance width;

    /** Length of box */
    private Distance length;

    /**
     * Test if this box will fit inside of another box.
     *
     * This assumes box walls have zero thickness.
     *
     * @param other Potential outer box
     * @return true if this box will fit in the other box
     */
    public boolean fitsIn(Box other) {
        List<Distance> ourDims = this.sortedDims();
        List<Distance> otherDims = other.sortedDims();
        return ourDims.get(0).compareTo(otherDims.get(0)) < 0 &&
                ourDims.get(1).compareTo(otherDims.get(1)) < 0 &&
                ourDims.get(2).compareTo(otherDims.get(2)) < 0;
    }

    /**
     * Formats a string representation of the box as a triple.
     * @return String representation as (height, width, length)
     */
    public String formatTuple() {
        return String.format("(%s, %s, %s)", height, width, length);
    }

    public int compareByVolume(Box other) {
        return Double.compare(this.volumeInMeters(), other.volumeInMeters());
    }

    public double volumeInMeters() {
        return height.asMeters() * width.asMeters() * length.asMeters();
    }

    /**
     * Returns box dimensions in sorted order.
     * @return Box dimensions in sorted order.
     */
    private List<Distance> sortedDims() {
        List<Distance> dims = new ArrayList<>(List.of(height, width, length));
        Collections.sort(dims);
        return dims;
    }

}
