import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Date : 2022.04.30(��)
 * Level : BaekJoon Gold 5
 * Difficulty : ����
 * Time : 1h 30m
 * Method : ����
 * Result : �ؽ÷� �غôµ� �����פ�����
 * Plus1 : �����غ��ϱ� i�� j ��� ���� �ֵ���� �����ϴϱ� 
 *		- likeCnt, emptyCne���� �� ������ ���� �ָ� �����ϸ� ���ڳ�(�ȹٲ�)
 *		- ���� �ٸ��� -> ������ Ž���� ��
 * 		- ���� ���� -> ���� �ٸ��� -> ������ Ž���� ��
 */

public class BJ21608_����ʵ��б�_�� {
	static int N, ans;
	static int[][] board;
	static Set<Integer>[] setArr;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int[] scores = {0, 1, 10, 100, 1000};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		board = new int[N][N];
		setArr = new HashSet[N * N + 1];
		for(int a = 1 ; a <= N * N ; a++) {
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			setArr[n] = new HashSet<>();
			for(int j = 0 ; j < 4 ; j++) {
				setArr[n].add(Integer.parseInt(st.nextToken()));
			}
			
			int gLikeN = -1, gEmptyN = -1, ansI = N, ansJ = N;
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(board[i][j] != 0) continue;
					int likeN = 0, emptyN = 0;
					for(int d = 0 ; d < 4 ; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
						if(board[ni][nj] == 0) emptyN++;
						if(setArr[n].contains(board[ni][nj])) likeN++;
					}
					if(gLikeN < likeN || (gLikeN == likeN && gEmptyN < emptyN)) {
						gLikeN = likeN;
						gEmptyN = emptyN;
						ansI = i;
						ansJ = j;
					}
				}
			}
			board[ansI][ansJ] = n;
		}
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				int n = board[i][j];
				int cnt = 0;
				for(int d = 0 ; d < 4 ; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
					if(setArr[n].contains(board[ni][nj])) cnt++;
				}
				ans += scores[cnt];
			}
		}
		System.out.println(ans);
	}
}
