import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        //0) 초기화
        // 0-9, A-Z의 36진수 값을 저장할 배열 설정
        BigInteger[] digit = new BigInteger[36];
        Arrays.fill(digit, BigInteger.ZERO); //초기화

        //총합
        BigInteger sum = BigInteger.ZERO;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            BigInteger cur = new BigInteger(str, 36); //36진수로 변환

            //1) 각 36진수를 총합에 더해줌
            sum = sum.add(cur);

            //2) 한글자씩 Z로 변환했을 때 가중치를 저장
            //ex) YZ라면, digit[Y] += (35 - Y) * 36^1을 저장함
            BigInteger pow = BigInteger.ONE; //지수 값
            for (int j = str.length() - 1; j >= 0; j--) {
                int idx = str.charAt(j) <= '9' ? str.charAt(j) - '0' : str.charAt(j) - 'A' + 10; //digit에 저장할 인덱스 설정
                digit[idx] = digit[idx].add(pow.multiply(BigInteger.valueOf(35 - idx)));
                pow = pow.multiply(BigInteger.valueOf(36)); //다음 자리수로
            }
        }

        //3) digit을 정렬하고 내림차순으로 사용해서 K만큼 더해줌
        //ex) 1+2 = 3을 10+2=12로 변경했을 때, 10-1의 차이인 9만큼 더해줘야 총 합이 됨
        int K = Integer.parseInt(br.readLine());
        Arrays.sort(digit);
        int cnt = 0;
        for (int i = 35; i >= 0 ; i--) {
            if(cnt == K) break;
            sum = sum.add(digit[i]);
            cnt++;
        }

        //4) 출력
        System.out.println(sum.toString(36).toUpperCase());

    }

}