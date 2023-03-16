import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int N;

        while (T-->0){
            N = Integer.parseInt(br.readLine());

            int[] arr = new int[N+1];

            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                //서류, 면접
                int idx = Integer.parseInt(tokens.nextToken());
                int val = Integer.parseInt(tokens.nextToken());
                
                arr[idx] = val;
            }

            //서류 기준 오름차순 정렬
            int top = arr[1];
            int cnt = 1; //서류 1등
            
            for (int i = 2; i <= N; i++) {
                if(arr[i] < top){
                    top = arr[i];
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}