// 고려사항 - A 재료선택(1, 2, 3), B재료선택(4, 5, 6) / A 재료선택(4, 5, 6), B재료선택(1, 2, 3) 
// 이런 경우는 다른 경우지만 최소인 맛의 차이를 찾기 때문에 중복해서 계산할 필요 없지만 -> 결과가 같으므로
// 이 코드에서는 둘다 계산한다.
package day0818;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210818
 */
public class SW4012_Chef {
	static int[][] map;
	static boolean[] selected;
	static int min;
	static int TC, N;
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream(new File("sample_input.txt")));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {			// TC만큼 반복
			N = Integer.parseInt(in.readLine());	// 요리 수
			map = new int[N][N];					// 시너지 배열 N * N
			selected = new boolean[N];				// 해당 요리가 포함되는지
			min = Integer.MAX_VALUE;				// 최소인 맛의 차이 저장할 변수
			for(int i = 0 ; i < N ; i++) {			// 시너지 저장
				st = new StringTokenizer(in.readLine());
				for(int j = 0 ; j < N ; j++) {		
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			comb(0, 0);								// 조합함수 실행
			
			sb.append("#" + tc + " " + min + "\n");	// 결과에 합침
		}
		System.out.println(sb);
	}
	static void comb(int idx, int cnt) {
		if(cnt == N / 2) {							// 재료를 모두 뽑았다면(N/2개)
			int[] AIdxs = new int[N / 2];			// 뽑은 재료들의 인덱스를 저장
			int[] BIdxs = new int[N / 2];			// 안뽑은 재료들의 인덱스를 저장
			int aIdx = 0, bIdx = 0;					
			for(int i = 0 ; i < N ; i++) {
				if(selected[i]) AIdxs[aIdx++] = i;	
				else BIdxs[bIdx++] = i;
			}
			int sumA = getSynergySum(AIdxs);		// 뽑은 재료들의 시너지 합 저장
			int sumB = getSynergySum(BIdxs);		// 안뽑은 재료들의 시너지 합 저장
			int sub = Math.abs(sumA - sumB);		// 두 음식의 맛의 차이
			min = Math.min(sub, min);				// 최소가 되는 맛의 차이를 저장
			
			return ;								// 끝났으면 리턴
		}
		
		if(idx == N) return;						// 모든 재료을 확인했다면 중단
		selected[idx] = true;						// 해당 재료 포함시킨 경우
		comb(idx + 1, cnt + 1);
		selected[idx] = false;						// 해당 재료 포함시키지 않은 경우
		comb(idx + 1, cnt);
	}
	static int getSynergySum(int[] selectIdx) {		// 선택된 재료들을 두개씩 뽑아 시너지를 더함
		int sum = 0;
		for(int i = 0 ; i < selectIdx.length - 1 ; i++) {	// 순서 없는 두 개의 재료 뽑기
			for(int j = i + 1 ; j < selectIdx.length ; j++) {
				sum += map[selectIdx[i]][selectIdx[j]] + map[selectIdx[j]][selectIdx[i]];
			}
		}
		return sum;									// 시너지 총합 반환
	}
}
