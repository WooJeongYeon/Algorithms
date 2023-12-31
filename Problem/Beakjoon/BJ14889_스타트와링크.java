import java.util.Scanner;
/*
 * Date : 2021.11.07
 * Level : BaekJoon Silver 3
 * Difficulty : 쉬움(SW4012_Chef와 완전 같은문제)
 * URL : https://www.acmicpc.net/problem/14889
 * Thinking : nCn/2개 선택(부분집합) -> n/2C2 * 2번
 * Method : 조합, 부분집합 
 * Error1 : setA, setB에 인덱스 저장했던건데 powerArr[i][j]해서 더했었음
 * Result : 어떻게 예전에 SW에서 푼 코드랑 완전 똑같지? 변수 이름이랑 메소드랑 계산한거 저장하는지만 다름...ㄷㄷㄷ
 * Plus1 : 123/456, 456/123 같은건데 둘다 선택해서 똑같은거 두번 계산. 이건 어케해야되는지 몰겄다.
 */
public class BJ14889_스타트와링크 {
	static int N, ans;
	static int[][] powerArr;
	static boolean[] isChoose;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		powerArr = new int[N][N];
		isChoose = new boolean[N];
		ans = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				powerArr[i][j] = in.nextInt();
			}
		}
		
		subset(0, 0);
		System.out.println(ans);
	}
	
	static void subset(int idx, int n) {		// NCN/2 선택
		if(n == N / 2) {
			int[] setA = new int[N / 2];
			int[] setB = new int[N / 2];
			int idxA = 0, idxB = 0;
			for(int i = 0 ; i < N ; i++) {
				if(isChoose[i]) setA[idxA++] = i;
				else setB[idxB++] = i;
			}
			
			int sub = Math.abs(getPower(setA) - getPower(setB));
			ans = Math.min(ans, sub);
		
			return;
		}
		if(idx == N) {
			return;
		}
		
		isChoose[idx] = true;
		subset(idx + 1, n + 1);
		isChoose[idx] = false;
		subset(idx + 1, n);
	}
	
	
	static int getPower(int[] set) {			// N/2C2개 선택
		int sum = 0;
		for(int i = 0 ; i < set.length - 1 ; i++) {
			for(int j = i + 1 ; j < set.length ; j++) {
				sum += powerArr[set[i]][set[j]] + powerArr[set[j]][set[i]];
			}
		}
		return sum;
	}
}
