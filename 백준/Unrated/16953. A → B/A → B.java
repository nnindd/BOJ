import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        String A = tokens.nextToken();
        String B = tokens.nextToken();

        res = Integer.MAX_VALUE;

        dfs(A, B, 1);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
    private static void dfs(String A, String B, int cnt) {
        if (res < cnt || Long.parseLong(A) > Long.parseLong(B) || A.length() > B.length()) { //백트래킹
            return;
        }

        if (B.equals(A)) {
            res = Math.min(res, cnt);
            return;
        }
        //2를 곱함
        dfs(Long.toString(Long.parseLong(A)*2), B, cnt + 1);
        //끝에 1을 붙임
        dfs(A + "1", B, cnt + 1);
    }
}
