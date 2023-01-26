import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str;
        Stack<Character> stack;

        while (true) {
            str = br.readLine();

            if (str.length() == 1 && str.charAt(0) == '.') {
                break;
            }

            stack = new Stack<>();

            boolean flag = true;
            char now, prev;

            for (int i = 0; i < str.length(); i++) {
                now = str.charAt(i);
                if (now == '(' || now == '[') { //왼쪽 괄호일 때 넣음
                    stack.push(now);
                } else if (now == ')' || now == ']') { //오른쪽 괄호일때 스택에서 꺼내서 비교

                    if(stack.isEmpty()){ //아무것도 없는데 오른쪽 괄호가 들어오면 불균형
                        flag = false;
                        break;
                    }

                    prev = stack.pop();
                    
                    if (!((prev == '(' && now == ')') || (prev == '[' && now == ']'))) {
                        //짝이 안 맞을 때
                        flag = false;
                        break;
                    }
                }
            }
            //한줄 다 돌았는데 스택이 비어있지 않으면 그것도 불균형
            if(!stack.isEmpty()){
                flag = false;
            }

            sb.append(flag ? "yes" : "no").append("\n");

        }//end while
        System.out.println(sb);
    }

}
