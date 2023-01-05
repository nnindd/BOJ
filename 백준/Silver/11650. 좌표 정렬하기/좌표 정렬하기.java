import java.util.*;
import java.io.*;

public class Main{
    static class Pos implements Comparable<Pos>{
        int x, y;
        
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Pos o){
            if(this.x==o.x){
                return this.y - o.y;
            }
            return this.x - o.x;
        }
        
        @Override
        public String toString(){
            return x+" "+y+"\n";
        }
    }
    
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        
        for(int i=0; i<N; i++){
            tokens = new StringTokenizer(br.readLine());
            pq.offer(new Pos(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
        }
        
        for(int i=0; i<N; i++){
            Pos cur = pq.poll();
            sb.append(cur.toString());
        }
        System.out.println(sb);
	}
}
