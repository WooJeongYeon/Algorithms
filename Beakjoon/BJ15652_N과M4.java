import java.util.Scanner;
/*
 * Date : 2021.11.14
 * Level : BaekJoon Sliver 3
 * Difficulty : 하인데...
 * URL : https://www.acmicpc.net/status?from_problem=1&problem_id=15652
 * Method : 중복조합 
 * Error1 : boolean배열로 체크했다가 중복인 경우에는 값이 덜 출력됨 
 * Error2 : comb를 i를 넘겨야지 start를 넘기고있네ㅠㅠㅠㅠ그럼 중복순열이라고
 * Result : 순조부 바로 구현이 안된다. 연습 좀 많이하자ㅠㅠㅠ
 * Plus1 : 
 */
public class BJ15652_N과M4 {
	static StringBuilder sb;
	static int N, M;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		sb = new StringBuilder();
		
		comb(0, 0, "");
		
		System.out.println(sb);
	}
	
	static void comb(int start, int cnt, String result) {
		if(cnt == M) {
			sb.append(result + "\n");
			return;
		}
		for(int i = start ; i < N ; i++) {
			comb(i, cnt + 1, result + (i + 1) + " ");
		}
	}
}
