package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210809
 */
public class SW9229_SpotMart {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(in.readLine());		// 테스트 케이스 수를 입력받음
		for(int tc = 1 ; tc <= TC ; tc++) {					// TC만큼 반복
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());		// 과자 봉지 개수
			int limitWeight = Integer.parseInt(st.nextToken());	// 무개 제한
			int maxWeight = -1, sum = 0;		// 최대 무게의 합(없으면 -1 출력하므로), 현재 합
			int[] weights = new int[N];			// 각 과자 봉지의 무게 배열
			st = new StringTokenizer(in.readLine());
			for(int i = 0 ; i < N ; i++) {		// 무게를 배열에 저장
				weights[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = 0 ; i < N - 1 ; i++) {		// 두개씩 더하므로 마지막 전 요소까지 선택
				for(int j = i + 1 ; j < N ; j++) {	// 조합이므로 순서를 생각하지 않게 선택한 요소 다음 요소들만 선택
					sum = weights[i] + weights[j];	// 두 봉지의 무게를 더해
					if(limitWeight >= sum && sum > maxWeight)	// 무게 제한을 넘지 않고, 원래 저장된 최대 무게보다 크다면
						maxWeight = sum;	// 최대 무게를 새로 저장
				}
			}
			System.out.println("#" + tc + " " + maxWeight);	// 최대 무게 출력
		}
	}
}
