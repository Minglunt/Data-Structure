public class UnionFind {

    // TODO - Add instance variables?
    private int[] disjointset;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        disjointset = new int[n];
        for (int i = 0; i < disjointset.length; i++){
            disjointset[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex >= disjointset.length) {
            throw new IllegalArgumentException("index is out of range");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int i = v1;
        while (disjointset[i]>=0) {
            i = disjointset[i];
        }
        return -disjointset[i];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        if (disjointset[v1] >=0) {
            return disjointset[v1];
        } else {
            return -sizeOf(v1);
        }
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        while (disjointset[v1]>=0 || disjointset[v2]>=0) {
            if (disjointset[v1]>=0) {
                v1 = disjointset[v1];
            }
            if (disjointset[v2]>=0) {
                v2 = disjointset[v2];
            }
        }
        return v2==v1;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        while (parent(v1)>=0 || parent(v2)>=0) {
            if (parent(v1)>=0) {
                v1 = parent(v1);
            }
            if (parent(v2)>=0) {
                v2 = parent(v2);
            }
        }
        if (sizeOf(v1) > sizeOf(v2)) {
            int a = sizeOf(v2);
            disjointset[v2] = v1;
            disjointset[v1] -= a;
        } else {
            int b = sizeOf(v1);
            disjointset[v1] = v2;
            disjointset[v2] -= b;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        int v = vertex;
        while (parent(v)>=0){
            v = parent(v);
        }
        disjointset[vertex] = v;
        return v;
    }

}
