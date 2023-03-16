import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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

            int[][] arr = new int[N][2];

            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                //서류, 면접
                arr[i][0] = Integer.parseInt(tokens.nextToken());
                arr[i][1] = Integer.parseInt(tokens.nextToken());
            }

            //서류 기준 오름차순 정렬
            Arrays.sort(arr, (a, b) -> a[0]-b[0]);

            int cnt = N;
            int max = arr[0][1]; //서류 1등의 면접 점수 저장
            for (int i = 1; i < N; i++) {
                if(arr[i][1] < max){//현재 1등의 등수보다 i의 면접 등수가 높으면 최대값 변경
                    max = arr[i][1];
                }else{ //현재 1등의 면접 점수보다 낮으면 선발 안 함
                    cnt--;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}