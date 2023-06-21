import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int res = 0;
        //제외할 값
        int sub = (int) Math.round(N * 0.15);

        //최대 우선순위큐, 최소 우선순위큐 설정
        PriorityQueue<Integer> max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        PriorityQueue<Integer> min = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            max.offer(n);
            min.offer(n);
            res += n; //합
        }

        //sub 개수 만큼 max, min에서 제외
        for (int i = 0; i < sub; i++) {
            int n = max.poll();
            res -= n;
            n = min.poll();
            res -= n;
        }

        //결과 계산
        double avg = (res * 1.0) / (N - sub * 2);
        System.out.println(Math.round(avg));
    }
}
