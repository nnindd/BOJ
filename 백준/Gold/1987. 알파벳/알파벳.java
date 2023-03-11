import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, max=0;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[] visited = new boolean[26]; //A~Z

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());

		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j)-'A';
			}
		}

		move(0, 0, 0);
		
		System.out.println(max);
		
	}// end main
	
	static void move(int x, int y, int count) {
		
		if(visited[map[x][y]]) { //방문한 곳이면 count값 갱신해줌
			max = Math.max(max, count);
			return;
		}
		else {
			visited[map[x][y]] = true;
			for (int d = 0; d < 4; d++) {
				int nx = x + dr[d];
				int ny = y + dc[d];
				
				if (nx >= 0 && nx < R && ny >= 0 && ny < C) { //범위를 벗어나지 않는다면 다음 탐색
					move(nx, ny, count+1);
				}
			}//end for
			
			visited[map[x][y]] = false; //갔다왔으면 바꿔줌
		}//end else
		
	}
}
