import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        //최대 중량 = 가장 작은 하중 * 로프의 수
        int cnt = 0;
        int min = Integer.MAX_VALUE;
        int w = 0;
        int max = 0;

        for (int i = N-1; i >= 0 ; i--) {
            min = Math.min(min, arr[i]); //가장 작은 무게 갱신
            cnt++; //로프 추가
            w = min * cnt;
            if(w > max){
                max = w;
            }
        }
        System.out.println(max);
    }
}
