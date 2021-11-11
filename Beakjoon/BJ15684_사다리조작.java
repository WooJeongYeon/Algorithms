import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.11
 * Level : BaekJoon Gold 4
 * Difficulty : 중상
 * Time : 2시간 넘게.. 
 * URL : https://www.acmicpc.net/problem/15684
 * Select1 : 
 * Thinking : 
 * Method : 
 * Error1 : 
 * Result : 
 * Plus1 : 
 */
public class BJ15684_사다리조작 {
	static int N, H, M, ans, maxCnt = 3;
	static boolean[][] isLink;
	static int[] di = {0, 0, 1};	// 왼쪽 - 오른쪽 - 아래
	static int[] dj = {-1, 1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		isLink = new boolean[H][N - 1];
		ans = -1;
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int h = Integer.parseInt(st.nextToken()) - 1;
			int n = Integer.parseInt(st.nextToken()) - 1;
			isLink[h][n] = true;
		}
		
		maxCnt = ((N - 1) * H - M) < 3 ? ((N - 1) * H - M) : maxCnt;  
		
		for(int i = 0 ; i <= maxCnt ; i++ ) {
			if(comb(0, 0, i)) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
	}
	
	static boolean comb(int start, int cnt, int maxCnt) {		// 조합으로 사다리 가로선 선택
		if(cnt == maxCnt) {
			return isPossible();
		}
		for(int i = start ; i < (N - 1) * H ; i++) {		
			int row = i / (N - 1);	// 이거 헷갈리네
			int col = i % (N - 1);
//			System.out.println(i + " " + row + " " + col);
			if(isLink[row][col]) continue;
			isLink[row][col] = true;
			if(comb(start + 1, cnt + 1, maxCnt)) return true;
			isLink[row][col] = false;		// 조합 쓰는 법도 헷갈렸음
		}
		return false;
	}

	static boolean isPossible() {
		for(int j = 0 ; j < N ; j++) {
			int nowI = 0, nowJ = j, lastJ = -1;
			while(nowI < H) {
				if(nowJ > 0) {
					if(isLink[nowI][nowJ - 1] && nowJ - 1 != lastJ) {
						lastJ = nowJ;
						nowJ--;
						continue;
					}
				}
				
				if(nowJ < N - 1) {
					if(isLink[nowI][nowJ] && nowJ + 1 != lastJ) {
						lastJ = nowJ;
						nowJ++;
						continue;
					}
				}
				
				lastJ = nowJ;
				nowI++;
			}
			if(j != nowJ) return false;
		}
		
		return true;
	}
	
	static void print() {
		for(int i = 0 ; i < H ; i++) {
			for(int j = 0 ; j < N - 1; j++) {
				System.out.print((isLink[i][j] ? 1 : 0) + " ");
			}
			System.out.println();
		}
		System.out.println("--------------------------------");
	}
}
