import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int K = Integer.parseInt(tokens.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long cnt = 0; //동전 개수
        int idx = N - 1;

        //가장 끝에서부터 만들수 있는 수
        while (K > 0) {
            if (arr[idx] > K) {
                idx--;
                continue;
            }
            cnt += (K / arr[idx]);
            K = K % arr[idx];
            idx--;
        }
        System.out.println(cnt);
    }
}
