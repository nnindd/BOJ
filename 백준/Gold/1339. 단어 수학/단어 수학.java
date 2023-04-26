import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] alpa = new int[26];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int len = word.length();
            int pow = (int) Math.pow(10, len - 1); //자릿수

            for (int j = 0; j < len; j++) {
                alpa[word.charAt(j) - 'A'] += pow;
                pow /= 10;
            }
        }

        Arrays.sort(alpa);

        //가장 큰 값부터 계산
        int num = 9;
        int sum = 0;
        for (int i = 'Z'-'A'; i >= 0 ; i--) {
            if(alpa[i] == 0) break;

            sum += alpa[i] * num--;
        }
        System.out.println(sum);
    }
}
