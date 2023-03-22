import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, d, k, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken()); // 접시의 수
		d = Integer.parseInt(tokens.nextToken()); // 초밥의 가짓수
		k = Integer.parseInt(tokens.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(tokens.nextToken()); // 쿠폰 번호

		int[] pan = new int[N];
		int[] eat = new int[d + 1]; // 초밥 먹은 것 카운트

		for (int i = 0; i < N; i++) {
			pan[i] = Integer.parseInt(br.readLine()); // 초밥의 종류 1~d
		}

		// 1) i~i+k까지 연속해서 먹으면 할인된 정액 가격
		// 2) 쿠폰(초밥종류1개) 1번 참여하면 쿠폰초밥 하나 무료제공. 현재 없으면 새로 제공
		// -> 초밥 가짓수 최대

		int i = 0, j = k - 1; // 초기 투포인터 위치
		int max = 0, cnt = 0;
		while (i < N) {
			j = j % N; // j가 N넘어가면 바꿔줌

			if (i == 0) { // 첫번째에는 다 넣어줌
				for (int x = i; x <= j; x++) {
					if (eat[pan[x]] == 0) {
						cnt++; // 먹은 초밥의 개수
					}
					eat[pan[x]]++;
				}
			} else { // 그 다음부턴 투포인터 사용
				// 첫번째 초밥 제거
				eat[pan[i-1]]--;
				if(eat[pan[i-1]]==0) cnt--; //여러번 안먹었으면 카운트 감소
				// 다음 초밥 추가
				eat[pan[j]]++;
				if(eat[pan[j]]==1) cnt++; //새로 먹은 초밥이면 카운트 증가
			}

			// c쿠폰의 경우 추가
			if(eat[c]==0) { //c를 안먹었다면 카운트 증가
				cnt++;
				eat[c]++;
			}

			max = Math.max(max, cnt); // 초밥 종류 갱신
			i++;
			j++;

		}

		System.out.println(max);
	}

}
