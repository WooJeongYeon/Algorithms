import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Date : 2021.09.29
 * Thinking : DFS, ans보다 같거나 크면 패스, newMap에 현재까지의 최솟값 저장해 visit 체크하도록. 방문했던 칸이라도 경우에 따라서 감
 * result : 안적고 생각해놓고 바로 옮기니까 거의 바로 되네. newMap은 빼면 안될듯. visit도 체크하고 있어서 필수
 * 			난이도 쉬웠음. 원숭이나 벽부수기랑 비슷..?
 * Plus1 : 다른 사람들 시간이 더 짧게 나오는데... 뭐가 다른걸까? 쌤 풀이 봐야지
 * 		방법1) DFS -> 누적거리 저장 -> 짜피 newMap에 다 최소거리 저장하니까 newMap[n-1][n-1]가 답이지!
 * 		방법2) BFS -> 누적거리 저장 -> 큐 빌때까지 하고 목적지가 답 
 * 		방법3) BFS + PQ -> 짧은 누적거리부터 큐에서 꺼냄(Comparable 구현되어 있어야 함)
 * Plus2 : DFS 내가푼거 문제 잘못풀었는데 어케된거지..? sum 비교할때 자기자신 안더해줬는데ㅋㅋㅋㅋㅋㅋ
 */

public class BJ4485_녹색옷입은애가젤다지 {
	static int N, tc, ans;					// 배열 크기, tc번호, 답
	static int[][] map, newMap;				// 맵, 최소합 저장할 맵
	static int[] di = {-1, 1, 0, 0};		// 델타배열
	static int[] dj = {0, 0, -1, 1};
	static final int MAX = 1250;			// 최대가 될 수 있는 값
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true) {				
			N = Integer.parseInt(in.readLine());
			if(N == 0) break;				// 0이 나올 때까지 반복
			
			map = new int[N][N];
			newMap = new int[N][N];
			ans = MAX;						// 답에 최댓값으로 저장
			
			for (int i = 0; i < N; i++) {	// 배열 입력받음
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				Arrays.fill(newMap[i], MAX);	// 현재 위치까지의 최소 합을 저장하기 위해 최댓값으로 저장
			}
			
			dfs(0, 0, map[0][0]);				// map[0][0]부터 dfs 시작
//			print(map);
//			print(newMap);
			
			sb.append("Problem " + (++tc) + ": " + newMap[N - 1][N - 1] + "\n");		
		}
		System.out.println(sb);					// 답 출력
	}
	
	static void dfs(int i, int j, int sum) {	// dfs - 현재 인덱스들, 현재 위치까지의 합
		
		for(int d = 0 ; d < 4 ; d++) {			// 상하좌우 네 칸에 대해
			int ni = i + di[d];
			int nj = j + dj[d];
			int newSum = sum += map[ni][nj];
			// 인덱스 벗어나거나, 현재 합이 최종 답이 될 수 없거나, 현재 위치에 저장된 값이 이미 최솟값이면 패스
			if(isOutOfIndex(ni, nj) || newSum >= newMap[N - 1][N - 1] || newMap[ni][nj] <= newSum) continue;
			newMap[ni][nj] = newSum;				// 현재 위치까지의 최소합을 저장
			dfs(ni, nj, newSum);		// dfs ㄱㄱㄱ
		}
	}
	static boolean isOutOfIndex(int i, int j) {		// 인덱스 벗어나는지 여부를 반환
		return i < 0 || i >= N || j < 0 || j >= N;
	}
//	static void print(int[][] arr) {
//		System.out.println("-----------------------------------------");
//		for(int i = 0 ; i < N ; i++) {
//			for(int j = 0 ; j < N ; j++) {
//				System.out.printf("%5d ", arr[i][j]);
//			}
//			System.out.println();
//		}
//	}
}
