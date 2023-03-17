import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        //도시 사이 거리
        long D = 0;
        int[] dist = new int[N-1];
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(tokens.nextToken());
            D += dist[i];
        }

        int[] cost = new int[N];
        int min = Integer.MAX_VALUE;
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(tokens.nextToken());
            if(i != N-1 && min > cost[i]){
                min = cost[i]; //마지막 도시를 제외하고 가장 싼 도시 저장
            }
        }// end input

        //싼 곳에서 가장 많이 충전하기
        int idx = 0; //0번째 도시부터 출발
        long result = 0;
        while(D > 0){
            if(cost[idx] == min){
                //가장 싼 도시라면 현재 남은 거리를 모두 충전
                result += (cost[idx] * D);
                D = 0;
            }else{
                //아니라면 필요한 거리만 충전하고 남은 거리 차감
                result += (cost[idx] * dist[idx]);
                D -= dist[idx];
            }
            idx++;
        }

        System.out.println(result);

    }
}
