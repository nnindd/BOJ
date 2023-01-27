import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] sorted = new int[N];

        StringTokenizer tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = sorted[i] = Integer.parseInt(tokens.nextToken());
        }

        //정렬
        Arrays.sort(sorted);

        //맵에 인덱스랑 같이 넣음
        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int i : sorted) {
            if(map.containsKey(i)) //이미 있는 경우 제외
                continue;
            map.put(i, index); //<배열 값, 순위> 저장
            index++;
        }

        //원본 배열에 해당하는 값 가져옴
        for (int i = 0; i < N; i++) {
            sb.append(map.get(arr[i])).append(" ");
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

}
