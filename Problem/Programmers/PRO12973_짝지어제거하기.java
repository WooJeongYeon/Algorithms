/*
 * Date : 2022.01.21
 * Level : Programmers level 2
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12973
 * Thinking : ��� ���ŵǴ� �ֵ� ���� �� ������ while�ȿ� while�� ��� ���ֵ���
 * Method : stack
 * Error1 : �ε����� �߸� �߾���?
 * Plus1 : �׳� for������ �ε��� �ϳ��� �����ص� �����(���ο��� while�� �Ⱦ���)
 */
import java.util.*;

public class PRO12973_¦���������ϱ� {
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
