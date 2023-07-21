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

        final int INF = 101; //최대 100단계

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    arr[i][j] = 0;
                    continue;
                }
                arr[i][j] = arr[j][i] = INF;
            }
        }

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
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        int min = INF; //최대 100단계
        int res = 0;
        //앞에서부터 탐색하는 경우 : min과 sum을 비교할 때 무조건 작은 경우만 탐색하면 된다.
        //뒤에서부터 탐색하는 경우 : 결과값이 같아도 가장 작은 사람의 번호가 저장됨
        // >> 취향차이
        for (int i = N - 1; i >= 0; i--) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += arr[i][j];
            }

            if (min >= sum) {
                min = sum;
                res = i;
            }
        }

        System.out.println(++res); //인덱스 매칭

    }
}
