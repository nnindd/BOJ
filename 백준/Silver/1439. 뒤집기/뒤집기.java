import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        //0과 1이 연속되지 않는 영역 개수 구함

        int prev = num.charAt(0) - '0'; //처음 값

        //처음 영역 설정
        int zero = prev == 0 ? 1 : 0;
        int one = prev == 1 ? 1 : 0;

        for (int i = 1; i < num.length(); i++) {
            int cur = num.charAt(i) - '0';
            if (prev != cur) {
                prev = cur;
                //현재 들어온 값이 0인지 1인지에 따라 영역 선택
                zero += cur == 0 ? 1 : 0;
                one += cur == 1 ? 1 : 0;
            }
        }

        System.out.println(Math.min(zero, one));
    }
}
