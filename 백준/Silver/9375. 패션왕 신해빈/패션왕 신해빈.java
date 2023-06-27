import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            HashMap<String, Integer> map = new HashMap<>();

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                tokens.nextToken();
                String category = tokens.nextToken();

                map.put(category, map.getOrDefault(category, 0) + 1);
            }

            int result = 1;
            for(int i : map.values()){
                result *= (i+1);
            }
            result--;
            sb.append(result).append("\n");
        }//end test case
        System.out.print(sb);
    }//end main
}
