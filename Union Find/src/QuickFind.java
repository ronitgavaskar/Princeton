import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class QuickFind {

    int[] indices;
    public QuickFind(int n) {
        indices = new int[n];
        initialize(n);
    }

    void union(int p, int q) {
        int pid = indices[p];
        int qid = indices[q];

        for (int i = 0; i < indices.length; i++) {
            if (indices[i] == pid) {
                indices[i] = qid;
            }
        }
    }

    boolean connected(int p, int q) {
        return indices[p] == indices[q];
    }

    int find(int p) {
        return indices[p];
    }

    int count() {
        HashSet<Integer> uniqueIds = new HashSet<>();
        for (int index : indices) {
            uniqueIds.add(index);
        }
        return uniqueIds.size();
    }

    void initialize(int n) {
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
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
    }

}
