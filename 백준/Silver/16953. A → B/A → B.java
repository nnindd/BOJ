import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        Long A = Long.parseLong(tokens.nextToken());
        Long B = Long.parseLong(tokens.nextToken());

        res = Integer.MAX_VALUE;

        dfs(A, B, 1);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
    private static void dfs(long A, long B, int cnt) {
        if (res < cnt || A > B) { //백트래킹
            return;
        }

        if (B == A) {
            res = Math.min(res, cnt);
            return;
        }
        //2를 곱함
        dfs(A * 2, B, cnt + 1);
        //끝에 1을 붙임
        dfs(Long.parseLong(Long.toString(A) + "1"), B, cnt + 1);
    }
}
