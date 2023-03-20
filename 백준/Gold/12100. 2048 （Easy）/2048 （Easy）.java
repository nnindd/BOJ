import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max;
    static int[][] map, copy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        copy = new int[N][N];

        max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                copy[i][j] = map[i][j];
                max = Math.max(max, map[i][j]); //초기 최대값은 맵에서 가장 큰 값
            }
        }

        perm(0, new int[5]);

        System.out.println(max);
    }

    private static void perm(int cnt, int[] order) {//상하좌우 움직일 순서를 정함. 중복순열
        if (cnt == 5) {
            //순서대로 이동하기
            for (int d = 0; d < 5; d++) {
                move(order[d]); //이동 순서에 따라 이동
            }

            //카피배열을 처음 상태로 돌려줌
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    copy[i][j] = map[i][j];
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            //상하좌우 움직일 순서를 정함
            order[cnt] = i;
            perm(cnt + 1, order);
        }
    }

    private static void move(int d) {
        //2 2 4
        //0 0 4
        //2 2 4 같은 경우 공백 없게 땡겨준 다음 같은 값을 합쳐줘야함 그리고 다시 0없게 땡김

        //붙여줌     값 합침    붙여줌
        //2 2 4    4 4 8    4 4 8
        //2 2 4 => 0 0 0 => 0 0 4
        //0 0 4    0 0 4    0 0 0

        noZero(d);

        switch (d) {
            case 0: //위로 움직임
                //열로 탐색, 시작 인덱스는 0~N

                //한 열씩 검사 -> 한 행씩 같은값 확인
                //첫번째 반복문은 검사할 인덱스
                //두번째 반복문은 그 다음으로 계속 진행할 거
                for (int j = 0; j < N; j++) {//한 열씩 진행
                    for (int i = 0; i < N - 1; i++) {//첫번째부터 n-1까지 선택
                        if (copy[i][j] == copy[i + 1][j]) {
                            copy[i][j] += copy[i + 1][j];
                            copy[i + 1][j] = 0;
                        }
                    }
                }
                break;

            case 1: //아래로 움직임
                //열로 탐색
                for (int j = 0; j < N; j++) {//한 열씩 값 수정
                    for (int i = N - 1; i > 0; i--) {//행 시작 인덱스는 N~0
                        if (copy[i][j] == copy[i - 1][j]) {
                            copy[i][j] += copy[i - 1][j];
                            copy[i - 1][j] = 0;
                        }
                    }
                }
                break;

            case 2: //좌로 움직임
                //행으로 탐색
                for (int i = 0; i < N; i++) {//한 행씩 값 수정
                    for (int j = 0; j < N - 1; j++) {//시작 인덱스는 0~N
                        if (copy[i][j] == copy[i][j + 1]) {
                            copy[i][j] += copy[i][j + 1];
                            copy[i][j + 1] = 0;
                        }
                    }
                }
                break;

            case 3: //우로 움직임
                //열로 탐색
                for (int i = 0; i < N; i++) {
                    for (int j = N - 1; j > 0; j--) {//행 시작 인덱스는 N~0
                        if (copy[i][j] == copy[i][j - 1]) {
                            copy[i][j] += copy[i][j - 1];
                            copy[i][j - 1] = 0;
                        }
                    }
                }
                break;
        }

        noZero(d);

        //한번 움직일때마다 최대값 찾음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, copy[i][j]);
            }
        }
    }

    private static void noZero(int d) {
        switch (d) {
            case 0:
                for (int j = 0; j < N; j++) {//한 열씩 값 수정
                    for (int i = 0; i < N - 1; i++) {
                        int idx = i + 1;
                        if (copy[i][j] == 0) {//0이라면 다음이 0이 아닐때까지 검사해야함
                            while (idx < N) {
                                if (copy[idx][j] != 0) {
                                    copy[i][j] = copy[idx][j];
                                    copy[idx][j] = 0;
                                    break;
                                }
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int j = 0; j < N; j++) {//한 열씩 값 수정
                    for (int i = N - 1; i > 0; i--) {
                        int idx = i - 1;
                        if (copy[i][j] == 0) {//0이라면 다음이 0이 아닐때까지 검사해야함
                            while (idx >= 0) {
                                if (copy[idx][j] != 0) {
                                    copy[i][j] = copy[idx][j];
                                    copy[idx][j] = 0;
                                    break;
                                }
                                idx--;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < N; i++) {//한 열씩 값 수정
                    for (int j = 0; j < N - 1; j++) {
                        int idx = j + 1;
                        if (copy[i][j] == 0) {
                            while (idx < N) {
                                if (copy[i][idx] != 0) {
                                    copy[i][j] = copy[i][idx];
                                    copy[i][idx] = 0;
                                    break;
                                }
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < N; i++) {//한 열씩 값 수정
                    for (int j = N - 1; j > 0; j--) {
                        int idx = j - 1;
                        if (copy[i][j] == 0) {
                            while (idx >= 0) {
                                if (copy[i][idx] != 0) {
                                    copy[i][j] = copy[i][idx];
                                    copy[i][idx] = 0;
                                    break;
                                }
                                idx--;
                            }
                        }
                    }
                }
                break;
        }
    }
}
