import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int res = 0;
        int idx = 0;
        int cnt = 0;

        StringBuilder sb;
        //ioi라면 인덱스를 +2, 아니라면 인덱스를 +1
        while (idx < M - 2) { //가장 끝 3자리까지만 비교하도록
            if ((str.charAt(idx) == 'I' &&  str.charAt(idx+1) == 'O' && str.charAt(idx+2) == 'I')) {
                idx += 2;
                cnt++;

                if (cnt == N) {//N만큼 검사했다면 결과 갱신
                    res++;
                    cnt--; //IOIOIOI인 경우 IOIOI|OI, IO|IOIOI를 비교할 때 cnt를 하나 이전으로 돌려줘야 함
                }
            } else {
                idx++;
                cnt = 0;
            }
        }
        System.out.println(res);
    }
}
