import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        StringTokenizer tokens;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(tokens.nextToken());
            arr[i][1] = Integer.parseInt(tokens.nextToken());
        }

        //끝나는 시간 오름차순, 같다면 시작 시간 오름차순
        Arrays.sort(arr, ((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        }));

        //가장 짧게 끝나는 회의 선정
        //다음으로 시작하는 시간 찾기

        int end = arr[0][1]; //가장 처음 회의가 끝나는 시간
        int cnt = 1; //첫번째 회의 선택

        for (int i = 1; i < N; i++) {
            if (arr[i][0] >= end) {
                end = arr[i][1]; //선택한 회의가 끝나는 시간으로 변경
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}