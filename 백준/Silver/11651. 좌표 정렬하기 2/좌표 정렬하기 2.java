import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Pos implements Comparable<Pos> {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.y == o.y) return this.x - o.x;
            return this.y - o.y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokens;

        ArrayList<Pos> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            list.add(new Pos(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
        }

        Collections.sort(list);

        for(Pos p : list){
            sb.append(p.x).append(" ").append(p.y).append("\n");
        }
        System.out.println(sb);
    }

}
