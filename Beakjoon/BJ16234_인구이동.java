/* 
 * error1 : while 안에서 모든거 검사할 때 상하좌우 갈 수 있는지 확인하고 들어가야 함
 * 안그러면 groupNum이 올라가서 while문이 안끝남(일단 연합이 가능한지를 알아야 하니까...)
 * error2 : 연합의 맨 처음 시작값들을 방문표시 안해서 인구가 두번 더해져버림
*/ 
/*
 * 과정
 * 1. 연합이 없을 때까지 반복
 * 	2. 모든 칸에 대해 반복
 *   3. 해당 칸에서 상하좌우로 연합 생성이 가능한지
 *    3.1. 연합에 속한 나라의 개수, 연합의 총 인구 구함
 *    3.2. 새로운 인구수를 구해 연합에 속한 나라에 셋팅해줌	
 */
/*
 * Date : 210912
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16234_인구이동 {
	static int N, L, R;
	static int[][] map, simulMap;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int countryNum, sum, newP;
	static int day;

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());					// 땅의 크기
		L = Integer.parseInt(st.nextToken());					// 인구 차이가 L이상
		R = Integer.parseInt(st.nextToken());					// R이하이면 하루동안 연합
		map = new int[N][N];									// 땅
		for(int i = 0 ; i < N ; i++) {							// map의 상태를 입력받음(각 나라 인구)
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {											// 무한반복
			// print(map);
			simulMap = makeSimulMap();							// 시뮬돌릴 맵 생성
			int groupNum = 0;									// 연합의 개수
			for(int i = 0 ; i < N ; i++) {						// 모든 땅(나라)에 대해 시작
				for(int j = 0 ; j < N ; j++) {
					if(simulMap[i][j] == 0) continue;			// 이미 연합이 있으면 패스
					boolean isGo = false;						// 갈 수 있는지
					for(int d = 0 ; d < 4 ; d++) {				// 현재 위치에서 상하좌우를 검사해 갈 수 있다면
						int ni = i + di[d];
						int nj = j + dj[d];
						if(ni >= 0 && ni < N && nj >= 0 && nj < N && simulMap[ni][nj] != 0 
								&& Math.abs(map[i][j] - map[ni][nj]) >= L && Math.abs(map[i][j] - map[ni][nj]) <= R) {
							isGo = true;						// 갈 수 있음을 표시
							break;
						}
					}
					if(!isGo) continue;							// 갈 수 없다면 패스
					
					groupNum++;									// 연합 개수 증가
					countryNum = 1;								// 나라개수 1개(시작 나라 포함)
					sum = map[i][j];							// 연합 인구수 셋팅(시작 나라 포함)
					simulMap[i][j] = -1;						// 현재 위치는 연합 표시
					dfs(i, j);									// 연합에 속한 나라들 구하기
					
					newP = sum / countryNum;					// 새로운 인구 구함
					map[i][j] = newP;							// 시작 위치도 인구수 갱신
					setPDfs(i, j);								// 연합에 속한 나라들에 대해 인구수 갱신
				}
			}
			if(groupNum == 0) break;						// 더이상 연합을 만들 수 없다면 while문 중단
			day++;											// 일 수 증가
		}
		System.out.println(day);
	}
	
	static void dfs(int i, int j) {							// 같은 연합인지를 표시하려는 dfs 함수
		for(int d = 0 ; d < 4 ; d++) {						
			int ni = i + di[d];
			int nj = j + dj[d];
			// map 안에 있고, 다른 연합에 속해있지 않고, 인구 차이가 L이상 R이하라면 연합임!
			if(ni >= 0 && ni < N && nj >= 0 && nj < N && simulMap[ni][nj] > 0 
					&& Math.abs(map[i][j] - map[ni][nj]) >= L && Math.abs(map[i][j] - map[ni][nj]) <= R) {
				countryNum++;								// 연합에 속한 나라개수 증가
				sum += map[ni][nj];							// 연합 인구수에 인구 증가
				simulMap[ni][nj] = -1;						// 같은 연합임을 표시(-1)
				dfs(ni, nj);								// dfsㄱㄱㄱ
			}
		}
	}
	
	static void setPDfs(int i, int j) {						// 변경된 인구로 셋팅하는 dfs 함수(연합인 친구들을 다 변경시킴)
		for(int d = 0 ; d < 4 ; d++) {						
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni >= 0 && ni < N && nj >= 0 && nj < N && simulMap[ni][nj] == -1) {	// map 안에 있고, 연합이라면(-1로 표시함)
				simulMap[ni][nj] = 0;												// 방문했음을 표시(0으로)
				map[ni][nj] = newP;													// 새로운 인구로 셋팅
				setPDfs(ni, nj);													// dfs 진행
			}
		}
	}
	
	static int[][] makeSimulMap() {							// 새로운 시뮬 돌릴 맵 생성
		int[][] simulMap = new int[N][];
		for(int i = 0 ; i < N ; i++) {
			simulMap[i] = map[i].clone();
		}
		return simulMap;
	}
	
	static void print(int[][] tMap) {						// 출력 확인
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(tMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
