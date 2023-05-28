import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, res;
	static int[][] map;
	static int[][] copymap;
	static Point[] pos; //회전 정보
//	static Point[] temp; //회전 순열
	
	static int[] dr = {1, 0, -1, 0}; //하우상좌 순서로 이동함
	static int[] dc = {0, 1, 0, -1};
	//a b c d
	//j     e
	//i h g f => a, j, i순으로 내려갔다가 오른쪽으로 위로 왼쪽으로 이동
	
	static class Point{
		int r, c, s;

		public Point(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws Exception {
		input();
		
		//1) 처음 배열 최소값 구하기
		//2) 배열 돌리는 순서 구하기
		//3) 배열 돌리는 순서대로 배열 돌리고 최소값 구하기
		res = Integer.MAX_VALUE;
		
//		getMinimum(map); //초기 배열의 최소값 구해줌
		
		permutation(0, new boolean[K], new Point[K]); //K만큼 연산의 수행 순서를 정하기 위함
		
		System.out.println(res);
		
	}
	
	private static void permutation(int cnt, boolean[] visited, Point[] select) {
		if(cnt == K) {//순서 전부 고름
			
			copyMap(); //배열 복사함
			
			rotate(select); //배열 돌림
			
			getMinimum(copymap); //최소값 설정함
			
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			select[cnt] = pos[i]; //순서정한대로 넣어줌
			permutation(cnt+1, visited, select);
			visited[i] = false;
		}
	}
	
	private static void rotate(Point[] select) {
		//선택된 순서대로 배열 돌림
		for(int k=0; k<K; k++) { //K만큼 배열 돌림
			
			int r = select[k].r;
			int c = select[k].c;
			int s = select[k].s;
			
			//회전할 내부 박스의 개수는 s
			for(int i=0; i<s; i++) { //내부 박스 회전
				int dir = 0; //dr, dc 인덱스로 사용할 수 0123 사용
				int sx = r-s+i; //시작 행
				int sy = c-s+i; //시작 열
				
				int first = copymap[sx][sy]; //시작 위치 값 손실 방지
				
				while (dir<4) { //하우상좌 순으로 이동
					int nx = sx+dr[dir];
					int ny = sy+dc[dir];
					
					if(nx>=r-s+i && ny>=c-s+i && nx<=r+s-i && ny<=c+s-i) {//범위확인
						copymap[sx][sy] = copymap[nx][ny]; //값 shift
						sx = nx;
						sy = ny; //값 갱신
					}else {
						dir++; //범위 벗어나면 이동
					}
					
				}//end 한 박스 회전
				copymap[r-s+i][c-s+i+1] = first;
				
			}
			
		}//end K rotate
		
	}

	private static void copyMap() { //배열 복사 메서드
		copymap = new int[N][M];
		
		for(int i=1; i<N; i++) {
			for(int j=1; j<M; j++) {
				copymap[i][j] = map[i][j]; 
			}
		}
	}

	private static void getMinimum(int[][] arr) {
		for(int i=1; i<N; i++) {
			int sum = 0;
			for(int j=1; j<M; j++) {
				sum+=arr[i][j];
			}
			res = Math.min(res, sum);
		}
	}

	private static void input() throws Exception { //입력 메서드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken())+1; //배열 행
		M = Integer.parseInt(tokens.nextToken())+1; //배열 열
		K = Integer.parseInt(tokens.nextToken()); //회전 횟수
		
		map = new int[N][M]; //배열 저장
		pos = new Point[K]; //회전 연산 저장 배열
		
		for(int i=1; i<N; i++) { //배열 입력 저장
			tokens = new StringTokenizer(br.readLine());
			for(int j=1; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int k=0; k<K; k++) { //연산 위치 pos 객체 배열에 저장
			tokens = new StringTokenizer(br.readLine());
			pos[k] = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		}
		
		br.close();
	}
}
