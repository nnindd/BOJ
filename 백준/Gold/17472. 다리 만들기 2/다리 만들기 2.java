import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, areaIdx;
	static final int INF = 999;
	static int[][] map, bridge;
	static boolean[][] v;
	static List<Pos> one;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		one = new ArrayList<>();
		map = new int[N][M];
		v = new boolean[N][M];

		bridge = new int[7][7]; // 0번째 섬 사용 안함
		for (int i = 0; i < 7; i++) {
			Arrays.fill(bridge[i], INF);
		}

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if (map[i][j] == 1) {
					one.add(new Pos(i, j)); // 1인 영역 모두 저장
				}
			}
		} // end input

		// bfs로 영역을 구함
		Pos cur;
		areaIdx = 1;
		for (int i = 0; i < one.size(); i++) {
			cur = one.get(i);
			if (v[cur.x][cur.y])
				continue; // 이미 영역이 구해졌다면 진행 안 함
			bfsForArea(cur); // 확인할 현재 위치와 맵을 채울 인덱스
		}
		areaIdx -= 2; // 인덱스 하나 줄여줌

		// 한 영역씩 다리를 만들 수 있는지 확인. 다리를 만든다면 브릿지 행렬에 다리 최소값으로 저장
		for (int i = 1; i <= areaIdx; i++) {
			for (int j = 0; j < one.size(); j++) {
				cur = one.get(j);
				if (map[cur.x][cur.y] == i) {// 맵에 표시된 구역 번호가 맞는 경우만 탐색함
					bfsForFindBridge(cur, i);
				}
			}
		}

		// prim 알고리즘으로 최소 연결 구하기
		int res = findMinimum();
		System.out.println(res == 0 ? -1 : res);
	}

	static class Edge implements Comparable<Edge> {
		int s, e, cost;

		public Edge(int s, int e, int cost) {
			this.s = s;
			this.e = e;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static int[] parent;
	static PriorityQueue<Edge> pq;

	private static void make() {
		// parent = new int[areaIdx+1];
		for (int i = 1; i <= areaIdx; i++) {
			parent[i] = i;
		}
	}

	private static int find(int a) {
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	private static boolean union(int a, int b) {
		int A = find(a);
		int B = find(b);

		if (A == B)
			return false;
		parent[B] = A;
		return true;
	}

	private static int findMinimum() {
		parent = new int[7];
		pq = new PriorityQueue<>();

		make();

		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				if (bridge[i][j] != INF) {
					pq.add(new Edge(i, j, bridge[i][j]));
				}
			}
		}
		
		int result = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.s, edge.e)) {
				result += edge.cost;
			}
		}
		
		int p = find(1);
		for(int i=1; i<7; i++) {
			if(find(i)!=p) {
				result = 0;
			}
		}
		
		return result;

	}

	private static void bfsForFindBridge(Pos cur, int i) {
		// bfs로 현재에서 사방으로 가봄
		for (int d = 0; d < 4; d++) {
			int cnt = 0;
			int nx = cur.x + dr[d];
			int ny = cur.y + dc[d];

			while (isRange(nx, ny)) { // 범위 내

				if (map[nx][ny] == i)
					break; // 본인 영역이면 안감

				nx += dr[d];
				ny += dc[d]; // 한 방향으로 계속 진행

				if (!isRange(nx, ny))
					break;

				cnt++;

				if (map[nx][ny] != 0 && map[nx][ny] != i) { // 0이 아니고 현재 구역이 아닌 곳에 닿았을 때
					// 2미만이면 넘겨줌
					if (cnt < 2)
						break;
					int other = map[nx][ny];
					if (bridge[i][other] > cnt) { // 연결된 다리의 수가 최소값이라면
						bridge[i][other] = cnt;
						bridge[other][i] = cnt;
						break; // 이 방향 탐색 종료
					}
					break; //다른 섬이 막고 있으면 못감!!
				}
			} // end while
		} // end for

	}

	private static boolean isRange(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return false;
		return true;
	}

	private static void bfsForArea(Pos cur) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(cur.x, cur.y));
		v[cur.x][cur.y] = true;
		map[cur.x][cur.y] = areaIdx; // 맵을 현재 구역의 번호로 새로 갱신해줌

		while (!q.isEmpty()) {
			Pos now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dr[d];
				int ny = now.y + dc[d];

				if (!isRange(nx, ny) || v[nx][ny])
					continue;

				if (map[nx][ny] == 1) {// 인접한 영역
					map[nx][ny] = areaIdx;
					v[nx][ny] = true;
					q.offer(new Pos(nx, ny));
				}
			}
		}
		areaIdx++;

	}

}
