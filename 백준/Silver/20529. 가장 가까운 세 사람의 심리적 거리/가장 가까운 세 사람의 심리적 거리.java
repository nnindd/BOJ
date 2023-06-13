import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            tokens = new StringTokenizer(br.readLine());

            //32개가 넘으면 무조건 0이 됨. 이미 같은 유형이 2개씩 존재하기 때문
            if (N > 32) {
                sb.append(0).append("\n");
                continue;
            }

            String[] type = new String[N];
            for (int i = 0; i < N; i++) {
                type[i] = tokens.nextToken();
            }

            //조합으로 선택
            res = Integer.MAX_VALUE;
            comb(0, 0, new String[3], type, N);
            sb.append(res).append("\n");

        }
        System.out.print(sb);
    }

    private static void comb(int cnt, int start, String[] s, String[] type, int N) {
        if (cnt == 3) {
            int temp = 0;
            for (int i = 0; i < 4; i++) {
                if (s[0].charAt(i) != s[1].charAt(i)) temp++;
                if (s[0].charAt(i) != s[2].charAt(i)) temp++;
                if (s[1].charAt(i) != s[2].charAt(i)) temp++;
            }
            res = Math.min(res, temp);
            return;
        }

        for (int i = start; i < N; i++) {
            s[cnt] = type[i];
            comb(cnt+1, i+1, s, type, N);
        }
    }
}
