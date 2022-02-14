package msdingfield;

import org.junit.jupiter.api.Test;

import static msdingfield.TestUtil.newBox;
import static org.assertj.core.api.Assertions.assertThat;

public class BoxTest {

    @Test
    public void testFitsIn() {
        assertThat(newBox(1, 20, 2).fitsIn(newBox(1, 20, 2))).isFalse();
        assertThat(newBox(1, 20, 2).fitsIn(newBox(1.1, 20.1, 2.1))).isTrue();
        assertThat(newBox(1, 20, 2).fitsIn(newBox(1.1, 2.1, 20.1))).isTrue();
        assertThat(newBox(1, 20, 2).fitsIn(newBox(20.1, 1.1, 2.1))).isTrue();
        assertThat(newBox(1, 20, 2).fitsIn(newBox(20.1, 2.1, 1.1))).isTrue();
        assertThat(newBox(1, 20, 2).fitsIn(newBox(2.1, 20.1, 1.1))).isTrue();
        assertThat(newBox(1, 20, 2).fitsIn(newBox(2.1, 1.1, 20.1))).isTrue();
    }
}
