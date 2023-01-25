import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(tokens.nextToken());
        int N = Integer.parseInt(tokens.nextToken());

        int[] arr = new int[K];

        long start = 0;
        long end = 0; //가장 끝 길이

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            if (end < arr[i]) { //가장 긴 길이 저장
                end = arr[i];
            }
        }

        //0으로 나누지 않게
        end++;

        long mid = 0;
        while (start < end) {
            mid = (start + end) / 2; //기준점 설정
            long sum = 0;

            for (int i : arr) {
                sum += (i / mid); //몫 저장 = 자른 랜선 개수
            }

            //개수가 모자르면 더 짧게 잘라야 함
            //개수가 넘치면 더 길게 잘라보기
            if (sum < N) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start - 1);
    }

}
