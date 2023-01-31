import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H, zeroCnt, map[][][];
    static boolean v[][][];
    static Queue<Pos> q;

    //상하좌우앞뒤 {높이,행,열}
    static int dir[][] = {{0, -1, 0},
                        {0, 1, 0},
                        {0, 0, -1},
                        {0, 0, 1},
                        {-1, 0, 0},
                        {1, 0, 0}};

    static class Pos{
        int h, x, y;

        public Pos(int h, int x, int y) {
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        H = Integer.parseInt(tokens.nextToken());

        zeroCnt = 0;

        map = new int[H][N][M]; //3차원 배열 (높이, 가로, 세로 순)
        q = new ArrayDeque<>();
        v = new boolean[H][N][M];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[k][i][j] = Integer.parseInt(tokens.nextToken());
                    if(map[k][i][j] == 1){
                        q.offer(new Pos(k, i, j)); //1인 위치 넣음
                    } else if (map[k][i][j] == 0) {
                        zeroCnt++;
                    }
                }
            }
        }

        if(zeroCnt == 0){
            System.out.println(0);
        }else{
            System.out.println(bfs());
        }

    }

    private static int bfs() {
        int size;
        Pos cur;
        int cnt = 0;

        while (!q.isEmpty()){
            size = q.size();

            for (int i = 0; i < size; i++) {
                cur = q.poll();

                for (int d = 0; d < 6; d++) {
                    int nh = cur.h + dir[d][0];
                    int nx = cur.x + dir[d][1];
                    int ny = cur.y + dir[d][2];

                    //범위
                    if(!isRange(nh, nx, ny)) continue;
                    //1, -1일때
                    if(map[nh][nx][ny] == 1 || map[nh][nx][ny] == -1) continue;

                    //1로 변경
                    map[nh][nx][ny] = 1;
                    //0 카운트 감소
                    zeroCnt--;
                    //q에 추가
                    q.offer(new Pos(nh, nx, ny));
                }
            }
            //한 턴이 지나가면 날짜 증가
            cnt++;
        }

        if(zeroCnt != 0) return -1;
        return cnt - 1; //마지막에 종료되었을때 하루가 추가되었기 때문

    }

    private static boolean isRange(int h, int x, int y) {
        if(h < 0 || x < 0 || y < 0 || h >= H || x >= N || y >= M)
            return false;
        return true;
    }
}
