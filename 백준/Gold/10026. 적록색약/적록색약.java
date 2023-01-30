import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static char[][] grid;
	static boolean[][] visited;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		grid = new char[N][N];

		for (int i = 0; i < N; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		// 입력 끝

		int[] res = { 0, 0 }; // 비적록색약, 적록색약

		visited = new boolean[N][N]; // 방문체크배열

		// 비적록색약
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					res[0]++; // 더이상 탐색할 범위가 없음 -> RGB 구역 증가
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == 'G')
					grid[i][j] = 'R'; // 적록색약 구별위해 R과 G를 하나로 만들어줌
			}
		}

		visited = new boolean[N][N]; // 다시 초기화해줌
		
		// 적록색약
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					res[1]++;
				}
			}
		}
		
		for (int i : res) {
			System.out.print(i+" ");
		}

	}

	private static void dfs(int x, int y) {

		visited[x][y] = true;
		char check = grid[x][y]; // 확인할 문자 저장

		for (int d = 0; d < 4; d++) {
			int nx = x + dr[d];
			int ny = y + dc[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;

			if (!visited[nx][ny] && grid[nx][ny] == check) // 다음 장소를 방문하지 않았고, 같은 문자일 경우
				dfs(nx, ny); // 다음 위치부터 다시 탐색
		}
	}
}
