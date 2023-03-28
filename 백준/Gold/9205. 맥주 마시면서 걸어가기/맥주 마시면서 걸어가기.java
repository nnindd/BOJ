import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			
			int N = Integer.parseInt(br.readLine())+2; //맥주를 파는 편의점의 개수
			int[][] map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer tokens = new StringTokenizer(br.readLine());
				map[i][0] = Integer.parseInt(tokens.nextToken());
				map[i][1] = Integer.parseInt(tokens.nextToken());
			}
			
			boolean[][] flag = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					int dist = Math.abs(map[i][0]-map[j][0]) + Math.abs(map[i][1]-map[j][1]);
					if(dist <= 1000) flag[i][j] = true; //최대거리 넘지 않으면 갈 수 있음 
				}
			}
			
			for(int k=0; k<N; k++) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(flag[i][k]&&flag[k][j]) {
							flag[i][j] = true; 
						}
					}
				}
			}
			
			System.out.println(flag[0][N-1] ? "happy" : "sad");
		}
	}
}
