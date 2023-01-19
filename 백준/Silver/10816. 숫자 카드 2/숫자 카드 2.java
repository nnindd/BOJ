import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        StringBuilder sb = new StringBuilder();

        final int PLUS = 10_000_000;
        int[] cnt = new int[20_000_001];

        int N = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cnt[Integer.parseInt(tokens.nextToken()) + PLUS]++; //음수 처리
        }

        int M = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sb.append(cnt[Integer.parseInt(tokens.nextToken()) + PLUS]).append(" ");
        }
        System.out.println(sb);
    }
}