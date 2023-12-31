import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * Date : 2021.12.07
 * Level : SWEA 모의 SW 역량테스트
 * Difficulty : 중
 * Time : 45분 
 * URL : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl
 * Select1 : 군집을 배열 리스트에 저장(이거 선택) VS 그냥 list만들어서 저장
 * Select2 : 매번 배열 생성해줘서 이동시키면 너무 메모리 많아질 거 같아서 군집클래스에 time변수 생성
 * Thinking : 순서를 정함
 * 			1. 군집을 이동시킴
 * 			2. 이동시킨 군집에 대한 처리
 * 				- 가장자리에 있는지
 * 				- 여러 군집들이 있는지
 * Method : 시뮬레이션
 * Result : 에러 안나고 바로 통과! 순서 정해서 메소드들 만들어서 구현
 * 			- 너무 코드가 지저분해진거 같긴 하다ㅜ
 * Plus1 : (다른분 코드)와... 그냥 합쳐지는게 cnt를 더하기만 하는거니까 list로 군집 관리하면서
 * 			int 이차원 배열 만들어서 이걸로 군집 여러개인지 체크했네
 * Plus2 : 그리고 나는 순서를 정했는데 그냥 하나 이동시키고 바로 처리해도 됬음. 이게 훨 난듯(근데 maxCnt가 저장되어 있어야 함)
 */
public class SW2382_미생물격리 {
	static int TC, N, M, K, ans;
	static LinkedList<Group>[][] map;	// 삽입 삭제땜시
	static int[] di = {-1, 1, 0, 0};	// 상하좌우
	static int[] dj = {0, 0, -1, 1};
	static int[] reverseD = {1, 0, 3, 2};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new LinkedList[N][N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = new LinkedList<>();
				}
			}
			for(int k = 0 ; k < K ; k++) {
				st = new StringTokenizer(in.readLine(), " ");
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				map[i][j].add(new Group(d, cnt, 0));
			}				// 여기까지 입력받는거
			
			isolate();
			
			ans = getSum();
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		System.out.println(sb);
	}
	private static void isolate() {
		for(int t = 0 ; t < M ; t++) {
			move(t);
			dieAndCombine();
		}
	}
	private static void move(int t) {		// 군집 이동
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j].isEmpty()) continue;
				Group g = map[i][j].getFirst();
				if(g.time == t) {
					map[i][j].removeFirst();
					g.time = t + 1;
					map[i + di[g.d]][j + dj[g.d]].add(g);
				}
			}
		}
		
	}
	private static void dieAndCombine() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j].isEmpty()) continue;
				if(i == 0 || i == N - 1 || j == 0 || j == N - 1) {		// 가장자리 있는애 처리
					Group g = map[i][j].getFirst();
					if(g.cnt / 2 == 0) {
						map[i][j].clear();
					}
					else {
						g.cnt = g.cnt / 2;
						g.d = reverseD[g.d];
					}
				} 
				if(map[i][j].size() >= 2) {			// 군집 여러개 있는 경우 합치기
					int sum = 0;
					int maxCnt = -1;
					int d = -1;
					int time = map[i][j].getFirst().time;
					for(Group g : map[i][j]) {
						sum += g.cnt;
						if(maxCnt < g.cnt) {
							maxCnt = g.cnt;
							d = g.d;
						}
					}
					map[i][j].clear();
					map[i][j].add(new Group(d, sum, time));
				}
			}
		}
	}
	private static int getSum() {
		int sum = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(!map[i][j].isEmpty()) sum += map[i][j].getFirst().cnt;
			}
		}
		return sum;
	}
	static class Group {
		int d, cnt, time;

		public Group(int d, int cnt, int time) {
			this.d = d;
			this.cnt = cnt;
			this.time = time;		// 같은 배열에서 이동시키도록 하려고(해당 시간애만 이동)
		}
	}
}
