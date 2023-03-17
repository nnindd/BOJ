import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        //도시 사이 거리
        long[] dist = new long[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(br.readLine());
        int cost = Integer.parseInt(tokens.nextToken()); //가장 처음 값
        
        //초기값 설정. 처음 도시는 다음 거리만큼 충전해야 함
        long min = cost;
        long result = min * dist[0];
        
        //두번째 도시부터 설정
        for (int i = 1; i < N-1; i++) {
            cost = Integer.parseInt(tokens.nextToken());
            min = cost < min ? cost : min; //이전 값이랑 비교해서 싼 값 저장
            result += (min * dist[i]);
        }// end input

        System.out.println(result);

    }
}
