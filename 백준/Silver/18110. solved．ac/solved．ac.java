import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int res = 0;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            res += arr[i];
        }

        Arrays.sort(arr);

        int sub = (int) Math.round(N * 0.15);
        for (int i = 0; i < sub; i++) {
            res -= arr[i];
            res -= arr[N - i - 1];
        }

        System.out.println(Math.round((res * 1.0) / (N - sub * 2)));
    }
}
