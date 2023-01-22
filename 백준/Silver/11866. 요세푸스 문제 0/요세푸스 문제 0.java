import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int K = Integer.parseInt(tokens.nextToken());

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        sb.append("<");
        int cnt = 0;
        while (!q.isEmpty()){
            if(cnt == K-1){
                sb.append(q.poll()).append(", ");
                cnt = 0;
                continue;
            }
            q.offer(q.poll());
            cnt++;
        }

        sb.setLength(sb.length()-2);
        sb.append(">");

        System.out.println(sb);

    }

}
