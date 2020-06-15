package cn.wengzi;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;

/**
 * Unit test for simple Application.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    public static void main(String[] args) {
        Arrays.asList("a", "b", "d").forEach(e -> {
            System.out.println(e);
        });

        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));

        short s1 = 1;
        s1 = (short) (s1 + 1);
        System.out.println(s1);
    }

    @Test
    public void test() {

    }
}
