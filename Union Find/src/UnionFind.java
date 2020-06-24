import java.util.*;

public class UnionFind {

    int numPoints;
    HashMap<Integer, HashSet<Integer>> components;
    public UnionFind(int numPoints) {
        this.numPoints = numPoints;
        components = new HashMap<>();
        initializeComponents();
    }

    public void union(int p, int q) {
        int compA = find(p);
        int compB = find(q);

        if (compA == compB) return;
        components.get(compA).addAll(components.get(compB));
        components.remove(compB);
    }

    public int find(int num) {
        for (int key: components.keySet()) {
            if (components.get(key).contains(num)) return key;
        }

        return -1;
    }

    public void initializeComponents() {
        for (int i = 0; i < numPoints; i++) {
            HashSet<Integer> set = new HashSet<>();
            set.add(i);
            components.put(i, set);
        }
    }

    public int count() {
        return components.size();
    }

    public boolean connected(int p, int q) {
        return components.get(find(p)).contains(q);
    }

    public void print() {
        int i = 0;
        for (int key: components.keySet()) {
            System.out.println(i++ + ": " + components.get(key));
        }
    }

    public static void main(String[] args) {

        /** Test 1 **/
        System.out.println("***********");
        System.out.println("TEST 1");
        System.out.println("***********");
        UnionFind uf = new UnionFind(4);
        uf.union(1,2);
        uf.union(0,3);
        boolean isConnected = uf.connected(0,3);
        System.out.println("0 is " + ((isConnected) ? "" : "not ") + "connected to 3");
        isConnected = uf.connected(1,3);
        System.out.println("1 is " + ((isConnected) ? "" : "not ") + "connected to 3");
        System.out.println("There are " + uf.count() + " components");

        /** Test 2 **/
        System.out.println("***********");
        System.out.println("TEST 2");
        System.out.println("***********");
        uf = new UnionFind(10);
        uf.union(4, 3);
        uf.union(3,8);
        uf.union(6,5);
        uf.union(9,4);
        uf.union(2,1);
        isConnected = uf.connected(0,7);
        System.out.println("0 is " + ((isConnected) ? "" : "not ") + "connected to 7");
        isConnected = uf.connected(8,9);
        System.out.println("8 is " + ((isConnected) ? "" : "not ") + "connected to 9");
        uf.union(5,0);
        uf.union(7,2);
        uf.union(6,1);
        uf.union(1,0);
        isConnected = uf.connected(0,7);
        System.out.println("0 is " + ((isConnected) ? "" : "not ") + "connected to 7");
    }
}
