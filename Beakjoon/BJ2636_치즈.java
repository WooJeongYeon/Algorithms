import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210915
 */
public class BJ2636_치즈 {
	static int N, M;		// 행크기, 열크기
	static boolean[][] map, deleteMark, visit;
	static int tmp, time, size;					
	static int[] di = {-1, 1, 0, 0};			// 델타배열 - 위, 아래, 왼, 오
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];							// 치즈의 map
		deleteMark = new boolean[N][M];						// 없앨 치즈구멍 표시
		for(int i = 0 ; i < N ; i++) {						// 치즈 입력받음(true로 저장)
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp == 1 ? true : false;
			}
		}
		
		while((tmp = getCheeseSize()) != 0) {					// 치즈가 없을 때까지 반복
			size = tmp;											// 치즈의 크기 저장
			visit = new boolean[N - time * 2][M - time * 2];	// BFS 방문표시할 배열(치즈 크기가 줄어서 얘도 줄어듬-의미없긴한데...)
			// 굳이 deleteMark에 생성 안하고 visit배열을 int로 생성해 표시하면 되지!
			deleteMark = new boolean[N][M];						// 삭제할 치즈 표시할 배열
			dfs(0, 0);											// bfs 시작
			time++;												// 시간 다음으로
		}
		
		System.out.println(time);
		System.out.println(size);
	}
	
	static void dfs(int i, int j) {								// 치즈 위치 찾기!
		visit[i][j] = true;										// 방문 표시
		if(map[i + time][j + time]) {							// 만약 해당 칸에 치즈가 있다면
			deleteMark[i + time][j + time] = true;				// 지울 치즈로 표시
			return;												// 가장자리 치즈만 고르기 위해 리턴
		}
		for(int d = 0 ; d < 4 ; d++) {							// 상하좌우에 대해 dfs
			int ni = i + di[d];
			int nj = j + dj[d];
			if(!isOutOfIndex(ni, nj) && !visit[ni][nj]) {		// 인덱스 벗어나지 않고, 방문하지 않았으면
				dfs(ni, nj);									// 탐색!
			}
		}
	}
	
	static int getCheeseSize() {							// 치즈의 개수 반환
		int size = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(deleteMark[i][j]) map[i][j] = false;		// 이전에 표시한 삭제할 치즈를 map에서 없앰
				if(map[i][j]) size++;
			}
		}
		return size;
	}
	
	static void print(boolean[][] nowMap) {					// 출력(boolean을 int로 출력)
		System.out.println();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print((nowMap[i][j] ? 1 : 0) + " ");
			}
			System.out.println();
		}
	}
	static boolean isOutOfIndex(int i, int j) {				// 인덱스를 벗어나는지(visit의 크기가 매번 바뀜)
		return i < 0 || i >= N - time * 2 || j < 0 || j >= M - time * 2;
	}
}
