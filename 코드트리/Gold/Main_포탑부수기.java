// 문제 링크 https://www.codetree.ai/training-field/frequent-problems/problems/destroy-the-turret/description?page=3&pageSize=20
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos {
        int x, y;
        int power; //파워
        int attack; //공격한 시점 저장

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int power, int attack) {
            this.x = x;
            this.y = y;
            this.power = power;
            this.attack = attack;
        }
    }

    static int N, M;
    static int[][] map; //탑 공격력 저장
    static int[][] attack;
    static boolean[][] no; //영향 받지 않는 타워
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}}; //우하좌상


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        int K = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        attack = new int[N][M];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }//end input

        for (int i = 1; i <= K; i++) {
            //무관한 타워 설정
            no = new boolean[N][M];

            //공격자, 공격 대상 선정
            Pos[] selected = selectStartAndEnd();
            Pos start = selected[0];
            Pos end = selected[1];

            //공격자랑 공격대상이 같은 경우 >> 더이상 진행 안 함
            if(start.x == end.x && start.y == end.y){
                break;
            }

            //영향 설정
            map[start.x][start.y] += N + M; //핸디캡 적용
            attack[start.x][start.y] = i; //공격 시점 저장

            no[start.x][start.y] = true; //영향 받은 타워 설정
            no[end.x][end.y] = true;

            //공격
            findBestWay(start, end);

            //포탑 부서짐 및 포탑 정비
            recovery();
        }

        //가장 강한 포탑 출력
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        System.out.println(max);
    }

    private static void recovery() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] < 0) { //무너진 포탑 처리
                    map[i][j] = 0;
                    continue;
                }

                //영향 받지 않은 포탑 1 증가
                if (map[i][j] != 0 && !no[i][j]) {
                    map[i][j]++;
                }
            }
        }
    }

    private static void findBestWay(Pos start, Pos end) {
        if (!isLaser(start, end)) {
            goBomb(start, end);
        }
    }

    private static void goBomb(Pos start, Pos end) {
        //공격 대상 공격받음
        int power = map[start.x][start.y];
        map[end.x][end.y] -= power;

        power /= 2;

        //8방으로 퍼지기
        for (int d = 0; d < 8; d++) {
            int nx = (end.x + dir[d][0] + N) % N;
            int ny = (end.y + dir[d][1] + M) % M;

            if (!(nx == start.x && ny == start.y)) {
                map[nx][ny] -= power;
                no[nx][ny] = true;
            }
        }
    }

    private static boolean isLaser(Pos start, Pos end) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(start.x, start.y));

        boolean[][] v = new boolean[N][M];
        v[start.x][start.y] = true;

        Pos[][] route = new Pos[N][M]; //현재 위치에 가장 먼저 도착한 x,y저장

        loop:
        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = (cur.x + dir[d][0] + N) % N;
                int ny = (cur.y + dir[d][1] + M) % M;

                if (map[nx][ny] == 0 || v[nx][ny]) continue; //무너졌거나 방문

                v[nx][ny] = true;
                q.offer(new Pos(nx, ny));
                route[nx][ny] = new Pos(cur.x, cur.y);

            }
        }

        if (!v[end.x][end.y]) {
            return false; //목적지에 도달 못함
        }

        //거꾸로 출발해서 공격
        int x = end.x;
        int y = end.y;

        while (x != start.x || y != start.y) {
            int power = map[start.x][start.y] / 2;
            if (x == end.x && y == end.y) {
                power = map[start.x][start.y];
            }

            map[x][y] -= power;
            no[x][y] = true;

            Pos now = route[x][y];
            x = now.x;
            y = now.y;
        }
        return true;
    }

    private static Pos[] selectStartAndEnd() {
        ArrayList<Pos> tower = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                tower.add(new Pos(i, j, map[i][j], attack[i][j]));
            }
        }

        //정렬
        Collections.sort(tower, (a, b) -> {
            if (a.power == b.power) {
                if (a.attack == b.attack) {
                    if ((a.x + a.y) == (b.x + b.y)) {
                        return b.y - a.y;
                    }
                    return (b.x + b.y) - (a.x + a.y);
                }
                return b.attack - a.attack;
            }
            return a.power - b.power;
        });

        Pos[] result = new Pos[2];
        result[0] = tower.get(0);
        result[1] = tower.get(tower.size() - 1);
        return result;

    }
}
