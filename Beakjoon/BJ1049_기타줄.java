import java.util.Scanner;
/*
 * Date : 210830
 */
public class BJ1049_기타줄 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();					// 사야할 기타줄
		int M = in.nextInt();					// 브랜드 개수
		int minP = Integer.MAX_VALUE;			// 패키지 최소금액(기타줄 6개묶음)
		int min = Integer.MAX_VALUE;			// 기타줄 최소금액
		int result = 0;							// 최소 금액
		for(int i = 0 ; i < M ; i++) {			// M번 반복해 각 최소금액 저장 
			int n = in.nextInt();
			if(n < minP) minP = n;
			n = in.nextInt();
			if(n < min) min = n;
		}
		// 무조건 패키지 값이 기타줄 6개 산 것보다 싸다고 생각해서 틀림
		if(minP >= min * 6) {					// 기타줄 6개 산 값이 패키지 산 값보다 작거나 같다면
			result = min * N;					// 기타줄 전부 각각 삼
		}
		else {									// 6개 살때는 패키지 사는게 이득이라면
			result = (N / 6) * minP;			// 6개는 전부 패키지로 사고
			N %= 6;								
			result += Integer.min(N * min, minP);	// 나머지는 패키지, 기타줄 각각 사는 것 중 더 싼 걸로 삼
		}
		System.out.println(result);
	}
}