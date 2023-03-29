
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, first, last;
    static int[] prev = new int[1_000_001]; //각 역의 이전 역을 저장할 배열
    static int[] next = new int[1_000_001]; //각 역의 다음 역을 저장할 배열

    static void addStation(int before, int after){
        //before 다음 역 -> after, after 전 역 -> before로 연결해주는 메서드
//        next[before] = after; //before -> after
//        prev[after] = before; //after -> before
        
        prev[after] = before; //현재의 이전을 before를 가리키도록
        next[after] = next[before]; //before가 가리키던 다음번호를 after가 가리키도록
        prev[next[after]] = after; //after가 가리키는 다음 번호가 after를 이전 번호로 가리키게
        next[before] = after;
    }
    
    static void removeStation(int num) {
    	//num을 지우면서 앞뒤로 있는 역을 연결해줌
    	next[prev[num]] = next[num]; //num의 이전 역의 다음 역을 num의 다음 역으로 연결 
    	prev[next[num]] = prev[num]; //num의 다음 역의 이전 역을 num의 이전 역으로 연결
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); //역의 개수
        M = Integer.parseInt(st.nextToken()); //공사 횟수

        st = new StringTokenizer(br.readLine());
        first = Integer.parseInt(st.nextToken());
        prev[first] = first; //0 출력 방지
        next[first] = first;
        int index = first; //첫번째 역은 멤버 변수로 저장해두고 인덱스로 설정

        for(int i=1; i<N; i++){
            int now = Integer.parseInt(st.nextToken());
            //첫번째 역을 제외한 나머지 역
            addStation(index, now);
            index = now; //인덱스를 현재 값으로 갱신
        }
//        last = index;
//        addStation(last, first); //마지막 역과 첫번째 역 연결

        int x,y;

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()){

                case "BN": //다음 역 설립
                    x = Integer.parseInt(st.nextToken());
                    y= Integer.parseInt(st.nextToken());
                    sb.append(next[x]).append("\n"); //x의 다음 역 출력
//                    if(x == last) { //마지막 역에 삽입했다면 y와 첫번째 역을 연결해준다
//                        addStation(y, first);
//                        last = y;
//                    }else { //아니라면 먼저 x의 다음 역과 y를 연결
//                        addStation(y, next[x]);
//                    }
                    addStation(x, y); //x와 y를 연결
                    break;

                case "BP": //이전 역 설립
                    x = Integer.parseInt(st.nextToken());
                    y= Integer.parseInt(st.nextToken());
                    sb.append(prev[x]).append("\n"); //x의 이전 역 출력
//                    if(x == first) { //첫번째 역에 삽입했다면 y와 마지막 역을 연결해준다
//                        addStation(last, y);
//                        first = y;
//                    }else { //아니라면 먼저 x의 이전 역과 y를 연결
//                        addStation(prev[x], y);
//                    }
                    addStation(prev[x], y); //x의 이전역과 y를 연결
                    break;

                case "CN": //다음 역 폐쇄
                    x = Integer.parseInt(st.nextToken());
                    sb.append(next[x]).append("\n"); //x의 다음 역 출력
//                    addStation(x, next[next[x]]); //x와 x의 다음다음역을 연결
                    removeStation(next[x]);
                    break;

                case "CP": //이전 역 폐쇄
                    x = Integer.parseInt(st.nextToken());
                    sb.append(prev[x]).append("\n"); //x의 이전 역 출력
//                    addStation(prev[prev[x]], x); //x와 x의 이전이전역을 연결
                    removeStation(prev[x]);
                    break;
            }//end switch

        }//end for

        System.out.print(sb);

    }//end main

}//end body