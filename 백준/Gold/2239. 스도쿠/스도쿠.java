import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String temp = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}

		sudoku(0, 0); // 처음 위치부터 모두 탐색

	}

	private static void sudoku(int x, int y) {

		if (y == 9) {
			sudoku(x + 1, 0);
			return;
		}

		if (x == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		if (map[x][y] == 0) {
			for (int i = 1; i <= 9; i++) {// 1~9까지 넣을 수 있는 수 탐색
				if (check(x, y, i)) { // 가로, 세로, 3x3 겹치는 값이 없을 때
					map[x][y] = i;
					sudoku(x, y + 1); // 다음 열 검사
				}
			}
			map[x][y] = 0; //맞는 값이 없으면 숫자 잘못 선택함
			return;
		}
		sudoku(x, y+1); //빈칸 아니면 다음 열 진행
	}

	// 가로, 세로, 3x3을 살폈을 때 같은 값이 있으면 false 반환. 없으면 true
	private static boolean check(int x, int y, int num) {
		for (int j = 0; j < 9; j++) {// 가로 탐색
			if (map[x][j] == num)
				return false;
		}

		for (int i = 0; i < 9; i++) {// 세로탐색
			if (map[i][y] == num)
				return false;
		}

		// 3x3탐색
		int rstart = 0;
		int rend = 0;
		int cstart = 0;
		int cend = 0;

		if (x < 3) {
			rstart = 0;
			rend = 3;
		} else if (x < 6) {
			rstart = 3;
			rend = 6;
		} else if (x < 9) {
			rstart = 6;
			rend = 9;
		}

		if (y < 3) {
			cstart = 0;
			cend = 3;
		} else if (y < 6) {
			cstart = 3;
			cend = 6;
		} else if (y < 9) {
			cstart = 6;
			cend = 9;
		}

		for (int i = rstart; i < rend; i++) {
			for (int j = cstart; j < cend; j++) {
				if (map[i][j] == num)
					return false;
			}
		}

		return true;
	}

}
