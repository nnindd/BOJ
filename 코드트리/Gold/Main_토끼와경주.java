// 문제 링크 https://www.codetree.ai/training-field/frequent-problems/problems/rabit-and-race/description?page=3&pageSize=20

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, P;
    static long totalScore;
    static HashMap<Integer, Rabbit> rabbitMap;

    static class Rabbit implements Comparable<Rabbit> {
        int pid; //토끼 아이디
        int x, y; //토끼 위치
        int jumpCnt; //토끼의 점프 횟수
        long score; //토끼가 얻은 점수
        int cur; //토끼가 현재 점프한 턴
        int dis;

        public Rabbit(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Rabbit(int pid, int x, int y, int jumpCnt, long score, int cur, int dis) {
            this.pid = pid;
            this.x = x;
            this.y = y;
            this.jumpCnt = jumpCnt;
            this.score = score;
            this.cur = cur;
            this.dis = dis;
        }

        @Override
        public int compareTo(Rabbit a) {
            //점프 횟수 적은 토끼
            if (this.jumpCnt != a.jumpCnt) return this.jumpCnt - a.jumpCnt;
            //행번호+열번호 작은 토끼
            if (this.x + this.y != a.x + a.y) return (this.x + this.y) - (a.x + a.y);
            //행번호 작은 토끼
            if (this.x != a.x) return this.x - a.x;
            //열번호 작은 토끼
            if (this.y != a.y) return this.y - a.y;
            //고유번호 작은 토끼
            return this.pid - a.pid;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        rabbitMap = new HashMap<>();
        totalScore = 0;

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            tokens = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(tokens.nextToken());

            switch (order) {
                case 100: //경주 시작 준비
                    N = Integer.parseInt(tokens.nextToken()); //가로
                    M = Integer.parseInt(tokens.nextToken()); //세로
                    P = Integer.parseInt(tokens.nextToken()); //토끼의 수

                    for (int j = 0; j < P; j++) {
                        int pid = Integer.parseInt(tokens.nextToken()); //아이디
                        int distance = Integer.parseInt(tokens.nextToken()); //점프거리

                        //초기 토끼 위치 1,1로 설정
                        rabbitMap.put(pid, new Rabbit(pid, 1, 1, 0, 0, 0, distance));
                    }
                    break;

                case 200: //경주 진행
                    int K = Integer.parseInt(tokens.nextToken()); //턴 횟수
                    int S = Integer.parseInt(tokens.nextToken()); //턴 종료 후 점수

                    play(K, S);

                    break;

                case 300: //이동거리 변경
                    int pid = Integer.parseInt(tokens.nextToken());
                    int L = Integer.parseInt(tokens.nextToken()); //거리 증가

                    changeRabbitLength(pid, L);
                    break;

                case 400: //최고의 토끼 선정
                    //총 합계 모든 토끼에게 더해줌 (할 필요 있나?)
                    //우선순위 토끼 뽑음 (점프가 있는 경우만)
                    getRabbitOfWinner();
                    break;
            }
        }//end order;

    }//end main

    private static void getRabbitOfWinner() {
        long max = 0;
        for (Integer key : rabbitMap.keySet()) {
            Rabbit cur = rabbitMap.get(key);
            max = Math.max(max, cur.score + totalScore);
        }
        System.out.println(max);
    }

    private static void changeRabbitLength(int pid, int L) {
        Rabbit now = rabbitMap.get(pid);
        now.dis *= L;

        rabbitMap.replace(pid, now);
    }

    private static void play(int K, int S) {
        //1) 모든 토끼 우선순위 큐에 넣어줌
        PriorityQueue<Rabbit> pq = new PriorityQueue<>();
        rabbitMap.forEach((k, v) -> {
            pq.offer(v);
        });

        //2) K번 경주 진행
        for (int i = 0; i < K; i++) {
            Rabbit now = pq.poll(); //달릴 토끼
//            System.out.println("선택된 토끼 >> " + now.pid + " " + now.x + "," + now.y + " dis : " + now.dis);

            Rabbit res = new Rabbit(now.pid, 0, 0, now.jumpCnt, now.score, now.cur, now.dis);
            //상하좌우로 이동해서 가장 우선순위가 높은 거리 찾음
            Rabbit up = upRabbit(new Rabbit(now.pid, now.x, now.y, now.jumpCnt, now.score, now.cur, now.dis));
            if (compareRabbit(res, up)) res = up;

            Rabbit down = downRabbit(new Rabbit(now.pid, now.x, now.y, now.jumpCnt, now.score, now.cur, now.dis));
            if (compareRabbit(res, down)) res = down;

            Rabbit left = leftRabbit(new Rabbit(now.pid, now.x, now.y, now.jumpCnt, now.score, now.cur, now.dis));
            if (compareRabbit(res, left)) res = left;

            Rabbit right = rightRabbit(new Rabbit(now.pid, now.x, now.y, now.jumpCnt, now.score, now.cur, now.dis));
            if (compareRabbit(res, right)) res = right;

//            System.out.println("나온 위치들");
//            System.out.println("상 >> " + up.x + "," + up.y);
//            System.out.println("하 >> " + down.x + "," + down.y);
//            System.out.println("좌 >> " + left.x + "," + left.y);
//            System.out.println("우 >> " + right.x + "," + right.y);

            //토끼 이동 및 맵에 변한 값 추가
            res.jumpCnt++;
            res.cur = K;
            res.score -= (res.x + res.y);
            totalScore += (res.x + res.y);

            rabbitMap.replace(res.pid, res);

            //다시 큐에 넣어줌
            pq.offer(res);

//            System.out.println(res.pid+ " 이동할 위치 >> " + res.x + "," + res.y + " score: " + res.score);
        }

        //3) 보너스 점수 토끼 뽑음
        Rabbit sRabbit = new Rabbit(0, 0, 0, 0, 0, 0, 0);
        while (!pq.isEmpty()) {
            Rabbit now = pq.poll();

            if (now.cur != K) continue;

            //이번 턴에서 달린 토끼들 값 수정해주기
            now.cur = 0;
            rabbitMap.replace(now.pid, now);

            if (compareRabbit(sRabbit, now)) {
                sRabbit = now;
            }
        }

//        System.out.println("보너스 점수 얻을 토끼 >> " + sRabbit.pid + " " + sRabbit.x + "," + sRabbit.y);

        sRabbit.score += S;
        rabbitMap.replace(sRabbit.pid, sRabbit);

//        System.out.println("토끼들 정보 출력 >> ");
//        for (int key : rabbitMap.keySet()) {
//            Rabbit cur = rabbitMap.get(key);
//            System.out.println(cur.pid + " | " + cur.x + "," + cur.y + " jc:" + cur.jumpCnt);
//        }
    }

    private static boolean compareRabbit(Rabbit a, Rabbit b) {
        if ((a.x + a.y) != (b.x + b.y)) return (a.x + a.y) < (b.x + b.y);
        if (a.x != b.x) return a.x < b.x;
        if (a.y != b.y) return a.y < b.y;
        return a.pid < b.pid;
    }

    // 토끼를 위로 이동시킵니다.
    public static Rabbit upRabbit(Rabbit curRabbit) {
        Rabbit upRabbit = curRabbit;
        int dis = curRabbit.dis;

        dis %= 2 * (N - 1);

        if (dis >= upRabbit.x - 1) {
            dis -= (upRabbit.x - 1);
            upRabbit.x = 1;
        } else {
            upRabbit.x -= dis;
            dis = 0;
        }

        if (dis >= N - upRabbit.x) {
            dis -= (N - upRabbit.x);
            upRabbit.x = N;
        } else {
            upRabbit.x += dis;
            dis = 0;
        }

        upRabbit.x -= dis;

        return upRabbit;
    }

    // 토끼를 아래로 이동시킵니다.
    public static Rabbit downRabbit(Rabbit curRabbit) {
        Rabbit downRabbit = curRabbit;
        int dis = curRabbit.dis;

        dis %= 2 * (N - 1);

        if (dis >= N - downRabbit.x) {
            dis -= (N - downRabbit.x);
            downRabbit.x = N;
        } else {
            downRabbit.x += dis;
            dis = 0;
        }

        if (dis >= downRabbit.x - 1) {
            dis -= (downRabbit.x - 1);
            downRabbit.x = 1;
        } else {
            downRabbit.x -= dis;
            dis = 0;
        }

        downRabbit.x += dis;

        return downRabbit;
    }

    // 토끼를 왼쪽으로 이동시킵니다.
    public static Rabbit leftRabbit(Rabbit curRabbit) {
        Rabbit leftRabbit = curRabbit;
        int dis = curRabbit.dis;

        dis %= 2 * (M - 1);

        if (dis >= leftRabbit.y - 1) {
            dis -= (leftRabbit.y - 1);
            leftRabbit.y = 1;
        } else {
            leftRabbit.y -= dis;
            dis = 0;
        }

        if (dis >= M - leftRabbit.y) {
            dis -= (M - leftRabbit.y);
            leftRabbit.y = M;
        } else {
            leftRabbit.y += dis;
            dis = 0;
        }

        leftRabbit.y -= dis;

        return leftRabbit;
    }

    // 토끼를 오른쪽으로 이동시킵니다.
    public static Rabbit rightRabbit(Rabbit curRabbit) {
        Rabbit rightRabbit = curRabbit;
        int dis = curRabbit.dis;

        dis %= 2 * (M - 1);

        if (dis >= M - rightRabbit.y) {
            dis -= (M - rightRabbit.y);
            rightRabbit.y = M;
        } else {
            rightRabbit.y += dis;
            dis = 0;
        }

        if (dis >= rightRabbit.y - 1) {
            dis -= (rightRabbit.y - 1);
            rightRabbit.y = 1;
        } else {
            rightRabbit.y -= dis;
            dis = 0;
        }

        rightRabbit.y += dis;

        return rightRabbit;
    }


}
/*
5
100 3 5 2 10 2 20 5
200 5 100
300 10 2
200 2 20
400

>> 122
 */
