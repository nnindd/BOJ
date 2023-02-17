import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int sum = 0;
        int temp;
        while (pq.size() > 1){ //2개씩 뽑아야 함
            temp = pq.poll() + pq.poll();
            sum += temp;
            pq.offer(temp);
        }
        System.out.println(sum);
    }

}
