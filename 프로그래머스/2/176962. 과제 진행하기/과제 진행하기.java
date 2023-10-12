import java.util.*;
class Solution {
    class Plan implements Comparable<Plan>{
        String name;
        int time;
        int res;
        
        Plan(String name, int time, int res){
            this.time = time;
            this.name = name;
            this.res = res;
        }
        
        @Override
        public int compareTo(Plan o){
            return this.time - o.time;
        }
        
        void changeTime(int next){ //시간 변경
            this.res -= (next - this.time);
            this.time = next;
        }
        
        boolean isEnd(int next){ //다음 시간 안에 끝나는지 확인
            if(this.time + this.res <= next) return true;
            return false;
        }
    }
    
    public String[] solution(String[][] plans) {
        ArrayList<Plan> list = new ArrayList<>(); //플랜 저장
        Stack<Plan> stack = new Stack<>(); //최근 멈춘 과제 저장
        ArrayList<String> result = new ArrayList<>(); //과제 종료 순 저장
       
        StringTokenizer tokens;
        
        for(int i=0; i<plans.length; i++){
            String[] now = plans[i];
            
            tokens = new StringTokenizer(now[1], ":");
            int h = Integer.parseInt(tokens.nextToken());
            int m = Integer.parseInt(tokens.nextToken());
            
            list.add(new Plan(now[0], h*60 + m, Integer.parseInt(now[2])));
        }
        
        Collections.sort(list);
        
        for(int i=0; i<list.size()-1; i++){
            Plan now = list.get(i);
            Plan next = list.get(i+1);
            
            int nowT = now.time + now.res;
            int nextT = next.time;
            
            if(nowT <= nextT){ //다음 시간 안에 끝낼 수 있음
                result.add(now.name); //결과 저장
                
                //남은 시간 동안 할 수 있는 만큼 과제 수행
                int remain = nextT - nowT;
                while(remain > 0 && !stack.isEmpty()){
                    int can = stack.peek().res - remain; //수행할 수 있는 시간
                    stack.peek().res = Math.max(0, can);
                    //수행 가능한 시간이 음수거나 같으면 수행완료
                    if(can<=0){
                        result.add(stack.pop().name);
                        remain = (can * -1);
                    }else{ //양수면 스택으로 돌아감. 할 수 있는 만큼 다 수행함
                        remain = 0;
                    }
                }
                
            }else{ //다음 시간 안에 못 끝내는 경우
                now.res = (nowT - nextT); //할 수 있는 만큼 시간 저장
                stack.push(now);
            }
        }
        //가장 마지막 시간의 과제 추가
        result.add(list.get(list.size()-1).name);
        
        while(!stack.isEmpty()) result.add(stack.pop().name);
        
        return result.toArray(new String[result.size()]);
    }
}