import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        int[] coin = {25, 10, 5, 1};

        while (T-- > 0) {
            int C = Integer.parseInt(br.readLine());

            for (int i = 0; i < 4; i++) {
                sb.append(C / coin[i]);
                C %= coin[i];
                if (i == 3) continue;
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
