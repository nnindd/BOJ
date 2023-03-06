import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 0, 1 }; //상 멈춤 하 순으로 이동해야 최대 파이프 값을 구할 수 있음
	static int R, C; //행열 값
	static char[][] map; //원본 지도
	static boolean[][] check; //지도 방문 체크 배열
	static int cnt; //전체 파이프 개수 저장할 값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열

		//지도와 체크 배열 생성
		map = new char[R][C];
		check = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j);
			}
		}

		//전체 행만큼 진행
		for (int i = 0; i < R; i++) {
			goPipe(i, 0);
		}
		
		System.out.println(cnt);

	}// end main

	static boolean goPipe(int x, int y) {
		check[x][y] = true; //방문한 곳을 true로 바꿔줌
		
		if(y == C-1) { //y가 끝까지 왔다면 파이프 연결에 성공함. 파이프 개수 증가시켜줌
			cnt++;
			return true;
		}
		
		for (int d = 0; d < 3; d++) { //방향대로 순서 진행
			int nx = x + dx[d];
			int ny = y + 1;

			if (nx >= 0 && nx < R && ny >= 0 && ny < C) { //해당 범위 내라면
				if (map[nx][ny] == '.' && check[nx][ny]== false) { //갈 수 있고 방문하지 않았다면
					check[nx][ny] = true; //방문했다고 표시해줌
					if(goPipe(nx, ny)) //상 멈춤 하 순서로 방문하기 위해 재귀호출. 무사히 도착했다면 true받음
						return true;
				}
			}
		}
		return false; //끝까지 방문하지 못함. 다음 방향 진행
	}

} 
