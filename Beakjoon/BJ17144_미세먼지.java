import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.sound.midi.Synthesizer;

/*
 * Date : 210924
 * Method : 미세먼지 확산 -> 공기청정기 작동 무한반복
 * Error1 : 계속 인덱스 벗어남 -> 공기청정기 작동시킬때 인덱스 벗어나는 범위를 줘야함 -> isOutOfIndex 범위 수정
 * Error2 : 위쪽 공기청정기가 '위-오른-아래-왼' / 아래게 '아래-오른-위-왼'인데 순서 바꿔서 했었음 
 * Error3 : 결과 제대로 안나옴1 -> dustSum을 while문 마지막에 구해줘야 함
 * Error4 : 결과 제대로 안나옴2 -> dustSum은 미세먼지 합 구해줘야 함 
 * Result : 배열 돌리는거 짜증남 헷갈림... 이름 짓기도 힘들고. 미세먼지 확산은 금방했는데 배열 돌리는거에서 좀 애먹음
 * 			2시간 걸린듯
 */

public class BJ17144_미세먼지 {
	static int R, C, T;
	static int[][] map, simulMap;
	static int t, ans;
	static int[][] cleanerPos;				// 공기청정기 위치
	static int[] di = {-1, 0, 1, 0};		// 위 - 오른쪽 - 아래 - 왼쪽
	static int[] dj = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		cleanerPos = new int[2][];
		int n = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) cleanerPos[n++] = new int[] {i, j};		// 공기청정기 위치를 저장
			}
		}
		
		while(t < T) {						// 주어진 시간동안 반복
				
			expandDust();					// 미세먼지 확산
			workCleaner(0, 0, 1);			// 위쪽 공기청정기 작동(반시계방향으로 작동 -> 시계방향으로 방향 이동하면서 끌어옴)
			workCleaner(1, 2, -1);			// 밑에 공기청정기 작동(시계방향으로 작동 -> 반시계방향으로 방향 이동하면서 끌어옴)
			
			t++;		
			ans = dustSum();				// 미세먼지 양을 구해
			if(ans == 0) break;				// 더이상 미세먼지가 없으면 중단
		}
		
		System.out.println(ans);
	}
	
	static void expandDust() {				// 미세먼지 확산시키기
		simulMap = new int[R][C];
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				if(map[i][j] == 0 || map[i][j] == -1) continue;			// 미세먼지가 없거나 공기청정기면 다음으로
				int dNum = 0;											// 확산 가능한 방향 수
				int amount = map[i][j] / 5;								// 확산되는 양
				for(int d = 0 ; d < 4 ; d++) {							// 4 방향에 대해
					int ni = i + di[d];
					int nj = j + dj[d];
					// 인덱스 벗어나거나 공기청정기이면 패스
					if(isOutOfIndex(ni, nj, 0, 0, R, C) || (cleanerPos[0][0] == ni && cleanerPos[0][1] == nj)
							 || (cleanerPos[1][0] == ni && cleanerPos[1][1] == nj)) 
						continue;
					dNum++;
					simulMap[ni][nj] += amount;
				}
				simulMap[i][j] += (map[i][j] - dNum * amount);			// 해당 칸에는 남은 미세먼지 저장
			}
		}
		map = simulMap;													// 미세먼지 확산시킨 맵으로 바꿔줌
	}
	
	// 공기청정기 번호, 해당 공기청정기의 시작 방향, 공기청정기 방향(시계인지, 반시계인지)
	static void workCleaner(int cleanerIdx, int startD, int cleannerD) {
		int d = startD;					// 시작 방향 저장
		int lastI = cleanerPos[cleanerIdx][0] + di[d], lastJ = cleanerPos[cleanerIdx][1] + dj[d];	 
		int i = lastI + di[d], j = lastJ + dj[d];
		int si, sj, ei, ej;				// 공기청정기가 도는 범위 지정해줌
		if(cleannerD == 1) {
			si = 0; sj = 0; ei = cleanerPos[cleanerIdx][0] + 1; ej = C;
		}
		else {
			si = cleanerPos[cleanerIdx][0]; sj = 0; ei = R; ej = C;
		}
		
		// 공기청정기 다 돌때까지 먼지 끌어당기기
		while(true) {
			if(i == cleanerPos[cleanerIdx][0] && j == cleanerPos[cleanerIdx][1]) break;	// 다 돌았으면 중단
			
//			System.out.println(i + " " + j);
			map[lastI][lastJ] = map[i][j];			// 먼지 당겨서 저장
		
			if(isOutOfIndex(i + di[d], j + dj[d], si, sj, ei, ej)) {					// 인덱스 벗어나면 방향 바꿔줌
				if(cleannerD == 1) d = (d + 1) % 4;										// 시계방향으로 끌어오는 공기청정기의 경우(위쪽)
				else d = (d - 1) < 0 ? d + 3 : d - 1;									// 반시계방향으로 끌어오는 공기청정기의 경우(아래쪽)
			}
			lastI = i; lastJ = j;														// 인덱스 갱신
			i = i + di[d]; j = j + dj[d];
		}
		map[lastI][lastJ] = 0;			// 마지막 위치에 0 넣어줌
	}
	
	
	static int dustSum() {								// 남아있는 미세먼지 양을 반환
		int sum = 0;
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				if(map[i][j] > 0) sum += map[i][j];
			}
		}
		return sum;
	}
	
	// 주어진 범위 내에 i, j가 있는지를 반환
	static boolean isOutOfIndex(int i, int j, int sI, int sJ, int eI, int eJ) {
		return i < sI || i >= eI || j < sJ || j >= eJ;
	}
	
	static void print() {								// 출력해보는 메소드
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
