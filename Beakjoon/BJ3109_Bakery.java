package day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210819
 */
public class BJ3109_Bakery {
	static int R, C;					// 행길이, 열길이
	static char[][] map;				// 맵
	static int[] di = {-1, 0, 1};		// 델타배열 - 오른쪽 위, 오른쪽, 오른쪽 아래
	static int[] dj = {1, 1, 1};		// 가장 최적의 파이프라인을 찾는 것은 위쪽부터 아래로 찾는 것이므로 순서가 위 옆 아래임
	static int result;					// 파이프라인 개수
	static boolean isFind;				// 해당 행의 빵집 가스관의 파이프라인을 완성했는지 
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		for(int i = 0 ; i < R ; i++) {		// 맵을 입력받아 저장
			map[i] = in.readLine().toCharArray();
		}
		for(int i = 0 ; i < R ; i++) {		// 각 행(가스관)에 대해 파이프라인 찾기 시작
			isFind = false;					// 아직 못찾았다고 저장해줌
			buildPipe(i, 0);				// 0열부터 시작이므로 0을 인자로 줌
		}
		
		System.out.println(result);			// 결과출력
	}
	static void buildPipe(int i, int j) {	// 파이프라인을 만들자!
		if(j == C - 1) {					// 원웅이의 빵집에 도착했다면
			result++;						// 파이프라인 개수를 증가시키고
			isFind = true;					// 찾았으므로 true를 저장해줌
			return;							
		}
		for(int d = 0 ; d < 3 ; d++) {		// 오위, 오, 오아래에 대해 찾기
			int ni = i + di[d];				// 다음 위치를 저장함
			int nj = j + dj[d];				//
			// map을 벗어나지 않고, 해당 위치가 지나갈 수 있고, 아직 파이프라인을 찾지 못했다면
			if(ni >= 0 && ni < R && nj >= 0 && nj < C && map[ni][nj] == '.' && !isFind) {
				map[ni][nj] = 'O';			// 이 경로로 가는 경우, 찾은 경우는 지나가지 못하고, 못찾은 경우는 지나가도 결과가 같으므로 이후의 행의 파이프라인들이 지나가지 못하도록 O으로 표시
				buildPipe(ni, nj);			// 다음 위치로 가서 파이프라인을 만듬
			}
		}
	}
}
