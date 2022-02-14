package msdingfield;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class NestedBoxes
{
    public static void main( String[] args ) throws IOException {
        if (args.length != 1) {
            System.out.format("Usage: kata-nested-boxes <file name>%n");
            return;
        }
        String filename = args[0];
        List<Box> boxes = Algorithms.parseCsvFile(filename);

        System.out.println("Boxes sorted by volume.");
        boxes
                .stream()
                .sorted(Algorithms.BOX_VOLUME_COMPARATOR.reversed())
                .forEach(box ->
                        System.out.format("%s, %s, %s, %f m^3%n"
                            , box.getHeight()
                            , box.getWidth()
                            , box.getHeight()
                            , box.volumeInMeters()
                        )
                );

        System.out.println("Nested boxes");
        Algorithms.nestBoxes(
                boxes
        ).forEach(stack -> System.out.println(
                stack
                        .stream()
                        .map(Box::formatTuple)
                        .collect(Collectors.joining(","))
        ));
    }

}
