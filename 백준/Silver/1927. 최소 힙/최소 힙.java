import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        int temp;

        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            temp = Integer.parseInt(br.readLine());
            if (temp == 0) {
                cnt++;
                if (pq.isEmpty()) {
                    result.add(0);
                } else {
                    result.add(pq.poll());
                }
            } else {
                pq.add(temp);
            }
        }

        int idx = 0;
        while (idx < cnt) {
            sb.append(result.get(idx++)).append("\n");
        }
        System.out.println(sb);

    }
}
