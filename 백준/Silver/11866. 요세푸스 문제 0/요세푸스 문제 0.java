import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int K = Integer.parseInt(tokens.nextToken());

        //(현재 위치에서 +3번째)        k번째 % 총인원 = 자리수
        //1 2 3 4 5 6 7 >> 3    3   3 % 7 = 3
        //1 2 4 5 6 7   >> 6    5   3+3-1 % 6 = 5
        //1 2 4 5 7     >> 2    2   3+5-1 % 5 = 2
        //1 4 5 7       >> 7    4   3+2-1 % 4 = 0?
        //1 4 5         >> 5    3   3+0-1 % 3 =
        //4             >> 1    1
        //              >> 4    1

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        sb.append("<");
        int index = 0;
        while (list.size() > 0) {
            index = (K + index - 1) % N; //자리수 저장
            sb.append(list.remove(index)).append(", ");
            N--;
        }
        sb.setLength(sb.length()-2);
        sb.append(">");

        System.out.println(sb);
    }
}
