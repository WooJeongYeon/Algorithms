import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 2021.09.30
 * Level : BaekJoon
 * Method : 조합
 */
public class BJ14888_연산자끼워넣기 {
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] numArr, opNumArr, opArr;
	static final int OPNUM = 4;			// 연산자 종류 4가지 - 덧셈, 뺄셈, 곱셈, 나눗셈
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		numArr = new int[N];			// 숫자
		opNumArr = new int[OPNUM];		// 연산자 종류별 각 개수
		opArr = new int[N - 1];			// 놓여진 연산자(N - 1개)
		st = new StringTokenizer(in.readLine(), " ");
		for(int i = 0 ; i < N ; i++) {	// 숫자 입력받음
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine(), " ");
		for(int i = 0 ; i < OPNUM ; i++) {	// 연산자 입력받음
			opNumArr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(opArr, -1);			// 연산자를 모두 -1로 셋팅(아직 연산자가 할당되지 않음)
		
		comb(0, 0, 0);					// 조합 시작
		
		System.out.println(max + "\n" + min);		// 결과 출력
	}
	
	static void comb(int idx, int cnt, int n) {
		if(cnt == opNumArr[n]) {					// 해당 연산자의 주어진 수만큼 연산자 할당 다 되었다면
//			System.out.println(n + " " + opNumArr[n]);
			if(++n == OPNUM) calc();				// 만약 다 할당했다면 계산ㄱㄱ
			else {									// 아니면 다음 연산자 종류에 대해 연삱 할당하러 ㄱㄱ
				comb(0, 0, n);						// 새로운 조합 시작
			}
			return;									// 반환
		}
		
		if(idx == N - 1) return;					// 끝까지 갔는데 아직 다 못뽑았다면 리턴
		if(opArr[idx] == -1) {						// 연산자가 아직 할당되지 않았다면
			opArr[idx] = n;							// 연산자 할당
			comb(idx + 1, cnt + 1, n);				// 다음 ㄱㄱ
			opArr[idx] = -1;						// 연산자 할당 취소
		}
		comb(idx + 1, cnt, n);						// 연산자 할당 안하고 ㄱㄱ
	}
	
	static void calc() {								// 최종 계산
//		System.out.println(Arrays.toString(opArr));
		int res = numArr[0];
		for(int i = 1 ; i < N ; i++) {
			switch(opArr[i - 1]) {						// 연산자 종류에 따른 계산
			case 0 :
				res = res + numArr[i];
				break;
			case 1 :
				res = res - numArr[i];
				break;
			case 2 :
				res = res * numArr[i];
				break;
			case 3 :
				res = res / numArr[i];
				break;
			}
		}
		max = Integer.max(max, res);					// 결과 갱신
		min = Integer.min(min, res);
	}
}
