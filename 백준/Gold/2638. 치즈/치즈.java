import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, map[][], cnt, time;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        map = new int[N][M];
        cnt = time = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == 1) cnt++;
            }
        }//end input

        while (cnt > 0) {
            checkOutArea(); //외부 영역 탐색
            removeCheese(); //치즈 삭제
            time++;
        }
        System.out.println(time);
    }


    private static void removeCheese() {
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (map[i][j] == 9 || map[i][j] == 0) continue;

                //4방 탐색
                int outline = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + direction[d][0];
                    int ny = j + direction[d][1];

                    if ((nx >= 0 && ny >= 0 && nx < N && ny < M) && map[nx][ny] == 9) {
                        outline++;
                    }
                }

                if (outline >= 2) {
                    //삭제할 위치 표시
                    map[i][j] = 0;
                    cnt--;
                }
            }
        }

    }

    private static void checkOutArea() {
        //외부 영역 표시
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        //0인 곳 찾음
        q.offer(new int[]{0, 0});
        v[0][0] = true;
        map[0][0] = 9;

        int[] cur;
        while (!q.isEmpty()) {
            cur = q.poll();

            for (int d = 0; d < 4; d++) {
                //4방을 확인해서 1을 마주치기까지 해당 영역을 외부 영역으로 바꿔줌
                int nx = cur[0] + direction[d][0];
                int ny = cur[1] + direction[d][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(v[nx][ny] || map[nx][ny] == 1) continue; //방문했거나 치즈면 넘어감

                map[nx][ny] = 9; //외부공기로 바꿔줌
                v[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }
}
