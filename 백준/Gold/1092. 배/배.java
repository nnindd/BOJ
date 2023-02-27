import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        ArrayList<Integer> crane = new ArrayList<>();
        ArrayList<Integer> box = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane.add(Integer.parseInt(tokens.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(tokens.nextToken()));
        }

        System.out.println(solve(N, M, crane, box));
    }

    private static int solve(int n, int m, ArrayList<Integer> crane, ArrayList<Integer> box) {
        //가장 허용량이 큰 크레인부터
        //가장 무거운 박스를 담는다
        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        //가장 허용량이 큰 크레인이 가장 무거운 박스를 담지 못한다면 운반 불가능
        if (crane.get(0) < box.get(0))
            return -1;

        int time = 0; //운반 시간

        while (!box.isEmpty()) { //박스를 다 옮길때까지 반복
            time++; //한번에 하나 이동
            int idx = 0; //가장 처음 박스 선택
            for (int i = 0; i < n; ) { //허용량 큰 크레인부터 진행
                if(box.size() == 0){
                    break;
                }
                // 박스를 전부 탐색했으면 처음 크레인으로 가야함
                // or
                // 지금 크레인이 가장 가벼운 박스를 담을 수 없으면 앞에도 못 담으니 차음 크레인으로 가야함
                if (idx >= box.size() || crane.get(i) < box.get(box.size() - 1)) {
                    break;
                }
                if (crane.get(i) >= box.get(idx)) {//박스를 담을 수 있는 경우
                    box.remove(idx); //박스 제거. 삭제시 인덱스 자동으로 당겨와짐
                    i++; //다음 크레인으로
                } else { //박스를 담지 못하는 경우
                    //현재 크레인 유지, 다음 박스로 이동
                    idx++;
                }
            }
        }
        return time;
    }
}
