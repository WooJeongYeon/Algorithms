import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.12.10
 * Level : BaekJoon Silver 4
 * URL : https://www.acmicpc.net/problem/10828
 * Method : 스택 구현(배열)
 * Result : 스택 구현하기 은근 헷갈리네. top을 size로 둘지, 마지막으로 저장된 애 위치로 둘지 고민함
 */
public class BJ10828_스택 {
	static int N;
	static String[] s;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(in.readLine());
		Stack stack = new Stack();
		for(int n = 0 ; n < N ; n++) {
			s = in.readLine().split(" ");
			switch(s[0]) {
				case "push" :
					stack.push(Integer.parseInt(s[1]));
					break;
				case "top" :
					sb.append(stack.top() + "\n");
					break;
				case "pop" :
					sb.append(stack.pop() + "\n");
					break;
				case "size" :
					sb.append(stack.size() + "\n");
					break;
				case "empty" :
					sb.append(stack.empty() + "\n");
					break;
			}
			
		}
		System.out.println(sb);
	}
	static class Stack {
		int top;
		int[] list = new int[10001];
		void push(int n) {
			list[top++] = n;
		}
		int pop() {
			if(size() > 0)
				return list[--top];
			else return -1;
		}
		int size() {
			return top;
		}
		int empty() {
			if(size() == 0) return 1;
			else return 0;
		}
		int top() {
			if(size() > 0)
				return list[top - 1];
			else return -1;
		}
	}
}
