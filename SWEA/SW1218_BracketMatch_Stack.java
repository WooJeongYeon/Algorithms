package day0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
/*
 * Date : 210805
 */
public class SW1218_BracketMatch_Stack {
	static final int TC = 10;
	static char[] left = {'(', '[', '{', '<'};
	static char[] right = {')', ']', '}', '>'};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for(int tc = 1 ; tc <= TC ; tc++) {
			int result = 1;
			Stack<Character> stack = new Stack<Character>();
			int N = Integer.parseInt(in.readLine());
			char[] s = in.readLine().toCharArray();
			for(int i = 0 ; i < N ; i++) {
				char c = s[i];
				if(c == left[0] || c == left[1] || c == left[2] || c == left[3]) {
					stack.push(c);
				}
				else {
					if(stack.isEmpty()) {
						result = 0;
						break;
					}
					char now = stack.pop();
					int index = -1;
					for(int j = 0 ; j < left.length ; j++) {
						if(left[j] == now) index = j;
					}
					if(c != right[index]) {
						result = 0;
						break;
					}
				}
			}
			if(!stack.isEmpty()) result = 0;
			System.out.println("#" + tc + " " + result);
		}
	}
}
