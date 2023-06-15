import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            sb.append(bfs(a, b)).append("\n");
        }
        System.out.println(sb);
    }

    static class Pos {
        int num;
        String order;

        public Pos(int num, String order) {
            this.num = num;
            this.order = order;
        }

        public int D() {
            return (num * 2) % 10000;
        }

        public int S() {
            return num == 0 ? 9999 : num - 1;
        }

        public int L() {
            //1234 -> 2341
            //123 -> 231
            //12 -> 21
            return (num % 1000) * 10 + (num / 1000);
        }

        public int R() {
            //1234 -> 4123
            return (num % 10) * 1000 + (num / 10);
        }

    }

    private static String bfs(int a, int b) {
        //D, S, L, R의 연산을 수행함
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(a, ""));
        boolean[] v = new boolean[10000];
        v[a] = true;

        String res = "";

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            if (cur.num == b) {
                res = cur.order;
                break;
            }

            for (int i = 0; i < 4; i++) {
                Pos next;
                if (i == 0) {
                    next = new Pos(cur.D(), cur.order+"D");
                } else if (i == 2) {
                    next = new Pos(cur.S(), cur.order+"S");
                } else if (i == 3) {
                    next = new Pos(cur.L(), cur.order+"L");
                } else {
                    next = new Pos(cur.R(), cur.order+"R");
                }

                if (v[next.num]) continue;
                v[next.num] = true;
                q.offer(next);
            }

        }
        return res;
    }

}
