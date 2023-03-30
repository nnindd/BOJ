// 문제 링크 https://www.codetree.ai/training-field/frequent-problems/codetree-mon-bread/description?page=3&pageSize=20&username=

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_코드트리빵 {
    static int N, M, map[][], finish;
    static Pos pos[][];
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Pos {
        int x, y, d;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        //입력 받기
        N = Integer.parseInt(tokens.nextToken()); //격자 크기
        M = Integer.parseInt(tokens.nextToken()); //사람 수
        map = new int[N][N]; //맵
        pos = new Pos[M][2]; //편의점 위치, 현재 위치

        //map input
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        //store input
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            pos[i][0] = new Pos(Integer.parseInt(tokens.nextToken()) - 1, Integer.parseInt(tokens.nextToken()) - 1);
        }

        int t = 0;
        finish = 0;
        while (finish < M) {
            move(t++);
        }
        System.out.println(t);

    }

    private static void move(int t) {
        //한 타임마다 모든 사람 이동
        for (int i = 0; i < M; i++) {
            //자기 차례가 되지 않아 격자 밖이라면 더이상 진행 안함
            if (i > t) return;
            //t == i이고 pos 현위치가 null이라면 베이스캠프 배정
            if (i == t && pos[i][1] == null) {
                getBaseCamp(i); //i번째 사람이 가려는 편의점에서 가장 가까운 베이스캠프 정함
            } else { //아니라면 최단 거리로 갈 방향 선택해서 이동
                if(pos[i][1] == null) continue; //이미편의점에 도착한 사람
                int d = goFast(i); //최단 거리로 갈 방향 구해옴
                //이동
                pos[i][1].x += dir[d][0];
                pos[i][1].y += dir[d][1];
                //편의점에 도착했는지 확인
                if (pos[i][0].x == pos[i][1].x && pos[i][0].y == pos[i][1].y) {
                    int cx = pos[i][0].x;
                    int cy = pos[i][0].y;
                    map[cx][cy] = 9; //맵에 방문 못하게 처리
                    pos[i][1] = null; //자신은 비움
                    finish++; //편의점에 도착한 사람 처리
                }
            }

        }

    }

    private static int goFast(int idx) { //현위치
        //현 위치에서 목적지 편의점까지 가장 빠른 방향을 구함
        Pos dest = pos[idx][0]; //도착하고자 하는 곳
        Pos start = pos[idx][1]; //현재 위치
        Deque<Pos> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];

        //4방향 전부 넣음
        for (int d = 0; d < 4; d++) {
            int nx = start.x + dir[d][0];
            int ny = start.y + dir[d][1];
            if(!isRange(nx, ny) || map[nx][ny] == 9) continue;
            q.offer(new Pos(nx, ny, d));
            v[nx][ny] = true;
        }

        int direction = -1;

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (cur.x == dest.x && cur.y == dest.y) {
                //목적지 방향을 구함
                direction = cur.d;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dir[d][0];
                int ny = cur.y + dir[d][1];

                if (!isRange(nx, ny) || map[nx][ny] == 9 || v[nx][ny]) continue;
                q.offer(new Pos(nx, ny, cur.d)); //방향 처음 그대로 저장
                v[nx][ny] = true;
            }
        }
        return direction;
    }

    //베이스 캠프 정하기 : bfs, 주어진 방향 순서대로 이동 후 가장 먼저 도착하는 곳이 베이스캠프(상좌우하)
    private static void getBaseCamp(int idx) {
        Pos s = pos[idx][0];
        Deque<Pos> q = new ArrayDeque<>();
        q.offer(s);
        boolean[][] v = new boolean[N][N];
        v[s.x][s.y] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dir[d][0];
                int ny = cur.y + dir[d][1];

                if (!isRange(nx, ny) || map[nx][ny] == 9 || v[nx][ny]) continue;
                //0이면 다음 위치로
                if (map[nx][ny] == 0) {
                    q.offer(new Pos(nx, ny));
                    v[nx][ny] = true;
                }
                //1이면 베이스 캠프 선택 완료
                if (map[nx][ny] == 1) {
                    pos[idx][1] = new Pos(nx, ny); //현재 위치 갱신
                    map[nx][ny] = 9; //맵에서 사용 못하게 처리
                    return;
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N) return false;
        return true;
    }

}
