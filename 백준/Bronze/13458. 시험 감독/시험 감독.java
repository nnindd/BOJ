import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //시험장의 개수

        int[] map = new int[N];
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { //응시자의 수
            map[i] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(tokens.nextToken()); //총감독관
        int C = Integer.parseInt(tokens.nextToken()); //부감독관

        //총 감독관은 시험장 당 1명
        //부 감독관은 여러명 가능
        //감독관 최소

        long cnt = 0;
        for (int i = 0; i < N; i++) {
            //총감독관
            if (map[i] < B) {//총감독관이 모두 감독 가능
                cnt++;
                continue;
            }
            map[i] -= B;
            cnt++;

            //부감독관
            if (map[i] <= 0) continue;

            int a = map[i] / C;
            int b = map[i] % C;
            if (b != 0) {
                cnt += a + 1;
            } else {
                cnt += a;
            }
        }
        System.out.println(cnt);

    }
}
