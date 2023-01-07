import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        //집합에 듣도 못한 사람 넣음
        Set<String> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        //보도 못한 사람을 집합에 넣음
        //집합의 크기가 변하지 않았다면 결과 집합에 저장
        Set<String> result = new TreeSet<>();
        int size;
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            size = set.size();
            set.add(str);
            if (size == set.size()) {
                result.add(str);
            }
        }
        sb.append(result.size()).append("\n");
        for(String str : result){
            sb.append(str).append("\n");
        }
        System.out.println(sb);

    }
}
