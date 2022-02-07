import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.02.07
 * Level : BaekJoon Gold 3
 * Difficulty : ����
 * URL : https://www.acmicpc.net/problem/14890
 * Thinking)
 * 		- �迭 �ΰ��� ����
 * 		- UP, DOWN / ����, ���� 4���� ���� ����(����)
 * 		- DU - 2L, DD - L, UD - X, UU = L
 * 		- ������ last�� down && len < L�̸� false
 * Method : ����
 * Result : �����ϰ� Ǫ�ϱ� �������� ���� Ǭ�Ű���.
 */
public class BJ14890_���� {
	static int N, L, last, ans;
	static int[][] map1, map2;
	static final int UP = 0;
	static final int DOWN = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map1 = new int[N][N];
		map2 = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				map1[i][j] = map2[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		checkPass(map1);
		checkPass(map2);
		System.out.println(ans);
	}
	private static void checkPass(int[][] map) {
		for(int i = 0 ; i < N ; i++) {
			if(check(map[i])) ans++;
		}
	}
	private static boolean check(int[] arr) {
		int len = 1;
		last = UP;
		for(int i = 1 ; i < N ; i++) {
			if(Math.abs(arr[i] - arr[i - 1]) > 1) return false;
			else if(arr[i - 1] < arr[i]) {
				if(last == DOWN && len < 2 * L) return false; 
				else if(last == UP && len < L) return false;
				last = UP;
				len = 1;
			}
			else if(arr[i - 1] > arr[i]) {
				if(last == DOWN && len < L) return false; 
				last = DOWN;
				len = 1;
			} else {
				len++;
			}
		}
		if(last == DOWN && len < L) return false;
		return true;
	}
	
}
