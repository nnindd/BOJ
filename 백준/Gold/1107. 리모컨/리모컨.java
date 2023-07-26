import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, res;
    static boolean[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        numbers = new boolean[10];

        if (M != 0) {
            StringTokenizer tokens = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                numbers[Integer.parseInt(tokens.nextToken())] = true;
            }
        }

        res = Math.abs(100 - N); //+/-키로만 이동
        if (N == 100) {
            res = 0;
        } else {
            dfs("");
        }
        System.out.println(res);
    }

    private static void dfs(String s) {
        for (int i = 0; i < 10; i++) {
            if (numbers[i]) continue;
            String str = s + i;
            res = Math.min(res, Math.abs(N - Integer.parseInt(str)) + str.length());

            if (str.length() < 6) {
                dfs(str);
            }
        }
    }
}
