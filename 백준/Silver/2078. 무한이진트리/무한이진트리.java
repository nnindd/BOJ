import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int left=0;
		int right=0;
		
		//(1, 1)에서 시작
		//왼쪽이 크면 왼쪽탐색, 오른쪽이 크면 오른쪽 탐색
		while(true) {
			if(a==1 && b==1) break; //루트까지 탐색 완료
			if(a>b) { //왼쪽 자식
				a-=b;
				left++;
			}else if(a<b) { //오른쪽 자식
				b-=a;
				right++;
			}
		}
		System.out.printf("%d %d\n", left, right);
	}
}
