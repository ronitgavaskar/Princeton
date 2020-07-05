import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class QuickUnion {

    int[] indices;
    public QuickUnion(int n) {
        indices = new int[n];
        initialize(n);
    }

    void union(int p, int q) {
        int p_root = root(p);
        int q_root = root(q);
        indices[p_root] = q_root;
    }

    boolean connected(int p, int q) {
        int p_root = root(p);
        int q_root = root(q);
        return p_root == q_root;
    }

    int find(int p) {
        return root(p);
    }

    int count() {
        HashSet<Integer> roots = new HashSet<>();
        for (int index: indices) {
            roots.add(root(index));
        }
        return roots.size();
    }

    int root(int p) {
        while(indices[p] != p) {
            p = indices[p];
        }
        return p;
    }

    void initialize(int n) {
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./resources/sample.txt"));
        int N = sc.nextInt();

        QuickFind quickFind = new QuickFind(N);

        while(sc.hasNextInt()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if(!quickFind.connected(p, q)) {
                quickFind.union(p, q);
                System.out.println(p + " " + q);
            }
        }
        System.out.println(quickFind.count());
    }
}
