import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //0이 포함되어야 30의 배수를 만들 수 있다.
        //각 자리의 합이 3의 배수여야 30의 배수를 만들 수 있다.

        //1. 입력받은 문자열을 0~9까지의 카운트 배열에 입력하고 총합 계산
        String num = br.readLine();

        char[] arr = num.toCharArray();
        Arrays.sort(arr);

        int sum = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = num.length() - 1; i >= 0; i--) {
            sum += arr[i] - '0';
            sb.append(arr[i] - '0');
        }

        //2. 0을 포함하는지, 총합이 3의 배수인지 확인
        System.out.println(num.contains("0") && sum % 3 == 0 ? sb : -1);
    }

}
