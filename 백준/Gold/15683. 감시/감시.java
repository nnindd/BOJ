import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;
	static int[][] copyMap;
	static int[] direction;
	static int N, M, min = Integer.MAX_VALUE;
	static List<CCTV> cctvs = new ArrayList<>();// CCTV 위치와 번호를 저장할 리스트
	
	// CCTV 클래스
	static class CCTV {
		int x, y, number; // x, y 위치, CCTV 번호

		public CCTV(int x, int y, int number) {
			this.x = x;
			this.y = y;
			this.number = number;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M]; // 맵 크기 설정

		// 맵 입력 받고 CCTV 위치 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp; // 맵 입력
				if (temp >= 1 && temp <= 5) {
					// 씨씨티비 번호라면 pos에 추가
					cctvs.add(new CCTV(i, j, temp));
				}
			}
		} // end for

		direction = new int[cctvs.size()];
		
		dfs(0);
		System.out.println(min);
	}

	static void dfs(int dept) {
		if (dept == cctvs.size()) {// 씨씨티비 전부 선택됐다면
			
			//배열 복사
			copyMap = new int[N][M];
			for(int i=0; i<N; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, M);
			}
			
			//방향정하기
			for(int i=0; i<cctvs.size(); i++) {//지금 들어온 cctv 개수만큼
				//CCTV에서 dept 인덱스로 접근해서 가져오기
				int d = direction[i];
				CCTV cctv = cctvs.get(i);
				directionCCTV(d, cctv);
			}

			//0 카운트
			int count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) { //0인 영역 카운트
					if(copyMap[i][j]== 0) count++;
				}
			}
			min = Math.min(min, count); // 최소영역 최소값으로 설정
		}else {
			for(int i=0; i<4; i++) {
				direction[dept] = i;
				dfs(dept+1);
			}
		}
		
	}
	
	static void directionCCTV(int d, CCTV cctv) {
		if(cctv.number == 1) {
			changeMap(cctv, d);
		}else if(cctv.number == 2) {
			if(d==0||d==2) {
				changeMap(cctv, 0); //상하
				changeMap(cctv, 2);
			}else{
				changeMap(cctv, 1); //좌우
				changeMap(cctv, 3);
			}
		}else if(cctv.number == 3) {
			if(d==3) {
				changeMap(cctv, 0); //좌상
				changeMap(cctv, 3);
			}else {
				changeMap(cctv, d); //상우 우하 하좌
				changeMap(cctv, d+1);
			}
		}else if(cctv.number == 4) {
			if (d == 0) {
				changeMap(cctv, 0); //좌상우
				changeMap(cctv, 1);
				changeMap(cctv, 3);
            } else if (d == 1) {
            	changeMap(cctv, 0); //상우하
            	changeMap(cctv, 1);
            	changeMap(cctv, 2);
            } else if(d == 2) {
            	changeMap(cctv, 1); //좌하우
            	changeMap(cctv, 2);
            	changeMap(cctv, 3);
            } else if (d == 3) {
            	changeMap(cctv, 2); //상좌하
                changeMap(cctv, 3);
                changeMap(cctv, 0);
            }
		}else if(cctv.number == 5) {
			changeMap(cctv, 0); //상우하좌
			changeMap(cctv, 1);
			changeMap(cctv, 2);
			changeMap(cctv, 3);
		}
		
	}
	
	static void changeMap(CCTV cctv, int di) {
		
		int nx = cctv.x + dx[di];
		int ny = cctv.y + dy[di];
		
		while(nx>=0 && ny>=0 && nx<N && ny<M && copyMap[nx][ny]!=6) {
			copyMap[nx][ny] = 7;
			
			nx += dx[di];
			ny += dy[di];
		}
	}


}