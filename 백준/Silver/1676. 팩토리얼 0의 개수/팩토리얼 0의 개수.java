import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //5가 곱해질때마다 0의 개수 증가
        int cnt = 0;
        while(N >= 5){
            cnt += N/5;
            N /= 5;
        }
        System.out.println(cnt);
    }
}
