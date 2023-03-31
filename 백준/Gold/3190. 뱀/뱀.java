import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine()); //보드의 크기
        int[][] map = new int[N+1][N+1];

        int K = Integer.parseInt(br.readLine()); //사과의 수
        for (int i = 0; i < K; i++) {
            //사과의 위치
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken()) - 1;
            int y = Integer.parseInt(tokens.nextToken()) - 1;
            map[x][y] = 9;
        }

        int L = Integer.parseInt(br.readLine()); //뱀의 방향 전환 횟수
        int[] pos = new int[10_001];

        int LEFT = 1;
        int RIGHT = 2;

        for (int i = 0; i < L; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken()); //초
            char c = tokens.nextToken().charAt(0); //방향 (L->1 왼쪽 90도, D->2 오른쪽 90도)
            if (c == 'L') pos[x] = LEFT;
            else if (c == 'D') pos[x] = RIGHT;
        }//end input

        //한칸 이동할때마다 1
        System.out.println(play(pos, map, N, L, LEFT));

    }

    private static int play(int[] pos, int[][] map, int N, int L, int LEFT) {
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //우 하 좌 상

        int t = 1; //시간
        int d = 0; //방향

        //뱀 위치
        ArrayDeque<int[]> q = new ArrayDeque<>();

        //뱀 시작 위치
        q.offer(new int[]{0, 0});
        map[0][0] = 1;

        while (true) {
            //뱀 머리 가져옴
            int[] head = q.pollFirst();

            //맵에 머리와 꼬리 표시
            map[head[0]][head[1]] = 1;

            int nx = head[0] + dir[d][0];
            int ny = head[1] + dir[d][1];

            //탈출 조건 (벽이거나 자기자신)
            if(!isRange(nx, ny, N) || map[nx][ny] == 1) {
                break;
            }

            //안먹음
            //지금 머리 다시 넣음
            q.offerFirst(head);

            int[] tail = q.pollLast();
            if(map[nx][ny] == 9) {
                //꼬리 가져옴 맵에 표시하고 다시 넣어줌
                map[tail[0]][tail[1]] = 1;
                q.offerLast(tail);
            }else{
                //꼬리 제거 맵에서도 제거
                map[tail[0]][tail[1]] = 0;
            }

            //이동한 머리를 가장 처음에 넣어줌, 맵에 표시함
            q.offerFirst(new int[]{nx, ny});
            map[nx][ny] = 1;

            //지금 시간이 L보다 작은지 확인, 배열에서 지금 회전해야 하는지 체크
            if (t < pos.length && pos[t] != 0) {
                //인덱스 설정
                if (pos[t] == LEFT) {
                    d = d - 1 == -1 ? 3 : d - 1;
                } else {
                    d = d + 1 == 4 ? 0 : d + 1;
                }
            }
            t++; //시간 증가
        }
        return t;
    }

    private static boolean isRange(int x, int y, int N) {
        if (x < 0 || x >= N || y < 0 || y >= N) return false;
        return true;
    }
}
