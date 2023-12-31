import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
/* 2022.04.29 2�ð�
 * ���� �ɸ� ����
 * 1. 90�� ȸ���ϴ°͵� �߸� �����߰�(��ĭ�� �ű����)
 * 2. �����Ѱ� üũ�ϴ°Ű� �ƴ϶� dfs�� 1 �̻��� �ֵ� �� üũ�ϴ��� �˾Ұ�
 * 3. ȸ���� �ݽð�������� �˾Ұ�
 * 4. ȸ�� ��Ծ��ٰ� ��� �߸� �����ؼ� ����븸 1�ð� �ѰŰ���.
 * -> ���� �Ĳ��� �а� ���� �� ��������... ������ ���ؤФФ�
 * ������ Ǭ�� ���ϱ� �� ��Ǯ���µ�..? 2�� ������ pow �Ⱦ��� ��Ʈ �Ἥ �߰� max�� 1�� ��쵵 üũ���ְ�...
 * �ڵ尡 �� ª������ �ߴµ� ��ġ�ڴ�...�ФФ�
 */
public class BJ20058_������������̾��_�� {
	static int N, Q, total, max, cnt;
	static int[][] map;
	static boolean[][] visited;
	static int[] di = {1, 0, -1, 0}; // �Ʒ� �� �� ��
	static int[] dj = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		st = new StringTokenizer(in.readLine());
		for(int k = 0 ; k < Q ; k++) {
			int L = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
			int[][] newMap = new int[N][N];
			for(int i = 0 ; i < N ; i += L) {
				for(int j = 0 ; j < N ; j += L) {
					for(int m = 0 ; m < L ; m++) {
						for(int n = 0 ; n < L ; n++) {
							newMap[i + n][L - m - 1 + j] = map[i + m][j + n];							
						}
					}
				}
			}
			map = newMap;
			
			visited = new boolean[N][N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					cnt = 0;
					for(int d = 0 ; d < 4 ; d++) {
						int ni = i + di[d];
						int nj = j + dj[d];
						if(ni < 0 || ni >= N || nj < 0 || nj >= N || map[ni][nj] == 0) continue;
						cnt++;
					}
					if(cnt < 3) {
						visited[i][j] = true;
					}
				}
			}
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(visited[i][j] && map[i][j] > 0) {
						map[i][j]--;
						total--;
					}
				}
			}
		}
		visited = new boolean[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				cnt = 0;
				if(visited[i][j] || map[i][j] == 0) continue;
				dfs(i, j);
				max = Math.max(max, cnt);
			}
		}
		System.out.println(total + "\n" + max);
	}

	private static void dfs(int i, int j) {
		visited[i][j] = true;
		cnt++;
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj] || map[ni][nj] == 0) continue;
			dfs(ni, nj);
		}
	}
}
