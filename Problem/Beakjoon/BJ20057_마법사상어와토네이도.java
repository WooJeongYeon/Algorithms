import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 2021.11.16
 * Level : BaekJoon Gold 4
 * Difficulty : 중쪼오금상
 * URL : https://www.acmicpc.net/problem/20057
 * Select1 :  mask를 리스트(클래스 + 리스트)로 만들지(확장성은 이게좋을듯)
 *				 VS 2차원 배열(단순배열)로 만들고 돌린걸 다 저장할지(초기셋팅은 이게 좋을듯) -> 이걸로하자
 * Thinking : board에 씌울 mask를 4가지 rotate해서 만들어놓음(방향별 사용)
 * 			- 토네이도 1 1 2 2 3 3 4 4 5 5... 이런 식으로 길이 반복됨 + 매 길이마다 방향 전환(왼 - 아래 - 오른 - 위)
 * Method : 배열회전, 시뮬레이션
 * Result : 안쓰고 어떻게 할지 생각하다가 구현함(모듈 단위로 실행해보면서) 초반에 에러났는데 모래 날리는 건 바로 실행되고 통과해서 놀랐다...
 * Plus1 : 날아간 모래가 board 밖으로 나가는지 안나가는지는 두 군데 들어가서 메소드로 만들 수 있었네(모래 날릴 때, a로 옮길 때) 
 */
public class BJ20057_마법사상어와토네이도 {
	static int N, ans;
	static int[][] board;
	static int[][] mask = {{0, 0, 2, 0, 0},
							{0, 10, 7, 1, 0},
							{5, 0, 0, 0, 0},
							{0, 10, 7, 1, 0},
							{0, 0, 2, 0, 0}};	
	static int[][][] masks;
	static int[] di = {0, 1, 0, -1};
	static int[] dj = {-1, 0, 1, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		board = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			String[] arr = in.readLine().split(" ");
			for(int j = 0 ; j < N ; j++) {
				board[i][j] = Integer.parseInt(arr[j]);
			}
		}
		setting();
		tornado();
		
		System.out.println(ans);
	}
	
	static void tornado() {
		int d = 0;
		int cntTotal = 1;
		int i = N / 2, j = N / 2;
		while(true) {
			for(int outCnt = 0 ; outCnt < 2 ; outCnt++) {
				for(int cnt = 0 ; cnt < cntTotal ; cnt++) {
					i = i + di[d];
					j = j + dj[d];
					if(isOutOfIdx(i, j)) return;	// 3중 반복문이라 break말고 return 해야대...
//					System.out.println(i + " " + j);
					moveSend(i, j, d);
				}
				d = (d + 1) % 4;
			}
			cntTotal++;
		}
	}
	
	static void moveSend(int i, int j, int d) {		// 모래를 이동시킴
		int total = board[i][j];
		int sum = board[i][j];
		int ni = i - 2;		// 마스킹 시작위치
		int nj = j - 2;
		for(int r = 0 ; r < mask.length ; r++) {
			for(int c = 0 ; c < mask.length ; c++) {
				if(masks[d][r][c] > 0) {
					int send = total * masks[d][r][c] / 100;
					sum -= send;
					addSend(ni + r, nj + c, send);
				}
			}
		}
		addSend(i + di[d], j + dj[d], sum);
	}
	
	static void addSend(int i, int j, int send) {	// 모래가 바깥으로 나가냐 아니냐에 따라 저장하는 곳이 달라짐
		if(isOutOfIdx(i, j)) {
			ans += send;
		} else {
			board[i][j] += send;
		}
	}

	static void setting() {							// 사용할 mask들 셋팅
		masks = new int[4][][];
		masks[0] = mask;
		makeRotateMask();
	}
	
	static void makeRotateMask() {					// mask를 만들어놓음(4 방향으로)
		for(int n = 1 ; n < 4 ; n++) {
			masks[n] = new int[mask.length][mask.length];
			for(int i = 0 ; i < mask.length ; i++) {
				for(int j = 0 ; j < mask.length ; j++) {
					masks[n][mask.length - j - 1][i] = masks[n - 1][i][j];
				}
			}
		}
	}
	
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	
//	static void print(int[][] arr) {
//		System.out.println("------------------------------");
//		for(int i = 0 ; i < 5 ; i++) {
//			for(int j = 0 ; j < 5; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	
//	static class Pixel {
//		int relI, relJ, retio;
//		public Pixel(int relI, int relJ, int retio) {
//			this.relI = relI;
//			this.relJ = relJ;
//			this.retio = retio;
//		}
//	}
}
