package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.Objects;


public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (capacity() > fillCount){
            rb[last] = x;
            last = (last + 1) % capacity();
            fillCount += 1;
        } else {
            throw new RuntimeException("Ring buffer overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount > 0) {
            T item = rb[first];
            rb[first] = null;
            first = (first + 1) % capacity();
            fillCount -= 1;
            return item;
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (!isEmpty()) {
            return rb[first];
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> a = (ArrayRingBuffer<T>) o;
        if (this.fillCount != a.fillCount) {
            return false;
        }
        int j = 0;
        for (T i : a){
            if ( !rb[j].equals(i)) {
                return false;
            }
            j+=1;
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{
        private int curPos;

        public ArrayIterator() {
            curPos = 0;
        }

        @Override
        public boolean hasNext() {
//            if (!isFull()){
//                return curPos < fillCount;
//            } else {
//                return true;
//            }
            return curPos < fillCount;
        }

        @Override
        public T next() {
            T item = rb[curPos];
            curPos = curPos + 1;
            return item;
        }
    }
}
