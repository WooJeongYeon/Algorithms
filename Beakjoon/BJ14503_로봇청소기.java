import java.util.Scanner;
/*
 * Date : 2021.11.07
 * Level : BaekJoon Gold 5
 * Difficulty : 쉬움
 * Time : 25분 
 * URL : https://www.acmicpc.net/problem/14503
 * Thinking : 인덱스 나가는지 봤다가 모서리 다 1이길래 뺌
 * Method : 시뮬레이션
 * Error1 : 후진하는 경우는 청소되어 있을 수 있으므로 해당 칸이 0일때만 ans에 더해야 함
 * Error2 : 방향 1부터 입력받는 줄 알고 -1해서 사용했었으ㅋㅋㅋㅋ 
 * Result : 문제가 안어려워 보여서 바로 구현. 
 */
public class BJ14503_로봇청소기 {
	static int N, M, ans;
	static int[][] map;
	static int robotI, robotJ, robotD;
	static int[] di = {-1, 0, 1, 0};	// 북-동-남-서
	static int[] dj = {0, 1, 0, -1};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		robotI = in.nextInt(); robotJ = in.nextInt(); robotD = in.nextInt();
		map = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		while(true) {					// false 받을 때까지 반복
			if(!clean()) break;
		}
		
		System.out.println(ans);
	}
	
	static boolean clean() {
		if(map[robotI][robotJ] == 0) {		// 후진하는 경우 이미 청소되있을 수 있음
			ans++;
		}
		map[robotI][robotJ] = 2;
		
		for(int r = 1 ; r <= 4 ; r++) {			// 4 방향에 대해 차례대로 검사
			robotD = ((robotD) - 1 + 4) % 4;
			int ni = robotI + di[robotD];
			int nj = robotJ + dj[robotD];
			if(map[ni][nj] >= 1) continue;
			robotI = ni; robotJ = nj;
			return true;
		}
		int ni = robotI + di[(robotD + 2) % 4];	// 후진해서
		int nj = robotJ + dj[(robotD + 2) % 4];
		if(map[ni][nj] == 1) return false;		// 벽이면 false 반환
		robotI = ni; robotJ = nj;
		return true;
	}
}
