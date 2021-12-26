import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/*
 * Date : 2021.12.26
 * Level : BaekJoon Silver 3
 * Time : 20m
 * URL : https://www.acmicpc.net/problem/1874
 * Method : 스택
 * Error1 : StringBuilder에 \n 들어갔어서 출력오류남
 * Result : 문제를 잘 읽자 
 */
public class BJ1874_스택수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Stack<Integer> stack = new Stack<>();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		int num = 1;
		for(int i = 0 ; i < N ; i++) {
			int now = Integer.parseInt(in.readLine());
			if(num <= now) {
				while(num <= now) {
					sb.append("+\n");
					stack.push(num++);
				}
			}
			if(num >= now) {
				if(stack.peek() != now) {
					sb = new StringBuilder();
					sb.append("NO");
					break;
				} else {
					stack.pop();
					sb.append("-\n");
				}
			}
		}
		
		System.out.println(sb);
	}
}
