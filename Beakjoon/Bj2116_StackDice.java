package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210824
 */
public class Bj2116_StackDice {
	static int N;									// 주사위의 수
	static int[][] diceArr;							// 주사위 전개도를 저장하는 배열
	static int[] oppositeIdx = {5, 3, 4, 1, 2, 0};	// 주사위에서 서로 반대되는 인덱스를 저장
	static int ans;									// 답
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		diceArr = new int[N][6];
		for(int i = 0 ; i < N ; i++) {				// N개의 주사위 전개도를 저장
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < 6 ; j++) {
				diceArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 첫 번째 주사위의 윗면만 설정해주면 그 이후는 계산만 해주면 됨
		// 첫 번째 주사위의 윗면이 될 수 있는 경우는 1 ~ 6
		for(int i = 0 ; i < 6 ; i++) {				
			int upIdx = i;							// 첫 번째 주사위 윗면 설정
			int sum = 0, n = 0;						// 이 떄의 옆면 최댓값 총 합, 몇 번째 주사위인지
			while(true) {							// 무한 반복
				int max = Integer.MIN_VALUE;
				for(int j = 0 ; j < 6 ; j++) {		// 해당 주사위에 대해
					if(j == upIdx || j == oppositeIdx[upIdx]) continue;		// 윗면, 아랫면이 아닌 칸 중
					max = Integer.max(max, diceArr[n][j]);					// 최댓값을 저장
				}
				sum += max;
				if(++n >= N) break;					// 모든 주사위에 대해 검사했다면 중단
				for(int j = 0 ; j < 6 ; j++) {		// 윗칸 주사위에 대해
					if(diceArr[n][j] == diceArr[n - 1][upIdx]) {			// 아랫칸 주사위의 윗면과 같은 값이라면 -> 윗칸 주사위의 아랫면임
						upIdx = oppositeIdx[j];								// 아랫면의 반대편에 있는 윗면의 인덱스를 저장
						break;
					}
				}
			}
			ans = Integer.max(ans, sum);			// 더 큰 값을 결과로 저장
		}
		System.out.println(ans);
	}
}
