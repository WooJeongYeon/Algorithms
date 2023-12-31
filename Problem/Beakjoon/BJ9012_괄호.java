import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 2021.12.13
 * Level : BaekJoon Sliver 4
 * Difficulty : 쉬움
 * URL : https://www.acmicpc.net/problem/9012
 * Method : 반복문
 * Result : 스택으로 풀어도 됨 
 */
public class BJ9012_괄호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(in.readLine());
		for(int tc = 0 ; tc < TC ; tc++) {
			int cnt = 0;
			String s = in.readLine();
			for(int i = 0 ; i < s.length() ; i++) {
				if(s.charAt(i) == '(') cnt++;
				else if(s.charAt(i) == ')') cnt--;
				if(cnt < 0) break;
			} 
			if(cnt == 0) sb.append("YES\n");
			else sb.append("NO\n");
		}
		System.out.println(sb);
		
	}
}
