import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 216 -> 216 + 2 + 1 + 6
        //216 -> 21 -> 2
        int result = 0;

        for (int i = 0; i < N; i++) {
            int num = i; //원래의 수
            int sum = 0;

            //자리수 더하기
            while (num != 0) {
                sum += num % 10; //216%10 = 6
                num /= 10; // 216/10 = 21
            }

            if(i + sum == N){
                result = i;
                break;
            }
        }
        System.out.println(result);
    }
}
