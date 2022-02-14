package msdingfield;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static msdingfield.TestUtil.dis;
import static msdingfield.TestUtil.newBox;
import static org.assertj.core.api.Assertions.assertThat;

public class AlgorithmsTest {

    @Test
    public void testSort() {
        List<Box> sorted = Stream.of(
                newBox("3 inch", "2inch","6inch"),
                newBox(".6 m", "30 cm", "50 cm"),
                newBox("300 mm", "100 mm", "200 mm")
        )
                .sorted(Algorithms.BOX_VOLUME_COMPARATOR)
                .collect(Collectors.toList());
        assertThat(sorted.get(0).volumeInMeters()).isCloseTo(0.0005899, Percentage.withPercentage(0.01));
        assertThat(sorted.get(1).volumeInMeters()).isCloseTo(0.006, Percentage.withPercentage(0.01));
        assertThat(sorted.get(2).volumeInMeters()).isCloseTo(0.09, Percentage.withPercentage(0.01));
    }

    @Test
    public void testNestBoxes() {
        List<Box> boxes = List.of(
                newBox("8 inch", "6 inch", "16 inch"),
                newBox("16 inch", "24.5 inch", "13 inch"),
                newBox("100mm", "200mm", "200mm"),
                newBox("0.75 m", "1 m", "30cm")
        );
        List<List<Box>> stacks = Algorithms.nestBoxes(boxes);
        assertThat(stacks).hasSize(2);

        List<Box> stack1 = stacks.get(0);
        assertThat(stack1).hasSize(3);
        assertThat(stack1.get(0).getHeight()).isEqualTo(dis("16 inch"));
        assertThat(stack1.get(1).getHeight()).isEqualTo(dis("8 inch"));
        assertThat(stack1.get(2).getHeight()).isEqualTo(dis("100 mm"));

        List<Box> stack2 = stacks.get(1);
        assertThat(stack2).hasSize(1);
        assertThat(stack2.get(0).getHeight()).isEqualTo(dis(".75 m"));
    }

    @Test
    public void testParseCsv() {
        List<Box> boxes = Algorithms.parseCsv(
                new BufferedReader(new StringReader("3 inch, 2 inch, 6 inch\n" +
                        "0.6 m, 30 cm, 50 cm\n"))
        );
        assertThat(boxes).hasSize(2);
        assertThat(boxes.get(0)).isEqualTo(newBox("3 inch", "2 inch", "6 inch"));
        assertThat(boxes.get(1)).isEqualTo(newBox("0.6 m", "30 cm", "50 cm"));
    }
}
