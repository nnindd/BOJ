import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, parent[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        parent = new int[N];

        initArr();

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            union(Integer.parseInt(tokens.nextToken()) - 1, Integer.parseInt(tokens.nextToken()) - 1);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(find(i));
        }
        System.out.println(set.size());
    }

    private static void initArr() {
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return find(parent[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        if (a <= b) parent[b] = a;
        else parent[a] = b;
        return true;
    }
}
