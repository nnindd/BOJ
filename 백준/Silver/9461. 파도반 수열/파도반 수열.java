import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //int 배열을 사용하면 오버플로우 발생
        long[] arr = new long[101];
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        arr[4] = 2;
        arr[5] = 2;
        //이후 값은 arr[n-1] + arr[n-5] 값을 따름
        for (int i = 6; i < arr.length; i++) {
            arr[i] = arr[i-1] + arr[i-5];
        }

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            sb.append(arr[N]).append("\n");
        }
        System.out.println(sb);
    }
}
