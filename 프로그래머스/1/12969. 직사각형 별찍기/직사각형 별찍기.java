import java.io.*;
import java.util.*;
class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(tokens.nextToken());
        int y = Integer.parseInt(tokens.nextToken());
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                sb.append("*");
            }
            if(i==y-1) continue;
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}