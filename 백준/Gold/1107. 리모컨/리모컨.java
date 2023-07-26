import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] numbers = new boolean[10];

        if (M != 0) {
            StringTokenizer tokens = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                numbers[Integer.parseInt(tokens.nextToken())] = true;
            }
        }

        int res = Math.abs(100 - N); //+/-키로만 이동
        if (N == 100) {
            res = 0;
        } else {
            //모든 번호를 눌러봄
            for (int i = 0; i < 10000001; i++) {
                String num = Integer.toString(i);

                boolean flag = false;
                for (int j = 0; j < num.length(); j++) {
                    if (numbers[num.charAt(j) - '0']) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    res = Math.min(res, Math.abs(i - N) + num.length());
                }
            }
        }
        System.out.println(res);
    }
}
