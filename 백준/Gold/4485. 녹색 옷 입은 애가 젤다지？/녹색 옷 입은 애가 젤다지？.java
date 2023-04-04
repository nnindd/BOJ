import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static int[][] distance;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Edge implements Comparable<Edge>{
		int x, y, cost;

		public Edge(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost; //비용 오름차순 정렬
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc=1;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			
			if(N==0)
				break;
			
			map = new int[N][N];
			distance = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer tokens = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					distance[i][j] = Integer.MAX_VALUE; //거리를 최대값으로 채워줌
				}
			}//입력 완료
			
			dijkstra();
			
			sb.append("Problem ").append(tc).append(": ").append(distance[N-1][N-1]).append("\n");
			tc++;
			
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		//첫방문 처리
		visited[0][0] = true;
		distance[0][0] = map[0][0];
		pq.add(new Edge(0, 0, distance[0][0]));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			for(int d=0; d<4; d++) {
				int nx = cur.x + dr[d]; //4방 탐색
				int ny = cur.y + dc[d];
				
				if(isRange(nx, ny)) {
					int cost = cur.cost + map[nx][ny];
					if(cost < distance[nx][ny]) {
						distance[nx][ny] = cost;
						visited[nx][ny] = true;
						pq.add(new Edge(nx, ny, cost));
					}
				}
			}
			
		}
	}

	private static boolean isRange(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<N)
			return true;
		return false;
	}

}
