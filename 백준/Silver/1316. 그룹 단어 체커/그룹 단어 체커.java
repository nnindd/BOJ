import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int res = N;
        for (int i = 0; i < N; i++) {
            HashMap<Character, Integer> map = new HashMap<>();
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                char w = str.charAt(j);
                int val = map.getOrDefault(w, -1);
                if(val == -1){ //존재하지 않는 경우
                    map.put(w, j);
                    continue;
                }
                //값이 존재하는 경우 인덱스 비교
                if(j-1 != val){
                    //연속으로 존재하지 않는다 그룹단어가 아님
                    res--;
                    break;
                }
                //값이 존재하고 인덱스가 1개 차이 날 때
                map.replace(w, j);
            }
        }
        System.out.println(res);
    }
}
