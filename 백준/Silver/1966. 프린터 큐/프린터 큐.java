import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer tokens;
		
		int T = Integer.parseInt(br.readLine());
		int N, M;
		
		while(T-- > 0) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken()); //문서 개수
			M = Integer.parseInt(tokens.nextToken()); //궁금한 문서
			
			int[] arr = new int[N];
			Deque<int[]> q = new ArrayDeque<>();
			
			tokens = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(tokens.nextToken());
				q.offer(new int[] {i, arr[i]}); //문서 번호와 중요도 저장
			}// end input
			
			Arrays.sort(arr);
			int idx = N-1; //중요도 인덱스. 내림차순 사용
			int cnt = 0;
			
			while(true) {
				if(q.isEmpty() || idx < 0)
					break;
				
				int[] cur = q.peek();
				if(cur[1] != arr[idx]) { //현재 중요도와 가장 높은 중요도가 같지 않다면 뒤로
					q.offer(q.poll());	
				}else { //현재 중요도와 가장 높은 중요도가 같다면 출력
					cnt++; //출력 횟수 증가
					if(cur[0] == M) {//현재 문서 번호가 찾으려는 문서 번호라면 끝
						sb.append(cnt).append("\n");
						break;
					}
					idx--; //다음 중요도로 인덱스 변경
					q.poll(); //문서 출력
				}
			}
		}//end test case
		System.out.println(sb);
	}
}
