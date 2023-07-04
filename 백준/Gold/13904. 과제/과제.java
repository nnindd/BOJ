import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Pos implements Comparable<Pos> {
        int d, w;

        public Pos(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Pos o) { //날짜 내림차순, 점수 내림차순
            if (this.d == o.d) return o.w - this.w;
            return o.d - this.d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());

        LinkedList<Pos> list = new LinkedList<>();

        int d, w, max = 0;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            d = Integer.parseInt(tokens.nextToken());
            w = Integer.parseInt(tokens.nextToken());

            list.add(new Pos(d, w));
            max = Math.max(max, d); //가장 큰 일자 갱신
        }

        int sum = 0;
        while(max > 0){
            Pos res = new Pos(0, 0); //최대 일자를 찾기 위해 설정

            for(Pos cur : list){
                if(cur.d >= max && cur.w > res.w){ //마감일자가 현재 마감할 수 있고 점수가 가장 큰 것 저장
                    res = cur;
                }
            }
            sum += res.w; //점수 갱신
            list.remove(res); //리스트에서 지워줌
            max--; //다음 날짜로
        }
        System.out.println(sum);

    }
}