import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        int arr[] = new int[N];
        tokens = new StringTokenizer(br.readLine());

        int up = 0;
        int down = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
            if(up < arr[i]) up = arr[i];
        }

        //중간 지점을 선택해서 자름
        //자른 합이 크다면 위의 높이를 탐색
        //자른 합이 작다면 아래의 높이를 탐색

        int mid;
        long sum;

        while (down < up){
            mid = (up + down) / 2; //높이의 중간 지점을 자름
            sum = 0;

            //잘린 길이를 저장
            for(int h : arr){
                //mid보다 작으면 자르지 않는다
                if(h <= mid) continue;
                sum += (h - mid);
            }

            //합이 M과 같다면 결과 출력
//            if(sum == M){
//                System.out.println(mid);
//                break;
//            }
            //합이 M보다 크다면 위의 높이 탐색
            if (sum < M) {
                up = mid;
            }
            //합이 M보다 작다면 아래 높이 탐색
            else{
                down = mid + 1;
            }
        }
        System.out.println(down - 1);
    }
}