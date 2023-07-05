import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos implements Comparable<Pos> {
        int dead, cnt;

        public Pos(int dead, int cnt) {
            this.dead = dead;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.dead == o.dead) return o.cnt - this.cnt;
            return this.dead - o.dead;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());
        Pos[] arr = new Pos[N]; //데드라인, 컵라면 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //선택된 컵라면 저장

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());

            arr[i] = new Pos(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
        }

        Arrays.sort(arr);

        for (Pos cur : arr){
            if(pq.size() < cur.dead){ //큐는 1이고 cur는 2라면 2일차의 과제 선택
                pq.offer(cur.cnt);
            }else if(pq.size() == cur.dead){ //큐도 1일차, 지금도 1일차일 때 가장 큰 컵라면 개수만 선택
                if(pq.peek() < cur.cnt){
                    pq.poll();
                    pq.offer(cur.cnt);
                }
            }
        }

        //선택된 컵라면 개수 더하기
        long sum = 0;
        while(!pq.isEmpty()){
            sum += pq.poll();
        }

        System.out.println(sum);

    }
}
