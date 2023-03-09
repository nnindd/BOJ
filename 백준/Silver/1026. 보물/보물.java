import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        int N, a[], b[];

        N = Integer.parseInt(br.readLine());
        a = new int[N];
        b = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(tokens.nextToken());
        }
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            b[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += a[i] * b[N-1-i];
        }
        System.out.println(sum);

    }
}
