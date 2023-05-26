import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        int M = Integer.parseInt(br.readLine());

        StringTokenizer tokens;
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            union(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
        }

        int res = 0;
        for (int i = 1; i <= N; i++) {
            if (find(i) == 1) res++;
        }
        System.out.println(res-1); //감염된 자기 자신 제외

    }

    private static int find(int x) {
        if (arr[x] == x) return x;
        return find(arr[x]);
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;
        if (x <= y) arr[y] = x;
        else arr[x] = y;
        return true;
    }

}
