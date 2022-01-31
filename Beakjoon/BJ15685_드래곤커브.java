import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * Date : 2022.01.31
 * Level : BaekJoon Gold 4
 * Difficulty : ����
 * URL : https://www.acmicpc.net/problem/15685
 * Select1 : LinkedList�� �迭 �����ٰ� LinkedList�� �Ź� �������� �ؾߵǰ�, �迭�� ũ�� �����ϱⰡ �� �����Ƽ� ArrayList ���
 * Thinking
 * 		- ���� �� ���ϰ�
 * 		- ���� ��ġ���� �� �������� ���� �����鼭 visited üũ�� 
 * Method : ����
 * Error1 : ���� �Ųٷ� �����ؼ� 90�� ȸ���� 
 * Error2 : i, j �ݴ�� �Է¹޾Ҵ�
 * Result : ������ �� �س����� ������ ���� �����ε�
 */
public class BJ15685_�巡��Ŀ�� {
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
			int j = Integer.parseInt(st.nextToken());		// i, j �ݴ�� ����
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
		for(int n = 0 ; n < g ; n++) {				// ���븸ŭ ���Ľ�Ŵ(�� ������ -90�� ȸ��)
			int size = dList.size();
			for(int s = size - 1 ; s >= 0 ; s--) {	// 1. �ݴ�� ���̳�
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
