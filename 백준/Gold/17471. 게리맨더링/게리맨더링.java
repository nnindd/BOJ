import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, min;
    static int[] arr;
    static int[][] map;
    static boolean[] area;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        N = Integer.parseInt(br.readLine().trim()); // 마을의 수

        // 유권자수
        arr = new int[N];
        tokens = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        map = new int[N][N]; // 인접행렬
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine().trim());
            int times = Integer.parseInt(tokens.nextToken());
            for (int j = 0; j < times; j++) {
                int link = Integer.parseInt(tokens.nextToken()) - 1;//연결될구역

                map[i][link] = 1;
                map[link][i] = 1;
            }
        }
        min = Integer.MAX_VALUE; // 갱신할 최소값

        area = new boolean[N]; // t/f로 나눌 구역

        powerset(0); // 부분집합 구하기

        System.out.println(min==Integer.MAX_VALUE?-1:min);

    }// end main

    private static void powerset(int cnt) {
        if (cnt == N) {
            if (getCnt()) { // 전부다 true/false가 아닌 경우
                // 각 구역이 연결됐는지 구한다

                if (isLink()) {

                    // 다 연결됐으면 유권자 차이값 구함
                    int firstTotal = 0, secondTotal = 0;

                    for (int i = 0; i < N; i++) {
                        if (area[i]) {
                            firstTotal += arr[i];
                        } else {
                            secondTotal += arr[i];
                        }
                    }

                    min = Math.min(min, Math.abs(firstTotal - secondTotal));
                }
            }
            return;
        }

        // 부분집합 구하기
        area[cnt] = true;
        powerset(cnt + 1);
        area[cnt] = false;
        powerset(cnt + 1);
    }

    private static boolean isLink() {
        // 각 구역 수 구하기
        int firstCnt = 0;
        int secondCnt = 0;
        for (int i = 0; i < N; i++) {
            if (area[i])
                firstCnt++;
            else
                secondCnt++;
        }

        // 각 구역이 연결되었는지 확인
        boolean firstFlag = false;
        boolean secondFlag = false;

        // true인 구역 확인 -> 가장 처음 구역만 확인
        for (int i = 0; i < N; i++) {
            if (area[i]) { // true 구역이라면
                firstFlag = bfs(i, true, firstCnt);
                // 빠져나와야함
                break;
            }
        }

        // false인 구역 확인
        for (int i = 0; i < N; i++) {
            if (!area[i]) { // false 구역이라면
                secondFlag = bfs(i, false, secondCnt);
                // 빠져나와야함
                break;
            }
        }

        // 둘 다 연결됐으면 이 부분집합은 잘 나눠진 구역입니다.
        if (firstFlag && secondFlag)
            return true;
        else
            return false;
    }

    private static boolean bfs(int idx, boolean flag, int total) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(idx);
        boolean[] v = new boolean[N];
        v[idx] = true;

        int cnt = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < N; i++) {
                if (i == cur)
                    continue; // 자신이라면
                if (v[i])
                    continue; // 방문했다면
                if (map[cur][i] == 0)
                    continue; // 연결되지 않았다면
                if (area[i] != flag)
                    continue; // 자신과 다른 구역이라면

                cnt++;
                v[i] = true;
                q.offer(i); // 다음 탐색할 구역으로 보기
            }
        }

        // 다 돌았을 때 구역수와 맞는지 확인
        if (cnt == total) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean getCnt() {
        boolean first = area[0];
        for (int i = 1; i < N; i++) {
            if (first != area[i]) // 같지 않은 것이 있다면 정상 부분집합
                return true;
        }
        return false;
    }

}
