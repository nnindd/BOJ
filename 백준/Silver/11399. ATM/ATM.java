import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        //오름차순 정렬
        Arrays.sort(arr);

        int total = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            total += sum;
        }

        System.out.println(total);
    }
}
