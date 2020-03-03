package es.datastructur.synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testPart2() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue(0.1);
        arb.enqueue(0.2);
        arb.enqueue(0.9);
        arb.enqueue(0.8);
        Assert.assertEquals(0.1, arb.dequeue());
        arb.enqueue(1.1);
        Assert.assertEquals(0.2, arb.peek());
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer expect = new ArrayRingBuffer(4);
        expect.enqueue(0.1);
        expect.enqueue(0.2);
        expect.enqueue(0.9);
        expect.enqueue(0.8);

        ArrayRingBuffer actual = new ArrayRingBuffer(4);
        actual.enqueue(0.1);
        actual.enqueue(0.2);
        actual.enqueue(0.9);
        actual.enqueue(0.3);

        boolean bool = expect.equals(actual);

        Assert.assertEquals(false, bool);
    }
}
