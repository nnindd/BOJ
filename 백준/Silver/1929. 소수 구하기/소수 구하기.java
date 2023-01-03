import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(tokens.nextToken());
        int N = Integer.parseInt(tokens.nextToken());

        int[] arr = new int[N+1];
        //0, 1, 2, 3, 5, 7, 11, 13 ...
        for(int i=2; i<=N; i++){
            arr[i] = i;
        }

        for(int i=2; i<=N; i++){
            if(arr[i]==0) continue;
            for(int j=i+i; j<=N; j+=i){ //배수 제거
                arr[j] = 0;
            }
        }

        for(int i=M; i<=N; i++){
            if(arr[i]==0) continue;
            System.out.println(arr[i]);
        }
    }
}
