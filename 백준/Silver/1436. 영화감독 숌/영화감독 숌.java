import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int num = 666;
        int cnt = 1;
        while (cnt != N){ //n번째가 될때까지
            num++;

            if(String.valueOf(num).contains("666")){ //666을 포함하면 횟수 증가
                cnt++;
            }
        }

        System.out.println(num);
    }
}
