import java.util.Stack;
/*
 * Date : 2021.11.19
 * Level : Programmers Level 1
 * URL : https://programmers.co.kr/learn/courses/30/lessons/64061
 */
public class PRO64061_크레인인형뽑기게임 {
	public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for(int idx = 0 ; idx < moves.length ; idx++) {
            int jIdx = moves[idx] - 1;
            int doll = -1;
            for(int i = 0 ; i < board.length ; i++) {
                if(board[i][jIdx] != 0) {
                    doll = board[i][jIdx];
                    board[i][jIdx] = 0;
                    break;
                }
            }
            if(doll == -1) continue;
            if(stack.isEmpty()) {
                stack.push(doll);
            } else{
                int lastDoll = stack.peek();
                if(lastDoll == doll) {
                    stack.pop();
                    answer += 2;
                } else {
                    stack.push(doll);
                }
            }
        }
        return answer;
    }
}
