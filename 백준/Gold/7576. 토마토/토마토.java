import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, M, res;
	static int[][] map;
	static boolean[][] v; // 방문체크
	static Deque<Pos> q;

	static class Pos {
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		q = new ArrayDeque<>();
		map = new int[M][N];
		v = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if (map[i][j] == 1) {
					q.offer(new Pos(i, j, 0));
				}
			}
		}

		res = 0;
		bfs();
		check();
		System.out.println(res);
	}

	private static void check() {
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0) {
					res = -1;
					break;
				}
			}
		}
	}

	private static void bfs() {
		Pos cur;
		while (!q.isEmpty()) {
			cur = q.poll();
			v[cur.x][cur.y] = true;
			
			res = cur.cnt;
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dr[d];
				int ny = cur.y + dc[d];
				
				if(nx<0||ny<0||nx>=M||ny>=N) continue;
				if(v[nx][ny]) continue;
				if(map[nx][ny] == 1 || map[nx][ny]==-1) continue;
				map[nx][ny] = 1;
				q.offer(new Pos(nx, ny, cur.cnt+1));
			}
		}
	}

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
}
