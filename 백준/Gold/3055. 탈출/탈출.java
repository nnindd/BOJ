import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static Queue<Pos> water, gosum;

    static class Pos {
        int x;
        int y;
        int cnt;

        public Pos(int x, int y) { //물
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int cnt) { //고슴도치
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        map = new char[R][C];
        water = new ArrayDeque<>();
        gosum = new ArrayDeque<>();

        String temp;
        for (int i = 0; i < R; i++) {
            temp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == '*') {//물 위치 저장
                    water.offer(new Pos(i, j));
                } else if (map[i][j] == 'S') {//고슴도치 위치 저장
                    gosum.offer(new Pos(i, j, 0));
                }
            }
        }//end input

        bfs();
    }

    private static void bfs() {
        Pos cur;
        int size, nx, ny;

        while (!gosum.isEmpty()) { //고슴도치가 도착할때까지
            size = water.size();
            for (int i = 0; i < size; i++) { //물이 있는 영역 전부 퍼짐
                cur = water.poll();
                for (int d = 0; d < 4; d++) {
                    nx = cur.x + dr[d];
                    ny = cur.y + dc[d];

                    if (isRange(nx, ny) && map[nx][ny] == '.') {
                        water.offer(new Pos(nx, ny));
                        map[nx][ny] = '*';
                    }
                }
            }//end water

            size = gosum.size();
            for (int i = 0; i < size; i++) {
                cur = gosum.poll();

                for (int d = 0; d < 4; d++) {
                    nx = cur.x + dr[d];
                    ny = cur.y + dc[d];

                    if(isRange(nx, ny)){
                        if(map[nx][ny]=='D'){
                            System.out.println(cur.cnt+1);
                            return;
                        } else if (map[nx][ny]=='.') {
                            map[nx][ny] = 'S';
                            gosum.offer(new Pos(nx, ny, cur.cnt+1));
                        }
                    }
                }
            }

        }//end while
        //비버 굴 이동 못함
        System.out.println("KAKTUS");
    }

    private static boolean isRange(int nx, int ny) {
        if (nx >= 0 && ny >= 0 && nx < R && ny < C) return true;
        return false;
    }
}
