package day0806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210806
 */
public class SW3499_Perfect_Shuffle {
	static int TC;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		// 결과 모아서 출력할 StringBuilder
		TC = Integer.parseInt(in.readLine());		// 테스트 케이스 수를 입력받음
		String[] input = new String[1001];			// String배열을 미리 생성
		for(int tc = 1 ; tc <= TC ; tc++) {
			int N = Integer.parseInt(in.readLine());	// 몇 개의 카드가 들어오는지 입력받음
			StringTokenizer st = new StringTokenizer(in.readLine());	// 카드를 입력한 한 줄을 가져와서 쪼갬
			for(int i = 1 ; i <= (N - 1) / 2 + 1; i++) {	// 퍼펙트 셔플이므로 반으로 쪼개 앞의 반은 홀수 번째 배열에 끼워넣음
				input[i * 2 - 1] = st.nextToken();		
			}
			for(int i = 1 ; i <= N / 2 ; i++) {		// 나머지 반은 짝수 번째에 끼워넣음
				input[i * 2] = st.nextToken();
			}
			sb.append("#" + tc + " ");				// 출력 양식을 sb에 더해
			for(int i = 1 ; i <= N ; i++) {			// 만든 카드 배열을 더한 후
				sb.append(input[i] + " ");
			}
			sb.append("\n");						// 다음 테스트 케이스를 위해 \n 넣음
		}	
		System.out.print(sb);						// 결과 출력

	}
}
