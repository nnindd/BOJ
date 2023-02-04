import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokens;

        int T = Integer.parseInt(br.readLine());
        int K;

        TreeMap<Integer, Integer> map;

        while (T-- > 0) {
            K = Integer.parseInt(br.readLine());
            map = new TreeMap<>();

            for (int i = 0; i < K; i++) {
                tokens = new StringTokenizer(br.readLine());
                char word = tokens.nextToken().charAt(0);
                int num = Integer.parseInt(tokens.nextToken());

                if (word == 'I') {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                    continue;
                }
                //비었는지 확인
                if (map.size() == 0) continue;

                //삭제 처리
                //맵에서 최소/최대 숫자 가져옴
                int getNum = num == -1 ? map.firstKey() : map.lastKey();
                //그 숫자가 1이면 삭제해주고 아니면 1 감소 후 맵에 넣어줌
                int cnt = map.get(getNum);
                if(cnt == 1){
                    map.remove(getNum);
                }else{
                    map.put(getNum, cnt - 1);
                }
            }//end for
            //최대값 최소값 출력
            if (map.size() == 0) {
                sb.append("EMPTY");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey());
            }
            sb.append("\n");
        }//end T

        System.out.println(sb);
    }

}