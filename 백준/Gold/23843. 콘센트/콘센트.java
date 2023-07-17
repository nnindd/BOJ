import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        Integer[] arr = new Integer[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        //내림차순 정렬
        Arrays.sort(arr, (a, b) -> {
            return b - a;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            if(pq.size() < M) { //큐에 M개만큼 넣어줌
                pq.offer(arr[i]);
            }else{ //충전기가 다 찬 경우
                //하나를 빼줌
                //새로 넣을 때 뺀 값을 더해서 넣음
                pq.offer(pq.poll() + arr[i]);
            }
        }

        //최대값을 뽑을 큐
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        while (!pq.isEmpty()){
            pq2.offer(pq.poll());
        }

        System.out.println(pq2.poll());
    }
}
