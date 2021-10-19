import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
/*
 * Date : 2021.10.10
 * Level : Promrammers Level 2
 * Thinking) 스택이나 큐로 구분되있어서 애초에 그렇게 생각하고 품
 * 			- 스택에 오름차순으로 주식 가격이 저장되도록 함
 * 			- 스택 가장 윗 값보다 현재 검사하는 가격이 더 낮다면 떨어진것이므로 꺼내서 시간 갱신 -> 이런 값이 여러개일 수 있으므로 무한반복
 * 			- 검사가 다 끝난 후, 스택에 남아있는 가격들은 떨어지지 않은 값들이므로 시간 갱신해줌
 * Method : stack
 * Result : 문제가 너무 가독성이 떨어진다.
 */
public class PRO스택큐_주식가격 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = in.readLine().split(" ");
		int[] prices = new int[arr.length];
		for(int i = 0 ; i < arr.length ; i++) {
			prices[i] = Integer.parseInt(arr[i]);
		}
		System.out.println(Arrays.toString(solution(prices)));
	}
	
	static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];		
		Stack<int[]> stack = new Stack<>();								// 스택에 넣을 값 - 0인덱스(가격) 1인덱스(시간)
		stack.push(new int[] { prices[0], 0 });							// 스택에 첫번째 가격을 추가
		for (int i = 1; i < prices.length; i++) {						// 모든 가격에 대해 진행(인덱스는 시간임)
			while (!stack.isEmpty() && stack.peek()[0] > prices[i]) {	// 스택이 비지 않았고, 가장 윗 값의 가격이 현재 가격보다 더 크다면(가격이 떨어진 것)
				int[] info = stack.pop();								// 스택의 값을 꺼내(가격이 떨어졌으므로)
				answer[info[1]] = i - info[1];							// 시간 구해줌
			}
			stack.push(new int[] { prices[i], i });						// 스택에 현재 가격을 넣어줌
		}
		while (!stack.isEmpty()) {										// 끝난 후 스택에 남아있는 가격들에 대해 시간을 구해줌(가격이 떨어지지 않은 값들)
			int[] info = stack.pop();									
			answer[info[1]] = prices.length - info[1] - 1;
		}
		return answer;
	}
}
