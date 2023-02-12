import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	// 인접한 곳 끼리 하나의 단지로 만든다
	// =>한곳을 지정해서 더이상 방문할곳이 없을 때까지 자식들을 넣음. 큐에서 뺀 횟수로 가넝한
	// 단지에 속하는 집의 개수를 구한다
	// 오름차순으로 정렬한다

	static int N, total;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static ArrayList<Integer> danji; // 단지의 갯수를 오름차순으로 정렬할 리스트

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		// 입력 끝

		danji = new ArrayList<>();
		boolean[][] v = new boolean[N][N];
		total = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==1 && !v[i][j]) { //continue로 처리하지 말고 해당 부분만 처리할 수 있도록 해야함. 여기서 계속 틀렸다
					bfs(i, j, v);
					total++;
				}
			}
		}

		System.out.println(total);
		
		Collections.sort(danji);

		for (int i : danji) {
			System.out.println(i);
		}

	}

	private static void bfs(int x, int y, boolean[][] v) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		int cnt = 1;
		v[x][y] = true;

		while (!queue.isEmpty()) {
			Point cur = queue.poll(); // 현재 위치 뽑아옴

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dr[d];
				int ny = cur.y + dc[d];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if (map[nx][ny] == 1 && !v[nx][ny]) {
						// 범위내에 있고 집이 존재하고 방문하지 않았다면
						v[nx][ny] = true; // 방문처리
						queue.add(new Point(nx, ny)); // 새로 넣어줌
						cnt++; // 큐에서 뽑은 만큼 연결된 단지
					}

				}
			}
		}
		
		danji.add(cnt);
	}
}
