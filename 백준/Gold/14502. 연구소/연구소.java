import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, zeroCnt, result;
	static int[][] map;
	static ArrayList<Point> list = new ArrayList<>();
	static ArrayList<Point> virus = new ArrayList<>();
	
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		int[][] newMap = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j]==0) {
					list.add(new Point(i, j));
				}else if(map[i][j]==2) {//바이러스 위치 넣음
					virus.add(new Point(i, j));
				}
			}
		}//end input
		
		zeroCnt = list.size(); //초기 안전영역의 크기
		
		//벽 세울 3곳 조합 => 0인 곳에서 고르기
		//바이러스 퍼지게 하기
		//	- 상하좌우 인접한 칸으로 모두 퍼짐. 더 이상 퍼질 수 없을 때까지 반복. 퍼질때마다 zero카운트--
		//zeroCnt 최대값 갱신
		result = 0;
		copyMap(map, newMap);
		comb(0, 0, newMap, new int[3]);
		System.out.println(result);
	}

	private static void comb(int cnt, int start, int[][] newMap, int[] s) {
		if(cnt==3) {
			setWall(newMap, s); //맵에 벽을 세움
			int temp = spredVirus(newMap); //바이러스 퍼지게 함
			result = Math.max(result, temp); //안전영역 최대값 갱신
			copyMap(map, newMap);//맵 원래대로 돌려줌
			return;
		}
		
		for(int i=start; i<list.size(); i++) {
			s[cnt] = i;
			comb(cnt+1, i+1, newMap, s);
		}
		
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	private static int spredVirus(int[][] newMap) {
		int temp = zeroCnt-3; //초기 안전영역 개수. 바이러스가 퍼질때마다 감소
		//존재하는 바이러스를 모두 큐에 넣음
		Queue<Point> q = new ArrayDeque<>();
		for(int i=0; i<virus.size(); i++) {
			q.add(virus.get(i));
		}
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = cur.x + dr[d];
				int ny = cur.y + dc[d];
				
				if(isRange(nx, ny) && newMap[nx][ny]==0) {
					newMap[nx][ny] = 2;
					q.add(new Point(nx, ny));
					temp--;
				}
			}
		}
		return temp;
	}

	private static boolean isRange(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}

	private static void setWall(int[][] newMap, int[] s) {
		//리스트의 인덱스대로 벽을 세움
		for(int i=0; i<3; i++) {
			Point p = list.get(s[i]); //선택된 순서의 인덱스로 0의 위치 가져옴
			newMap[p.x][p.y] = 1;//벽 세움
		}
	}

	private static void copyMap(int[][] origin, int[][] copy) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = origin[i][j];  
			}
		}
	}

}
