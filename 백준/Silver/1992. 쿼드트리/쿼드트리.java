import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = temp.charAt(j)-'0';
			}
		}
		
		divide(N, 0, 0); //처음에서부터 시작
		System.out.println(sb);
		
	}//end main
	
	static void divide(int size, int x, int y) {
		//요소를 확인했을 때 다 같으면 출력에 추가
		if(check(size, x, y)) {
			sb.append(map[x][y]);
			return;
		}
		
		//한번 압축 돌때마다 () 사이에서 돌아야 함
		sb.append("(");
		
		//왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순서
		divide(size/2, x, y);
		divide(size/2, x, y+(size/2));
		divide(size/2, x+(size/2), y);
		divide(size/2, x+(size/2), y+(size/2));
		
		sb.append(")");
		
		
	}
	
	static boolean check(int size, int x, int y) {
		//x,y위치부터 size까지 전부 돌았을 때 처음 값과 다음 값이 다르다면 더 쪼개야함
		//같다면 참 반환
		int first = map[x][y];
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(first != map[i][j])
					return false;
			}
		}
		return true;
	}
	
}
