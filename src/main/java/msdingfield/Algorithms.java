package msdingfield;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Algorithms on boxes
 */
@UtilityClass
public class Algorithms {
    public static Comparator<Box> BOX_VOLUME_COMPARATOR = Box::compareByVolume;

    /**
     * Take a collection of boxes and compute an arrangement of nested boxes.
     *
     * @param boxes Boxes to nest
     * @return List of stacks of nested boxes
     */
    public static List<List<Box>> nestBoxes(Iterable<Box> boxes) {
        List<List<Box>> stacks = new ArrayList<>();
        boxes.forEach(box -> {
            boolean placed = false;
            for (List<Box> stack : stacks) {
                if (stack.get(0).fitsIn(box)) {
                    stack.add(0, box);
                    placed = true;
                    break;
                } else if (box.fitsIn(stack.get(stack.size()-1))) {
                    stack.add(box);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                List<Box> newStack = new ArrayList<>();
                newStack.add(box);
                stacks.add(newStack);
            }
        });

        return stacks;
    }

    /**
     * Parse a csv file of box dimensions
     * @param filename Name of input file
     * @return List of boxes
     * @throws IOException if an IO error occurs
     */
    public static List<Box> parseCsvFile(String filename) throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(filename))) {
            return parseCsv(r);
        }
    }

    /**
     * Parse a csv input stream of box dimensions
     * @param reader Input stream to read
     * @return List of obxes
     */
    public static List<Box> parseCsv(BufferedReader reader) {
        return reader.lines().map(
                s -> {
                    String[] parts = s.split(",");
                    if (parts.length != 3) {
                        System.out.format("Expected 3 columns: %s%n", s);
                        return null;
                    } else {
                        try {
                            return new Box(
                                    Distance.parse(parts[0]),
                                    Distance.parse(parts[1]),
                                    Distance.parse(parts[2])
                            );
                        } catch (ParseException e) {
                            System.out.println(e.getLocalizedMessage());
                            return null;
                        }
                    }
                }
        ).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
