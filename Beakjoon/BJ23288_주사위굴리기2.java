import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2022.01.31
 * Level : BaekJoon Gold 3
 * Difficulty : ��
 * Why : �ֻ��� ������ �� �ٲ��ִ� �͸� �򰥷���
 * Time : 1h
 * URL : https://www.acmicpc.net/problem/23288
 * Thinking - K�� �ݺ�
 * 		1. �̵� �������� ��. ĭ�� ������ �̵� ���� �ݴ�� ��
 * 		2. ������ ĭ�� ���� BFS Ž������ ���� �ش� ĭ�� �� * ������ŭ ���� ����
 * 		3. �ֻ��� �Ʒ��� ���� A�� �ش� ĭ�� ���� B�� ���� �̵����� ���� -> �ֻ��� ȸ��
 * Method : BFS, Dice ��ü
 * Error1 : BFS���� !isEmpty()�� �� ���ư�����
 * Error2 : ó�� ������ visited ó�� ������
 * Result : ������ Ǯ�� Ǫ�ϱ� ���� ����. ��ü ���� �����ؼ� �������.
 * Plus1 : �ֻ��� �� 6���� �밡�ٷ� �Ű��� �� �ֳ�
 */
public class BJ23288_�ֻ���������2 {
	static int N, M, K, ans;
	static int[][] map;
	static int[] di = {0, 1, 0, -1};	// ��������
	static int[] dj = {1, 0, -1, 0};
	static Dice dice;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dice = new Dice();
		for(int k = 0 ; k < K ; k++) {
			dice.roll(N, M);
			getScore();
			dice.changeDirection();
		}
		System.out.println(ans);
	}
	private static void getScore() {
		int n = map[dice.i][dice.j];
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new int[] {dice.i, dice.j});
		visited[dice.i][dice.j] = true;	//2. �߰�
		while(!queue.isEmpty()) {	// 1. ! �޾���
			int[] pos = queue.poll();
			cnt++;
			for(int d = 0 ; d < 4 ; d++) {
				int ni = pos[0] + di[d];
				int nj = pos[1] + dj[d];
				if(ni < 0 || ni >= N || nj < 0 || nj >= M || visited[ni][nj] || map[ni][nj] != n) continue;
				visited[ni][nj] = true;
				queue.offer(new int[] {ni, nj});
			}
		}
		ans += n * cnt;
	}
	static class Dice {
		final int HORIZON = 0;
		final int VERTICAL = 1;
		int[][] planeArr = {{1, 3, 6, 4}, {1, 5, 6, 2}};
		int i = 0, j = 0, d = 0;
		void roll(int N, int M) {		// �ֻ��� ȸ��
			int ni = i + di[d];
			int nj = j + dj[d];
			int rotateIdx = -1, num = -1;
			if(ni < 0 || ni >= N || nj < 0 || nj >= M) {
				d = (d + 2) % 4;
			}
			i += di[d];
			j += dj[d];
			switch(d) {
			case 0:
				rotateIdx = HORIZON;
				num = -1;
				break;
			case 1:
				rotateIdx = VERTICAL;
				num = -1;
				break;
			case 2:
				rotateIdx = HORIZON;
				num = 1;
				break;
			case 3:
				rotateIdx = VERTICAL;
				num = 1;
				break;
			}
			int tmp = planeArr[rotateIdx][0];
			int idx = 0;
			for(int i = 0 ; i < 3 ; i++) {			// �ش� ��� ȸ��
				planeArr[rotateIdx][idx] = planeArr[rotateIdx][(idx + num + 4) % 4];
				idx = (idx + num + 4) % 4;
			}
			planeArr[rotateIdx][idx] = tmp;			// ����, �Ʒ��� �Ȱ��� ������
			planeArr[1 - rotateIdx][0] = planeArr[rotateIdx][0]; 
			planeArr[1 - rotateIdx][2] = planeArr[rotateIdx][2]; 
		}
		void changeDirection() {					// ���� �ٲٱ�
			int n = map[i][j];
			if(getFloorNum() > n) d = (d + 1) % 4;
			else if(getFloorNum() < n) d = (d - 1 + 4) % 4;
		}
		int getFloorNum() {
			return planeArr[0][2];
		}
	}
}
