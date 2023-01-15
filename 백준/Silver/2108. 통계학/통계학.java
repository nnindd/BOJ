import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        int[] cnt = new int[8001];

        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
            cnt[arr[i]+4000]++;
        }

        //산술평균
        double avg = sum * 1.0 / N;
        sb.append(Math.round(avg)).append("\n");

        //중앙값
        Arrays.sort(arr);
        sb.append(arr[N/2]).append("\n");

        //최빈값
        int max = 0; //카운트 저장
        for (int i = 0; i < 8001; i++) {
            if(cnt[i] > max){
                max = cnt[i];
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 8001; i++) {
            if(cnt[i] == max){
                list.add(i-4000);
            }
            if(list.size() == 2){
                break;
            }
        }
        if(list.size() > 1){
            sb.append(list.get(1)).append("\n");
        }else{
            sb.append(list.get(0)).append("\n");
        }

        //범위
        sb.append(arr[N-1] - arr[0]).append("\n");

        System.out.println(sb);
    }

}
