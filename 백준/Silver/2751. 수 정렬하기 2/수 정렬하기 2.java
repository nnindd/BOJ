import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] count = new int[2_000_001];
        for (int i = 0; i < N; i++) {
            count[Integer.parseInt(br.readLine())+1_000_000]++;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) continue;
            sb.append(i-1_000_000).append("\n");
        }

        System.out.println(sb);

    }

}
