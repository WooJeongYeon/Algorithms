/*
 * Date : 2022.01.21
 * Level : Programmers level 2
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12973
 * Thinking : 계속 제거되는 애들 만날 수 있으니 while안에 while로 계속 없애도록
 * Method : stack
 * Error1 : 인덱스를 잘못 했었나?
 * Plus1 : 그냥 for문으로 인덱스 하나씩 증가해도 됬었음(내부에서 while문 안쓰고)
 */
import java.util.*;

public class PRO12973_짝지어제거하기 {
	class Solution
	{
	    public int solution(String s)
	    {
	        int answer = 0;
	        Stack<Character> stack = new Stack<>();
	        int idx = 0;
	        boolean isEnd = false;
	        while(idx < s.length()) {
	            char c = s.charAt(idx++);
	            while(true) {
	                if(stack.isEmpty() || stack.peek() != c) break;
	                stack.pop();
	                if(idx == s.length()) {
	                    isEnd = true;
	                    break;
	                }
	                c = s.charAt(idx++);
	            }    
	            if(isEnd) break; 
	            stack.add(c);
	        }
	        
	        if(stack.isEmpty()) answer = 1;
	        return answer;
	    }
	}
}
