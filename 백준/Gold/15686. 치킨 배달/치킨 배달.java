import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Location {
	int x;
	int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static ArrayList<Location> ckList; // 치킨집 위치 저장
	static ArrayList<Location> homList; // 집 위치 저장
	static int N, M, homeCnt, ckCnt;
	static boolean[] selected;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ckList = new ArrayList<>();
		homList = new ArrayList<>();
		homeCnt = 0; // 집 카운트
		ckCnt = 0; // 치킨집 카운트

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) { // 집 위치 저장
					homList.add(new Location(i, j));
					homeCnt++;
				} else if (num == 2) { // 치킨집 위치 저장
					ckList.add(new Location(i, j));
					ckCnt++;
				}
			}
		} // 배열입력종료

		selected = new boolean[ckCnt];

		result = Integer.MAX_VALUE;
		combi(0, 0);
		System.out.println(result);

	}

	static void combi(int index, int cnt) {
		if (cnt == M) {
			// true인 곳의 치킨집 최소거리 구하기
			int hap = 0;

			for (int i = 0; i < homeCnt; i++) {// 집의 개수만큼 진행
				int minLen = Integer.MAX_VALUE;
				for (int j = 0; j < ckCnt; j++) {// 한 집 당 치킨집마다 거리 구하고 최소값 저장
					if (selected[j]) { // 선택된 치킨집이라면
						// tempLen에 각 치킨집과의 거리를 구한 후
						int tempLen = Math.abs(homList.get(i).x - ckList.get(j).x)
								+ Math.abs(homList.get(i).y - ckList.get(j).y);
						// 그 값이 최소라면 저장한다
						minLen = Math.min(tempLen, minLen);
					}
				}
				hap += minLen; // 각 집마다의 치킨거리를 합에 저장함
			}
			// 도시 치킨거리의 최소값을 저장함
			result = Math.min(hap, result);
			return;
		}

		for (int i = index; i < ckCnt; i++) {
			selected[i] = true;
			combi(i + 1, cnt + 1);
			selected[i] = false;
		}

	}
}
