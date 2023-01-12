import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

//	static class Person implements Comparable<Person>{
//		int age;
//		int idx;
//		String name;
//
//		public Person(int age, int idx, String name) {
//			this.age = age;
//			this.idx = idx;
//			this.name = name;
//		}
//
//		@Override
//		public int compareTo(Person o) {
//			if(this.age == o.age) return this.idx - o.idx; 
//			return this.age - o.age;
//		}
//
//		@Override
//		public String toString() {
//			return age + " " + name;
//		}
//	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 배열에 나이 이름 순서로 저장
		StringBuilder[] builders = new StringBuilder[201];

		StringTokenizer tokens;
		int age;
		String name;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());

			age = Integer.parseInt(tokens.nextToken());
			name = tokens.nextToken();

			if (builders[age] == null) {
				builders[age] = new StringBuilder();
			}

			builders[age].append(age).append(" ").append(name).append("\n");
		}

		for (StringBuilder sb : builders) {
			if (sb != null) {
				sb.deleteCharAt(sb.length() -1);
				System.out.println(sb);
			}
		}

	}

}
