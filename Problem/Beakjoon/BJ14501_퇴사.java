import java.util.Scanner;
/*
 * Date : 2021.10.30
 * Level : BaekJoon Sliver 3
 * Difficulty : 쉬움
 * URL : https://www.acmicpc.net/problem/14501
 * Thinking : 매번 상담을 포함시킬지 안포함시킬지를 결정 -> subset
 * 			- least가 0 초과인 경우(아직 상담중) 이번 상담은 포함 못시킴
 * Method : 부분집합
 * Error1 : 마지막 상담을 어떻게 포함시킬지를 고민하다가 그냥 least가 0일 때 금액 추가하도록 함 
 * Result : 재귀로 쓸건데 매개변수들을 생각하면서 추가해감
 * Plus1 : DP로 가능할듯
 * Plus2 : 해당 idx의 상담을 포함시킨 경우, 상담 일수만큼 idx를 넘기도록 할 수 있네.(매 idx마다 검사는게 아니라) -> recursion
 */
public class BJ14501_퇴사 {
	static int N, ans;
	static int[] term;
	static int[] price;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		term = new int[N];
		price = new int[N];
		for(int i = 0 ; i < N ; i++) {
			term[i] = in.nextInt();
			price[i] = in.nextInt();
		}
//		subset(0, 0, 0, 0);
		recursion(0, 0);
		System.out.println(ans);
	}
	
	static void subset(int idx, int lastPrice, int sum, int least) {
		if(least == 0) {
			sum += lastPrice;
			lastPrice = 0;
		}
		if(idx >= N) {
			ans = Math.max(ans, sum);
			return;
		}
		if(least <= 0) {
			subset(idx + 1, price[idx], sum, term[idx] - 1);
		}
		subset(idx + 1, lastPrice, sum, least - 1);
	}
	
	static void recursion(int idx, int sum) {
		if(idx >= N) {
			ans = Math.max(ans,  sum);
			return;
		}
		if(idx + term[idx] <= N )		// 포함시킬 수 있으면 포함
			recursion(idx + term[idx], sum + price[idx]);
		recursion(idx + 1, sum);		// 포함 안시키는 경우
	}
}
