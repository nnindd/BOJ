import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        int num;
        for (int i = 0; i < M; i++) {
            num = Integer.parseInt(tokens.nextToken());

            System.out.println(Arrays.binarySearch(arr, num) >= 0 ? 1 : 0);
        }
    }
}
