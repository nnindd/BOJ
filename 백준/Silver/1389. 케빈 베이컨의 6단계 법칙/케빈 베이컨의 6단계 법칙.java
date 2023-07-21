import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        int[][] arr = new int[N][N];

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken()) - 1;
            int b = Integer.parseInt(tokens.nextToken()) - 1;

            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    if(arr[i][k] == 0 || arr[k][j] == 0) continue;
                    if(arr[i][j] == 0){
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }else{
                        arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                    }
                }
            }
        }

        int p = N + 1;
        int min = Integer.MAX_VALUE;
        for (int i = N - 1; i >= 0; i--) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += arr[i][j];
            }
            if (min >= sum) {
                min = sum;
                p = i;
            }
        }
        System.out.println(++p);
    }
}
