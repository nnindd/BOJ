import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 1000 - Integer.parseInt(br.readLine());

        int[] coin = {500, 100, 50, 10, 5 ,1};
        int cnt = 0;
        int idx = 0;
        while(idx < coin.length){
            if(N <= 0)
                break;
            cnt += N / coin[idx];
            N %= coin[idx];
            idx++;
        }
        System.out.println(cnt);
    }
}