import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        int[] map = new int[101];

        int a, b;
        for (int i = 0; i < N + M; i++) {
            tokens = new StringTokenizer(br.readLine());
            a = Integer.parseInt(tokens.nextToken());
            b = Integer.parseInt(tokens.nextToken());

            map[a] = b;
        }

        System.out.println(bfs(map));
    }

    private static int bfs(int[] map) {
        int res = 0;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] v = new boolean[101];

        q.offer(new int[]{1, 0}); //위치와 횟수
        v[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            //1~6 주사위를 굴림
            for (int d = 1; d <= 6; d++) {
                int next = cur[0] + d;

                if (v[next] || next > 100) continue;

                //도착
                if (next == 100) {
                    res = cur[1] + 1;
                    return res;
                }

                //맵 방향 설정
                if (map[next] != 0) {
                    next = map[next];
                }
                //다음 위치
                v[next] = true;
                q.offer(new int[]{next, cur[1] + 1});

            }
        }

        return res;
    }
}
