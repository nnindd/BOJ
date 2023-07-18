import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken()); //마을 수
        int C = Integer.parseInt(tokens.nextToken()); //트럭 용량

        int M = Integer.parseInt(br.readLine()); //박스 정보의 개수

        int[][] arr = new int[M][];

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            arr[i] = new int[]{Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken())};
        }

        //받는 마을 오름차순 (가까운 곳에 빨리 내려야 이득), 보내는 마을 오름차순
        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int[] box = new int[N + 1];
        int max, sum, res = 0;
        for (int i = 0; i < M; i++) {
            max = 0;

            //배송 구간에 최대 적재량 찾기
            for (int j = arr[i][0]; j < arr[i][1]; j++) {
                max = Math.max(max, box[j]);
            }

            //적재할 수 있는 최소 개수 (최대량에서 현재 적재량 뺀 것과 지금 박스)
            sum = Math.min(C - max, arr[i][2]);

            res += sum; //최종 적재량

            //해당 구간에 박스 양 더해줌
            for (int j = arr[i][0]; j < arr[i][1]; j++) {
                box[j] += sum;
            }
        }
        System.out.println(res);
    }
}