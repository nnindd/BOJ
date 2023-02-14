import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Shark{
		int r, c;

		public Shark() {}
		
		public Shark(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		Shark shark = new Shark(); //초기 상어 정보 저장
		
		for(int i=0; i<N; i++) {
			StringTokenizer tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 9) { //상어 위치라면 상어 x y 좌표 저장해줌
					shark = new Shark(i, j);
					map[i][j] = 0; //이동 가능하도록 상어 위치 값을 0으로 변경
				}
			}
		}//입력 끝
		
		int size = 2; //상어 초기 사이즈
		int eat = 0; //상어가 먹은 물고기의 횟수
		int res = 0; //움직인 시간
		
		while (true) {
			
			//물고기 위치
			int x=Integer.MAX_VALUE;
			int y=Integer.MAX_VALUE;
			int d=Integer.MAX_VALUE;
			
			Queue<Shark> q = new LinkedList<>();
			int[][] dist = new int[N][N]; //이동거리 저장
			
			q.add(new Shark(shark.r, shark.c)); //현재 상어 위치
			
			while (!q.isEmpty()) {
				Shark cur = q.poll();
				
				for(int dir=0; dir<4; dir++) {
					int nx = cur.r + dr[dir];
					int ny = cur.c + dc[dir];
					
					if(nx<0 || ny<0 || nx>=N || ny>=N) continue; //범위 초과
					if(map[nx][ny] > size) continue; //큰 사이즈의 물고기면 못 지나감
					if(dist[nx][ny]!=0) continue; //이미 방문한 위치는 가지 않도록 함(무한반복방지)
					
					dist[nx][ny] = dist[cur.r][cur.c] + 1; //이동횟수
					
					if(map[nx][ny]!=0 && map[nx][ny]<size) { //먹을 수 있는 물고기일때
						if(d>dist[nx][ny]) { //가까운 물고기
							d = dist[nx][ny];
							x = nx;
							y = ny;
						}else if(d==dist[nx][ny]) { //거리가 같다면 
							if(nx==x) { //왼쪽
								if(y>ny) {
									x=nx;
									y=ny;
								}
							}else if(nx<x) { //위쪽
								x=nx;
								y=ny;
							}
						}
					}
					q.add(new Shark(nx, ny)); //상어의 현재 위치 갱신
					
				}
			}
			
			if(x==Integer.MAX_VALUE && y==Integer.MAX_VALUE) { //물고기가 없는 경우
				break;
			}
			
			res += dist[x][y]; //이동 횟수 저장
			map[x][y] = 0; //물고기 먹었다고 표시
			
			shark.r = x; //상어 현재 위치
			shark.c = y;
			
			eat++; //먹은 물고기의 수 증가
			
			if(eat==size) { //먹은 횟수와 상어 사이즈가 같다면 사이즈 증가, 먹은 횟수 초기화
				size++;
				eat=0;
			}
		}//end while
		
		System.out.println(res);
		
	}//end main

}//end class
