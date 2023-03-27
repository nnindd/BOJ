import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int result, N, M;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		//X Y가 친구다 4번 만족하면 완료
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		list = new ArrayList[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<Integer>(); 
		}
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			list[a].add(b);
			list[b].add(a);
			
		}//입력 완료
		
		result = 0;
		
		for(int i=0; i<N; i++) {
			visited = new boolean[N];
			if(result==1) break; //친구관계 확인 되면 반복문 탈출
			dfs(0, i); //주어진 친구 횟수만큼 연결 확인
		}
		
		System.out.println(result);
	}

	private static void dfs(int cnt, int cur) {
		if(cnt==4) {
			result = 1;
			return;
		}
		
		visited[cur] = true; //방문처리
		for(int i : list[cur]) {
			int temp = i;
			if(!visited[temp]) {
				dfs(cnt+1, temp);
			}
		}
		visited[cur] = false; //한번 다녀왔으면 다음 탐색을 위해 바꿔줌
		
	}
}
