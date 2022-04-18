package programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PRO76502_괄호회전하기 {
    public int solution(String s) {
        int answer = 0;
        int N = s.length();
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        for(int i = 0 ; i < N ; i++) {
            boolean isPossible = true;
            for(int j = 0 ; j < N ; j++) {
                char c = s.charAt((i + j) % N);
                if(map.keySet().contains(c)) {
                    stack.push(c);
                }
                else {
                    if(stack.isEmpty() || map.get(stack.peek()) != c) {
                        isPossible = false;
                        break;
                    }
                    stack.pop();
                }

            }
            if(stack.isEmpty() && isPossible) answer++;
            stack.clear();
        }
        return answer;
    }
}
