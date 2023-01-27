import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        //집합에 넣어서 자동 정렬
        TreeSet<Integer> set = new TreeSet<>();
        for(int i : arr){
            set.add(i);
        }

        //맵에 인덱스랑 같이 넣음
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(set.pollFirst(), i); //정렬된 배열값과 인덱스 순서로 넣음
        }

        //원본 배열에 해당하는 값 가져옴
        for (int i = 0; i < N; i++) {
            sb.append(map.get(arr[i])).append(" ");
        }

        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }

}
