import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int result;
	static int[] dice, X, Y;
	static int[][] map;
	static boolean[][] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());

		dice = new int[10];
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(tokens.nextToken());
		} // end input

		X = new int[4];
		Y = new int[4];
		map = new int[4][30];
		v = new boolean[4][30];

		setMap();
		
		result = 0;
		dfs(0, 0); //말을 하나씩 선택하면서 dfs 돌림
		System.out.println(result);
	}

	private static void dfs(int cnt, int sum) {
		if (cnt == 10) {
			result = Math.max(result, sum);
			return;
		}
		for (int i = 0; i < 4; i++) { //말을 4개 중 한개를 선택함. 선택하지 못하는 조건이라면 continue
			int tempX = X[i]; //임시 저장
			int tempY = Y[i];
			
			if(map[X[i]][Y[i]] == -1) continue; //말이 도착지점을 넘었다면 이 말 선택 안 함
			
			v[X[i]][Y[i]] = false; //이동하기 전 방문 취소
			
			//말이 외곽라인에 있을 때 파란색 위치에 있는지 확인
			if(X[i]==0) { 
				if(Y[i]==5) X[i] = 1; //10이면 1번 라인으로 
				else if(Y[i]==10) X[i] = 2; //20이면 2번 라인으로 
				else if(Y[i]==15) X[i] = 3; //30이면 3번 라인으로 
			}
			
			Y[i] += dice[cnt]; //현재 cnt에 해당하는 주사위 값만큼 이동 증가시켜줌
			
			//말이 외곽라인이 아닐 때 끝줄인 25 30 35 40에 대한 설정 => 40이라면 외곽라인으로 이동, 나머지는 1번라인에서 동시에 처리
			if(X[i]!=0) { 
				if(map[X[i]][Y[i]]==25) {
					X[i] = 1;
					Y[i] = 9;
				}else if(map[X[i]][Y[i]]==30) {
					X[i] = 1;
					Y[i] = 10;
				}else if(map[X[i]][Y[i]]==35) {
					X[i] = 1;
					Y[i] = 11;
				}else if(map[X[i]][Y[i]]==40) {
					X[i] = 0;
					Y[i] = 20;
				}
			}
			
			if(!v[X[i]][Y[i]]) { //다음 위치로 옮겼는데 방문하지 않았다면
				if(map[X[i]][Y[i]] != -1) { //종료지점이 아니라면
					v[X[i]][Y[i]] = true; //방문처리
					dfs(cnt+1, sum + map[X[i]][Y[i]]); //다음 말 선택 dfs
					v[X[i]][Y[i]] = false; //말 선택 안 했을시 방문 비워줌
				}else { //해당 말이 종료했다면
					dfs(cnt+1, sum); //값 갱신하지 않고 다음 주사위 진행
				}
			}
			
			//다음 말 선택을 위해 원래대로 바꿔줌
			X[i] = tempX;
			Y[i] = tempY;
			v[X[i]][Y[i]] = true;
			
		}
	}

	

	private static void setMap() {
		// 외곽 라인. 끝에서 주사위 범위 초과 안 나게 설정
		//		             0 1 2 3 4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 
		map[0] =  new int[] {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,-1,-1,-1,-1,-1,-1,-1};
		map[1] =  new int[] {0,0,0,0,0,10,13,16,19,25,30,35,40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		map[2] =  new int[] {0,0,0,0,0, 0, 0, 0, 0, 0,20,22,24,25,30,35,40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		map[3] =  new int[] {0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,30,28,27,26,25,30,35,40,-1,-1,-1,-1,-1};
	}

}
