import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.02
 * Level : SWEA 기출
 * Method : 시뮬레이션
 */
public class SW5653_줄기세포배양 {
	static int TC, N, M, K, R, C, ans;
	static int[][] map, simulMap, status;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {	
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());		
			M = Integer.parseInt(st.nextToken());		
			K = Integer.parseInt(st.nextToken());
			R = N + 2 * K;
			C = M + 2 * K;
			map = new int[R][C];
			simulMap = new int[R][C];
			status = new int[R][C];
			for(int i = K ; i < K + N ; i++) {							
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = K ; j < K + M ; j++) {
					simulMap[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) status[i][j] = 1;
				}
			}
			ans = simul();
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	static int simul() {
		int time = 0;
		while(true) {
//			System.out.println("시뮬맵---------");
//			print(simulMap);
//			System.out.println("상태맵---------");
//			print(status);
			if(time == K) return getCellCnt();
			time++;
			spread();
			change();
		}
	}
	static void change() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				if(status[i][j] >= 1 && status[i][j] <= 3) {
					simulMap[i][j]--;
					if(simulMap[i][j] == 0) {
						status[i][j]++;
						simulMap[i][j] = map[i][j];
					}
				}
				if(status[i][j] == 0 && simulMap[i][j] > 0) {
					status[i][j] = 1;
				}
			}
		}
	}
	static void spread() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				if(status[i][j] == 2) {
					for(int d = 0 ; d < 4 ; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if(isOutOfIdx(ni, nj) || status[ni][nj] > 0) continue;
						simulMap[ni][nj] = map[ni][nj] = Integer.max(simulMap[ni][nj], map[i][j]);
					}
					status[i][j] = 3;
				}
			}
		}
	}
	static int getCellCnt() {
		int cnt = 0;
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				if(status[i][j] >= 1 && status[i][j] <= 3)
					cnt++;
			}
		}
		return cnt;
	}
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= R || j < 0 || j >= C;
	}
	static void print(int[][] arr) {								// 출력해보는 메소드
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				System.out.printf("%3d ", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("------------------------------------------------");
	}
}
