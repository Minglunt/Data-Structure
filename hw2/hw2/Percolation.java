package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int count = 0;
    private int size;
    private boolean[][] world;
    private WeightedQuickUnionUF map;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = N;
        world = new boolean[N][N];
        map = new WeightedQuickUnionUF(N*N+2);
    }

    private int twodToOne(int row, int col) {
        if (row < 0 || col < 0){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return row * size + col;
    }

    public void open(int row, int col) {
        if (row >= size || row < 0 || col >= size || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (world[row][col]) {
            return;
        }
        world[row][col] = true;
        count += 1;
        int position = twodToOne(row, col);
        if (row-1>=0 && isOpen(row-1, col)) {
            map.union(position, twodToOne(row-1, col));
        }
        if (row+1<size && isOpen(row+1, col)) {
            map.union(position, twodToOne(row+1, col));
        }
        if (col-1>=0 && isOpen(row, col-1)) {
            map.union(position, twodToOne(row, col-1));
        }
        if (col+1<size && isOpen(row, col+1)) {
            map.union(position, twodToOne(row, col+1));
        }

        if (row == 0) {
            map.union(position, size*size);
        }
        if (row == size-1) {
            map.union(position, size*size+1);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row >= size || row < 0 || col >= size || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return world[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row >= size || row < 0 || col >= size || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int position = twodToOne(row, col);
        return map.connected(position, size*size);
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return map.connected(size*size, size*size+1);
    }

    // Used for unit tests
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        //Test open and isOpen
        /*
        p.open(2,3);
        System.out.println(p.isOpen(3,3));
        System.out.println(p.isOpen(2,3));
         */

        // Test others
        p.open(2,4);
        p.open(0,3);
        p.open(1, 3);
        p.open(2,3);
        p.open(3,3);
        p.open(4,3);
        System.out.println(p.numberOfOpenSites());
        System.out.println(p.isFull(2,3));
        System.out.println(p.percolates());
    }

}
