/*public class ArrayDeque<T> implements Deque<T> {

    private int size;
    private T[] items;
    private int first_ind = 0;
    private int array_size = 8;

    public ArrayDeque() {
        items = (T []) new Object[array_size];
        size = 0;
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    @Override
    public void addFirst(T item){
        if (items.length == size) {
            array_size *= 2;
            resize(array_size);
            first_ind = -1;
        }
        if (first_ind<0){
            first_ind += array_size;
        }
        items[first_ind] = item;
        first_ind -= 1;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (items.length == size) {
            array_size *= 2;
            resize(array_size);
            first_ind = -1;
        }
        if (first_ind<0){
            first_ind += array_size;
        }
        items[(first_ind+size+1)%array_size] = item;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 1; i<=size; i++) {
            System.out.print(items[(first_ind + i)%array_size] + " ");
        }

    }

    @Override
    public T removeFirst() {
        first_ind += 1;
        size -= 1;
        return items[first_ind];
    }

    @Override
    public T removeLast() {
        size -= 1;
        return items[(first_ind + size + 2)%array_size];
    }

    @Override
    public T get(int index) {
        return items[(first_ind + index + 1)%array_size];
    }


}

 There are mistakes in this function*/
