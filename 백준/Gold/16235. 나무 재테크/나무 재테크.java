import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, map[][], grow[][];
    static PriorityQueue[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        //현재 땅 상태
        map = new int[N][N];

        //양분 양 저장 맵
        grow = new int[N][N];

        //현재 나무의 나이를 저장할 맵
        tree = new PriorityQueue[N][N];

        for (int i = 0; i < N; i++) { //맵 설정
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grow[i][j] = Integer.parseInt(tokens.nextToken()); //양분 양 저장
                map[i][j] = 5; //초기 양분 저장
                tree[i][j] = new PriorityQueue<Integer>(); //나무 우선순위큐 설정
            }
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken()) - 1;
            int y = Integer.parseInt(tokens.nextToken()) - 1;
            int age = Integer.parseInt(tokens.nextToken());

            tree[x][y].offer(age); //나무의 나이 저장
        }//end input

        while (K-- > 0) {
            //봄
            int[][] dead = spring();

            //여름
            summer(dead);

            //가을
            fall();

            //겨울
            winter();

        }

        //살아남은 나무의 수 출력
        System.out.println(getRemainTrees());
    }

    private static int getRemainTrees() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += tree[i][j].size();
            }
        }
        return sum;
    }

    private static int[][] spring() {
        //기본 동작
        //나무는 자신의 나이만큼 양분을 먹고 1 증가
        //나이가 어린 나무부터 양분을 먹음
        //양분이 부족하다면 죽음

        //죽은 나무를 관리하기 위함
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Integer> remain = new PriorityQueue<>(); //한번에 저장하기 위함

                while(!tree[i][j].isEmpty()) {
                    int cur = (int) tree[i][j].poll();

                    //양분 되는지 체크
                    if (map[i][j] - cur >= 0) {
                        map[i][j] -= cur;
                        //나이가 자람
                        remain.offer(cur + 1);
                    } else {
                        //죽은나무 처리
                        temp[i][j] += cur / 2;
                    }
                }

                //지금 위치에 산 나무만 저장
                while(!remain.isEmpty()) {
                    tree[i][j].offer(remain.poll());
                }
            }
        }
        return temp;
    }

    private static void summer(int[][] dead) {
        //죽은 나무 양분 처리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += dead[i][j];
            }
        }
    }

    private static void fall() {
        //나무 번식
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //나무맵에 들어있는 나무 탐색
                Iterator<Integer> itr = tree[i][j].iterator();

                while (itr.hasNext()) {
                    int cur = itr.next();

                    if (cur % 5 == 0) { //나무 번식
                        for (int d = 0; d < 8; d++) {
                            int nx = i + dir[d][0];
                            int ny = j + dir[d][1];

                            if (!isRange(nx, ny)) continue;

                            tree[nx][ny].offer(1); //1살 나무 추가
                        }
                    }
                }
            }
        }
    }

    private static void winter() {
        //S2D2가 땅을 돌아다니면서 땅에 양분을 추가함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += grow[i][j];
            }
        }
    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N) return false;
        return true;
    }

}
