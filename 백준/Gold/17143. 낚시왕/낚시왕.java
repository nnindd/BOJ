import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, M;
	static int[][] map;
	static Shark[] sharks;
	
	static class Shark{
		int x, y, speed, dir, size;

		public Shark(int x, int y, int speed, int dir, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark [x=" + x + ", y=" + y + ", speed=" + speed + ", dir=" + dir + ", size=" + size + "]";
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken()); // 격자판 크기
		C = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken()); // 상어의 수
		
		if(M==0) {
			System.out.println(0);
			System.exit(0);
		}
		
		map = new int[R+1][C+1];
		
		//상어 저장
		sharks = new Shark[M+1];
		for (int i = 1; i <= M; i++) {
			tokens = new StringTokenizer(br.readLine());
			sharks[i] = new Shark(Integer.parseInt(tokens.nextToken()),
									Integer.parseInt(tokens.nextToken()), 
									Integer.parseInt(tokens.nextToken()), 
									Integer.parseInt(tokens.nextToken()), 
									Integer.parseInt(tokens.nextToken()));
			map[sharks[i].x][sharks[i].y] = i; //맵에 상어의 인덱스 저장
		}//end input
		
		//맵에 상어 설정하기

		System.out.println(fishing());
	}

	private static int fishing() {
		int sum = 0; //낚시왕이 잡은 물고기의 수
		
		//낚시하기
		for(int j=1; j<=C; j++) {//낚시왕 이동 k=1~C까지
			//상어 잡음
			for(int i=1; i<=R; i++) {
				//행단위로 내려가면서 먼저 만나면 그 상어 잡음
				if(map[i][j]!=0) {
					//System.out.println("잡은 상어>>"+sharks[map[i][j]].toString());
					sum += sharks[map[i][j]].size; //잡은 상어 크기 더함
					//상어 비워줌
					sharks[map[i][j]] = null;
					map[i][j] = 0; //맵 비워줌
					break;
				}
			}
			
//			System.out.println("낚시꾼 위치 >> "+j);
			int[][] temp = new int[R+1][C+1];
			//상어 이동
			for(int i=1; i<=M; i++) {
				if(sharks[i]!=null) //먹힌 상어가 아닌 것만 반복
					moveShark(i, sharks[i], temp);
			}
			//모두 이동을 마치고 최종 위치를 확인해야 함
			//모든 상어가 이동을 마쳤으면 원본 배열에 temp 배열 복사
			copyMap(map, temp);
		}
		
		return sum;
	}

	private static void copyMap(int[][] origin, int[][] temp) {
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				origin[i][j] = temp[i][j];
			}
		}
	}

	private static void moveShark(int idx, Shark shark, int[][] temp) {
		//상어 방향에 따라 이동
		int cnt = shark.speed;
		int x = shark.x;
		int y = shark.y;
		int dir = shark.dir;
//		System.out.print(idx+"번째 상어 이동(사이즈"+shark.size+") >> ("+x+","+y+")");
		
		while (cnt > 0) { //스피드가 0이 될때까지 이동
			//방향에 맞춰 상어 이동
			if(dir==1) x--;
			else if(dir==2) x++;
			else if(dir==3) y++;
			else if(dir==4) y--;
			
			if(x<=0 || x>R || y<=0 || y>C) { //끝부분에 닿는다면
				//방향 바꿔줌
				if(dir==1) {
					dir = 2;
					x = 1;
				}
				else if(dir==2) {
					dir = 1;
					x = R;
				}
				else if(dir==3) {
					dir = 4;
					y = C;
				}
				else if(dir==4) {
					dir = 3;
					y = 1;
				}
				continue;
			}
			
			cnt--; //이동거리 감소
		}
		
		if(x<=0 || x>R || y<=0 || y>C) { //반복문 나왔는데 끝부분에 위치한다면
			//방향 바꿔줌
			if(dir==1) {
				dir = 2;
				x = 1;
			}
			else if(dir==2) {
				dir = 1;
				x = R;
			}
			else if(dir==3) {
				dir = 4;
				y = C;
			}
			else if(dir==4) {
				dir = 3;
				y = 1;
			}
		}
//		System.out.print("이동 후 위치 >> "+ x +" "+ y + " >> ");
		
		//상어 최종 이동지 배열에 상어를 위치함
		//상어 이동 마친 위치에 다른 상어 있는지 확인
		if(temp[x][y]!=0) {
			//크기 비교
			if(sharks[temp[x][y]].size > shark.size) { //원래 맵에 있던 상어가 더 크다면
				//상어배열에서 현재 상어 삭제 
				sharks[idx] = null;
//				System.out.println("먹힘");
			}else if(sharks[temp[x][y]].size < shark.size) { //지금 상어가 더 크다면
//				System.out.println(" !!"+temp[x][y]+"상어 먹힘!! ");
				//상어 배열에서 이전 상어 삭제
				sharks[temp[x][y]] = null;
				//맵에 현재 상어 삽입
				temp[x][y] = idx; 
				//상어의 원래 방향과 위치 수정해줌
				shark.x = x;
				shark.y = y;
				shark.dir = dir;
//				System.out.println("안전하게 도착 !");
			}
		}else {//맵에 상어가 없다면 맵에 상어 배치
			temp[x][y] = idx;
			//상어의 원래 방향과 위치 수정해줌
			shark.x = x;
			shark.y = y;
			shark.dir = dir;
//			System.out.println("안전하게 도착 !");
		}
		
	}

}
