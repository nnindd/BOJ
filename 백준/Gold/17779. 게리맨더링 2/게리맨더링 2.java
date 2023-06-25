import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, totalPopu;
    static int[] popu; //인구수를 저장할 배열
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        totalPopu = 0;
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer tokens = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                totalPopu += map[i][j]; //총 인구수를 구함
            }
        }//end input

        //popu = new int[5]; //1~5 선거구의 인구수를 저장할 배열. 한번 (x, y) (dx, dy)를 정할때마다 초기화

        //(x, y) (dx, dy)를 정한다
        //정한 위치에 따라 선거구를 5개로 나눈다
        //1~4번 선거구를 구하고 popu 배열에 순서대로 넣는다
        //5번 선거구이면 총인구수 - popu합을 빼서 5번 선거구 인구수로 넣어준다
        //배열 정렬하고 (맨 뒤 - 맨 앞)을 구해서 최소값 갱신

        int minPopu = Integer.MAX_VALUE;

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {

                        //1. 의 d1, d2에 대한 길이 조건에 해당되지 않으면 탐색 안 함
                        if(x+d1+d2 > N) continue;
                        if(y-d1 < 1 || y + d2 > N) continue;

                        popu = new int[5];
                        //int[][] second = new int[N+1][N+1];

                        //5번 선거구의 경계선을 지역구 배열에 true 표시
                        //각 지역구별로 다르게 범위를 구하면서 인구수 저장
                        boolean[][] fifth = new boolean[N + 1][N + 1];
                        //경계선 긋기
                        for (int r = x, c = y; r <= x + d1; r++, c--) //대각선 윗줄 '/'
                            //if (isRange(r, c)) {
                                fifth[r][c] = true;
                                //second[r][c] = 9;
                            //}
                        for (int r = x, c = y; r <= x + d2; r++, c++) //대각선 윗줄 '\'
                            //if (isRange(r, c)) {
                                fifth[r][c] = true;
                                //second[r][c] = 9;
                            //}
                        for (int r = x + d1, c = y - d1; r <= x + d1 + d2; r++, c++) //대각선 아래줄 '\'
                            //if (isRange(r, c)) {
                                fifth[r][c] = true;
                                //second[r][c] = 9;
                            //}
                        for (int r = x + d2, c = y + d2; r <= x + d2 + d1; r++, c--)
                            //if (isRange(r, c)) {
                                fifth[r][c] = true;
                                //second[r][c] = 9;
                            //}

                        //한줄씩 범위대로 탐색
                        //1번 선거구 인원수 더하기
                        for (int r = 1; r < x + d1; r++) {
                            for (int c = 1; c <= y; c++) {
                                //if (!isRange(r, c)) continue;
                                if (fifth[r][c]) break; //경계선 만나면 다음줄로
                                popu[0] += map[r][c];
                                //second[r][c] = 1;
                            }
                        }

                        //2번 선거구
                        for (int r = 1; r <= x + d2; r++) {
                            for (int c = N; c >=y+1; c--) {//열을 뒤에서부터 읽음
                                //if (!isRange(r, c)) continue;
                                if (fifth[r][c]) break;
                                popu[1] += map[r][c];
                                //second[r][c] = 2;
                            }
                        }

                        //3번 선거구
                        for (int r = x + d1; r <= N; r++) {
                            for (int c = 1; c < y - d1 + d2; c++) {
                                //if (!isRange(r, c)) continue;
                                if (fifth[r][c]) break;
                                popu[2] += map[r][c];
                                //second[r][c] = 3;
                            }
                        }

                        //4번 선거구
                        for (int r = x + d2 + 1; r <= N; r++) {
                            for (int c = N; c >= y - d1 + d2; c--) {//열을 뒤에서부터 읽음
                                //if (!isRange(r, c)) continue;
                                if (fifth[r][c]) break;
                                popu[3] += map[r][c];
                                //second[r][c] = 4;
                            }
                        }

                        //인구수가 0인 곳이 있으면 계산 안하고 넘어감
                        if (popu[0] == 0 || popu[1] == 0 || popu[2] == 0 || popu[3] == 0) {
                            break;
                        }

//                        System.out.println("5구역배열출력");
//                        for (int i = 1; i <=N; i++) {
//                            for (int j = 1; j <= N ; j++) {
//                                System.out.printf("%7b", fifth[i][j]);
//                            }
//                            System.out.println();
//                        }
//                        System.out.println();
//                        for (int i = 1; i <=N; i++) {
//                            for (int j = 1; j <= N ; j++) {
//                                System.out.printf("%4d", second[i][j]);
//                            }
//                            System.out.println();
//                        }

//                        System.out.println(x + " " + y + " " + d1 + " " + d2);
                        //5번 선거구 인원수 구하기
                        int tempSum = 0;
                        for (int k = 0; k < 4; k++) {
                            tempSum += popu[k];
                        }
                        popu[4] = totalPopu - tempSum;
                        //System.out.println(Arrays.toString(popu));

                        Arrays.sort(popu);

                        minPopu = Math.min(minPopu, (popu[4] - popu[0])); //최대-최소 인구수 가장 작은 값 갱신

                    }
                }
            }
        }//end 4 for

        System.out.println(minPopu);

    }//end main

//    private static boolean isRange(int r, int c) { //범위 체크
//        if (r < 1 || c < 1 || r > N || c > N) return false;
//        return true;
//    }
}
