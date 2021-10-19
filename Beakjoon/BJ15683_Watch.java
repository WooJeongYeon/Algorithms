package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * Date : 210820
 */
public class BJ15683_Watch {
	static int N, M;					// 행 열 크기
	static int[][] map;					// map 저장
	static ArrayList<CCTV> cctvs;		// cctv를 모두 저장해줌
	static boolean[][] cctvD;			// cctv 방향이 존재하는지 저장 - [cctv 번호(인덱스)][각 방향(0 ~ 4)]
	static int min;						// 안전거리 최솟값
	static int[] di = {-1, 0, 1, 0};	// 델타배열 - 위, 오, 아래, 왼
	static int[] dj = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctvs = new ArrayList<CCTV>();
		for(int i = 0 ; i < N ; i++) {	// 모든 map을 저장
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5)	// 만약 CCTV라면
					cctvs.add(new CCTV(i, j, map[i][j]));	// 위치와 종류를 저장
			}
		}
		min = Integer.MAX_VALUE;	
		cctvD = new boolean[cctvs.size()][di.length];		// CCTV가 가리키는 방향을 저장할 이차원 배열 
		
		chooseDis(0);										// CCTV 0번부터 방향 설정
		
		System.out.println(min);							// 결과 출력
	}
	
	// 재귀 사용(재귀의 깊이 = CCTV의 개수)
	// 매 CCTV마다 방향을 결정해줌
	// 다음 방향을 결정하기 위해 다시 되돌아오면 false로 저장해줌
	static void chooseDis(int cnt) {						// cnt : 현재 CCTV 번호
		if(cnt == cctvs.size()) {							// 모든 CCTV의 방향을 설정했다면
			int[][] simulMap = copyMap();					// 시뮬레이션 하기 위해 맵을 복사
			simul(simulMap);								// 시뮬 돌림
			int area = safeArea(simulMap);					// 시뮬 결과 맵에 대한 안전거리 수 저장
			min = Math.min(min, area);						// 최솟값 저장
			return;	
		}
		
		int kind = cctvs.get(cnt).kind;						// 현재 CCTV의 종류 저장
		// i : 경우(수만큼 반복)
		// j : 설정할 방향(개수만큼 반복)
		if(kind == 1) {										// CCTV 1번일 때(→)
			for(int i = 0 ; i < 4 ; i++) {					// 4개의 경우에 대해 한번쌕 선택
				cctvD[cnt][i] = true;			
				chooseDis(cnt + 1);
				cctvD[cnt][i] = false;
			}
		} else if(kind == 2) {								// CCTV 2번일 때(← →)
			for(int i = 0 ; i < 2 ; i++) {					// 2개의 경우에 대해 한번씩 선택
				cctvD[cnt][i] = true;
				cctvD[cnt][i + 2] = true;
				chooseDis(cnt + 1);
				cctvD[cnt][i] = false;
				cctvD[cnt][i + 2] = false;
			}
		} else if(kind == 3) {								// CCTV 3번일 때(←↑)
			for(int i = 0 ; i < 4 ; i++) {					// 4가지 경우에 대해 한번씩 선택
				cctvD[cnt][i] = true;
				cctvD[cnt][(i + 1) % 4] = true;				// 인덱스 넘어갈 수 있으므로 나머지해서 저장
				chooseDis(cnt + 1);
				cctvD[cnt][i] = false;
				cctvD[cnt][(i + 1) % 4] = false;
			}
		} else if(kind == 4) {								// CCTV 4번일 때(↓←↑ )
			for(int i = 0 ; i < 4 ; i++) {					// 4가지 경우에 대해 한번씩 선택
				for(int j = 0 ; j < 3 ; j++) {
					cctvD[cnt][(i + j) % 4] = true;
				}
				chooseDis(cnt + 1);
				for(int j = 0 ; j < 4 ; j++) {
					cctvD[cnt][(i + j) % 4] = false;
				}
			}
		} else {											// CCTV 5번일 때 모든방향(↓←↑→)
			for(int j = 0 ; j < 4 ; j++) {					// 1가지 경우에 대해 한번 선택
				cctvD[cnt][j] = true;
			}
			chooseDis(cnt + 1);
			for(int j = 0 ; j < 4 ; j++) {
				cctvD[cnt][j] = false;
			}
		}
	}
	
	static void simul(int[][] simulMap) {					// 시뮬 맵으로 시뮬 돌림
		for(int n = 0 ; n < cctvs.size() ; n++) {			// 모든 CCTV에 대해 감시 시작!
			for(int d = 0 ; d < 4 ; d++) {					// 4가지 방향에 대해 검사
				if(!cctvD[n][d]) continue;					// 해당 방향을 검사할 수 없다면 다음 방향으로 continue
				int i = cctvs.get(n).i;						// 감시를 시작할 CCTV의 인덱스 저장
				int j = cctvs.get(n).j;	
				while(true) {								// 인덱스를 벗어나거나 벽이 있을때까지 반복
					int ni = i + di[d];						// 해당 방향으로 한칸 감
					int nj = j + dj[d];
					if(ni < 0 || ni >= N || nj < 0 || nj >= M || simulMap[ni][nj] == 6) break;	
					else if(simulMap[ni][nj] == 0) simulMap[ni][nj] = 9;					// 감시한 위치는 9로 저장
					i = ni; j = nj;							// 위치 갱신
				}
			}
		}
		//print(simulMap);
	}
	
	static int safeArea(int[][] simulMap) {					// map에서 0의 개수 반환(안전거리)
		int sum = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(simulMap[i][j] == 0)
					sum++;
			}
		}
		return sum;
	}
	
	static int[][] copyMap() {							// 맵으로 새로운 시뮬 맵을 만듬
		int[][] simulMap = new int[N][];
		for(int i = 0 ; i < N ; i++)
			simulMap[i] = map[i].clone();				// 각 행을 clone해서 저장
		return simulMap;
	}
	
	static class CCTV {									// CCTV 클래스
		int i, j, kind;									// 위치와 방향 저장

		public CCTV(int i, int j, int kind) {
			this.i = i;
			this.j = j;
			this.kind = kind;
		}
	}
	static void print(int[][] simulMap) {				// 디버깅하기 위한 시뮬맵 출력 메소드
		System.out.println();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print(simulMap[i][j] + " ");
			}
			System.out.println();
		}
	}
}
