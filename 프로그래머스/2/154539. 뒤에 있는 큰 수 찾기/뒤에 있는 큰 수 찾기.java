import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[numbers.length];
        
        //뒤의 수 부터 스택에 넣음. 맨 뒷자리 수는 큰수가 없으니 -1
        for(int i=numbers.length-1; i>=0; i--){
            while(!stack.isEmpty()){ //앞의 작은수 찾을때까지 비교
                if(stack.peek() > numbers[i]){
                    //스택에 있는 것이 큰수인 경우
                    answer[i] = stack.peek();
                    break;
                }else{
                    //스택에 있는 수가 큰수가 아닌 경우
                    stack.pop();
                }
            }
            if(stack.isEmpty()) answer[i] = -1; //맨 뒷자리 수
            stack.push(numbers[i]);
        }
        return answer;
    }
}