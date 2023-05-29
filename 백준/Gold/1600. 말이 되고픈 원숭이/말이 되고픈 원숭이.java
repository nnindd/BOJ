import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] jump = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } }; // 오른쪽맨위부터
																														// 시계방향으로
																														// 8개
	static int k;
	static int w, h;
	static int[][] map;
	static boolean[][][] visit;
	

	static public class Monkey {
		int k;
		int x;
		int y;
		int cnt;

		public Monkey(int k, int cnt, int x, int y) {
			this.k = k;
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Queue<Monkey> queue = new LinkedList<>();

		k = sc.nextInt(); // 말 처럼 움직일 수 있는 회수
		w = sc.nextInt();
		h = sc.nextInt();

		map = new int[h][w];
		visit = new boolean[k + 1][h][w];

		// 입력받기
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				map[i][j] = sc.nextInt();

			}
		}

		visit[0][0][0] = true; // 방문표시
		queue.add(new Monkey(0, 0, 0, 0)); // k가 0일때 부터 시작

		while (!queue.isEmpty()) {
			Monkey m = queue.poll();
			// 추가 처리
			if (m.x == h - 1 && m.y == w - 1) { // 끝에 도달했을 때,
				// System.out.println(m.x + " " + m.y);
				System.out.println(m.cnt);
				return;

			}

			// m.k 가 말처럼 뛸 수 있는 최대 횟수와 같다면, 원숭이처럼만 뛰어야 한다.

			for (int d = 0; d < 4; d++) { // 4방 서치
				int nr = m.x + dr[d];
				int nc = m.y + dc[d];
				int cnt = m.cnt;
				int nextmap = m.k;
				if (nr < 0 || nc < 0 || nr >= h || nc >= w)
					continue; // boundary check
				// 벽인경우
				if (map[nr][nc] == 1)
					continue;

				if (visit[nextmap][nr][nc])
					continue; // 방문했으면

				// 들린적이 없거나, 벽이 아닌경우에는
				visit[nextmap][nr][nc] = true ; // 방문처리
				queue.add(new Monkey(nextmap, cnt + 1, nr, nc)); // 
			}
			// 4방 서치 마지막 다 한다음에
			// m.k 가 주어진 K와 같다면 더이상 말처럼 뛰지 못한다.
			if ( m.k == k) continue;

			for (int d = 0; d < 8; d++) {
				int nr = m.x + jump[d][0];
				int nc = m.y + jump[d][1];
				int cnt = m.cnt;
				int nextmap = m.k + 1;
				if (nr < 0 || nc < 0 || nr >= h || nc >= w)
					continue; // boundary check
				// 벽인경우
				if (map[nr][nc] == 1)
					continue;
				
				if (visit[nextmap][nr][nc])
					continue; // 방문했으면 패스
				// 들린적이 없거나, 벽이 아닌경우에는
				// m.k는 while 실행 시 , 이전에 취한 원숭이 움직임의 종류
				visit[nextmap][nr][nc] = true ; // 방문처리
				queue.add(new Monkey(nextmap, cnt + 1, nr, nc)); // k 하나 올리고

			}

		}
		
		System.out.println("-1");
	}
}