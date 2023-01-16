import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static class Person{
		int w, h;//몸무게, 키
		
		public Person(int w, int h) {
			this.w = w;
			this.h = h;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Person> list = new ArrayList<>();
		
		StringTokenizer tokens;
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(br.readLine());
			list.add(new Person(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		
		for(int i=0; i<N; i++) {
			int rank = 1;
			Person cur = list.get(i);
			
			for(int j=0; j<N; j++) {
				if(i==j) continue; //자기 자신이면 확인 안 함
				
				Person next = list.get(j);
				
				if(cur.w < next.w && cur.h < next.h) { //다음 값이 몸무게와 키가 더 크다면 현재보다 큰 사람 증가
					rank++;
				}
			}
			
			sb.append(rank).append(" ");
		}
		System.out.println(sb);

	}
}