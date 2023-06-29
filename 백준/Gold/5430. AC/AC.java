import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int T = Integer.parseInt(br.readLine());

        loop:
        while (T-- > 0) {
            String order = br.readLine();
            int N = Integer.parseInt(br.readLine());

            String numbers = br.readLine();
            numbers = numbers.substring(1, numbers.length() - 1);
            tokens = new StringTokenizer(numbers, ",");

            ArrayDeque<Integer> q = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                q.offer(Integer.parseInt(tokens.nextToken()));
            }

            boolean flag = false;
            for (int i = 0; i < order.length(); i++) {
                if (order.charAt(i) == 'R') {
                    flag = !flag;
                } else {
                    if (q.isEmpty()) {
                        System.out.println("error");
                        continue loop;
                    }
                    if (!flag) {
                        q.pollFirst();
                    } else {
                        q.pollLast();
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (q.isEmpty()) {
                sb.append("]");
            } else {
                while (!q.isEmpty()) {
                    if (!flag) {
                        sb.append(q.pollFirst()).append(",");
                    } else {
                        sb.append(q.pollLast()).append(",");
                    }
                }
                sb.replace(sb.length() - 1, sb.length(), "]");
            }

            System.out.println(sb);
        }
    }
}
