import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        int[] arr = new int[3];

        while (true){
            tokens = new StringTokenizer(br.readLine());
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                arr[i] = Integer.parseInt(tokens.nextToken());
                sum += arr[i];
            }

            if(sum==0) break;

            Arrays.sort(arr);

            boolean flag = true;
            if(Math.pow(arr[0], 2) + Math.pow(arr[1], 2) != Math.pow(arr[2], 2)){
                flag = false;
            }

            System.out.println(flag ? "right" : "wrong");
        }
    }
}
