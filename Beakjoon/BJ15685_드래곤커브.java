import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * Date : 2022.01.31
 * Level : BaekJoon Gold 4
 * Difficulty : 중하
 * URL : https://www.acmicpc.net/problem/15685
 * Select1 : LinkedList나 배열 쓰려다가 LinkedList는 매번 직접접근 해야되고, 배열은 크기 관리하기가 더 귀찮아서 ArrayList 사용
 * Thinking
 * 		- 방향 다 구하고
 * 		- 시작 위치에서 그 방향으로 각각 가보면서 visited 체크함 
 * Method : 구현
 * Error1 : 방향 거꾸로 접근해서 90씩 회전함 
 * Error2 : i, j 반대로 입력받았다
 * Result : 생각만 잘 해놓으면 구현은 쉬운 문제인듯
 */
public class BJ15685_드래곤커브 {
	static int N;
	static boolean[][] map;
	static ArrayList<Integer> dList;
	static int[] di = {0, -1, 0, 1};
	static int[] dj = {1, 0, -1, 0};
	static final int SIZE = 101;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		map = new boolean[SIZE][SIZE];
		for(int n = 0 ; n < N ; n++) {
			st = new StringTokenizer(in.readLine(), " ");
			dList = new ArrayList<>();
			int j = Integer.parseInt(st.nextToken());		// i, j 반대로 저장
			int i = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			makeDragonCurveDir(d, g);
			markDragonCurve(i, j);
		}
		System.out.println(getSquareCnt());
		
	}
	private static void makeDragonCurveDir(int d, int g) {		
		dList.add(d);
		for(int n = 0 ; n < g ; n++) {				// 세대만큼 증식시킴(각 방향을 -90도 회전)
			int size = dList.size();
			for(int s = size - 1 ; s >= 0 ; s--) {	// 1. 반대로 붙이네
				dList.add((dList.get(s) + 1) % 4);		
			}
		}
	}
	private static void markDragonCurve(int i, int j) {		
		map[i][j] = true;
		for(Integer d : dList) {
			i += di[d];
			j += dj[d];
			if(i < 0 || i >= SIZE || j < 0 || j >= SIZE) continue;
			map[i][j] = true;
		}
	}
	private static int getSquareCnt() {
		int cnt = 0;
		for(int i = 0 ; i < SIZE - 1 ; i++) {
			for(int j = 0 ; j < SIZE - 1 ; j++) {
				if(map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1])
					cnt++;
			}
		}
		return cnt;
	}

}
