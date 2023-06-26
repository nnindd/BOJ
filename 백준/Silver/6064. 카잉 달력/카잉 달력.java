import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            tokens = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(tokens.nextToken());
            int N = Integer.parseInt(tokens.nextToken());
            int x = Integer.parseInt(tokens.nextToken()) - 1;
            int y = Integer.parseInt(tokens.nextToken()) - 1;

            boolean flag = false;
            for (int i = x; i < (N * M); i += M) {
                if (i % N == y) {
                    sb.append(i + 1);
                    flag = true;
                    break;
                }

            }
            if (!flag) {
                sb.append(-1);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}