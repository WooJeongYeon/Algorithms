import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/*
 * Date : 2021.12.27
 * Level : BaekJoon Silver 3
 * Time : 15m
 * URL : https://www.acmicpc.net/problem/17413
 * Method : 스택
 * Error1 : 여러가지...
 * Result : 생각 안하고 바로 풀어서 계속 에러났다. 스택써서 그런지 시간, 메모리 미쳤다..
 * Plus1 : 스택 안쓰고 StringBuilder와 boolean 변수 써서 할 수도 있음
 * Plus2 : Pattern, Matcher 클래스..?
 */
public class BJ17413_단어뒤집기2 {
	public static void main(String[] args) throws IOException {
		String s = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		String result = "";
		Stack<Character> stack = new Stack<>();
		for(int i = 0 ; i < s.length() ; i++) {
			char c = s.charAt(i);
			if(!stack.isEmpty() && stack.peek() == '<') {
				if(c == '>') stack.pop();
			}
			else if(c == ' ' || c == '<') {
				while(!stack.isEmpty()) {
					result += stack.pop();
				}
				if(c == '<') {
					stack.add(c);
				}
			}
			else {
				stack.add(c);
				continue;
			}
			result += c;
		}
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		
		System.out.println(result);
	}
}
