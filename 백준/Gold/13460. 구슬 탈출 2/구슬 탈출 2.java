import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static Point pos;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int rx, ry, bx, by, cnt;

        public Point() {
        }

        public Point(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        pos = new Point();
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'R') {
                    pos.rx = i;
                    pos.ry = j;
                } else if (map[i][j] == 'B') {
                    pos.bx = i;
                    pos.by = j;
                }
            }
        }

        pos.cnt = 0;
        bfs();

    }//end main

    private static void bfs() {

        Queue<Point> queue = new LinkedList<>();
        queue.offer(pos);
        boolean[][][][] v = new boolean[N][M][N][M];

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            v[cur.rx][cur.ry][cur.bx][cur.by] = true;

            if (cur.cnt >= 10) {//10회 이상 움직이면 -1
                System.out.println(-1);
                return;
            }

            for (int d = 0; d < 4; d++) {//4방으로 굴려봄
                boolean red = false, blue = false;

                //빨간공 먼저 굴림
                int rx = cur.rx;
                int ry = cur.ry;

                while (map[rx + dr[d]][ry + dc[d]] != '#') {//벽, 파란구슬 안만날때까지
                    rx += dr[d];
                    ry += dc[d];
                    if (map[rx][ry] == 'O') {//구멍만나면 탈출
                        red = true;
                        break;
                    }
                }

                //파란공 굴림
                int bx = cur.bx;
                int by = cur.by;

                while (map[bx + dr[d]][by + dc[d]] != '#') {//벽, 빨간구슬 안만날때까지
                    bx += dr[d];
                    by += dc[d];
                    if (map[bx][by] == 'O') {//구멍만나면 탈출
                        blue = true;
                        break;
                    }
                }

                if (blue) {//파란공이 빠졌으면 다음 방향으로 넘어감
                    continue;
                }
                if (red) {//빨간공이 빠졌으면 결과 출력. bfs이기 때문에 최소 횟수가 됨
                    System.out.println(cur.cnt + 1);
                    return;
                }

                if (rx == bx && ry == by) {
                    switch (d) {
                        case 0: //위로 움직이는 방향. 열이 같음
                            if (cur.rx < cur.bx) //처음에 빨간파란구슬 위치 비교
                                bx += 1; //도착지점 비교
                            else
                                rx += 1;
                            break;
                        case 1: //아래로 움직이는 방향. 열이 같음
                            if (cur.rx < cur.bx) //처음에 빨간파란구슬 위치 비교
                                rx -= 1; //도착지점 비교
                            else
                                bx -= 1;
                            break;
                        case 2: //좌로 움직이는 방향. 행이 같음
                            if (cur.ry < cur.by) //처음에 빨간파란구슬 위치 비교
                                by += 1; //도착지점 비교
                            else
                                ry += 1;
                            break;
                        case 3: //우로 움직이는 방향. 행이 같음
                            if (cur.ry < cur.by) //처음에 빨간파란구슬 위치 비교
                                ry -= 1; //도착지점 비교
                            else
                                by -= 1;
                            break;
                    }
                }

                if (!v[rx][ry][bx][by]) {
                    queue.offer(new Point(rx, ry, bx, by, cur.cnt + 1));
                }
            }
        }
        System.out.println(-1); //다 돌았는데도 못나오면 -1
    }


}
