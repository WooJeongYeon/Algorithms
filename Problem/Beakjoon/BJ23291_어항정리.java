import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * Date : 2022.01.29
 * Level : BaekJoon Gold 1
 * Difficulty : ��
 * Why : ��� �ε��� �ٲ���� �ؼ� �������
 * Time : 3h 35m
 * URL : https://www.acmicpc.net/problem/23290
 * Thinking : �⺻���� ArrayList�� �����ϰ�(���� �����Ϸ���) ������ �ֵ� �� ���� �迭�� ��ȯ�ؼ� ���
 * Method : �ùķ��̼�, ����
 * Error : �׳� ���� ���� �������� �־���... ����븸 2�ð� �ɸ���
 * Result : �� ������ �ٽ� Ǯ�� ���� �ʴ�. �迭 ȸ���ϰ� �ε��� ��� �ű�� �� ���� �������
 */
public class BJ23291_�������� {
	static int N, K, ans;
	static ArrayList<Fishbowl> fishbowls;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		fishbowls = new ArrayList<>();
		st = new StringTokenizer(in.readLine(), " ");
		for (int j = 0; j < N; j++) {
			fishbowls.add(new Fishbowl(0, j, Integer.parseInt(st.nextToken())));
		}
		while (true) {
			if (isEndPoint())
				break;
			ans++;
			addFishInBowls();
			stackFishbowls();
			adjustFish();
			alignFishbowls();
			stackFishbowlsTwice();
			adjustFish();
			alignFishbowls();

		}
		System.out.println(ans);
	}

	private static void stackFishbowlsTwice() {
		int size = N / 2;
		for (int n = 1; n <= 2; n++) {
			for (int i = 0; i < N / 2; i++) {
				Fishbowl f = fishbowls.get(i);
				f.i = n - 1 - f.i + n;
				f.j = size - 1 - f.j;
			}
			for (int i = N / 2; i < N ; i++) {
				Fishbowl f = fishbowls.get(i);
				f.j = f.j - size;
			}
			size /= 2;
			Collections.sort(fishbowls);	// ������. ���� ����� ��
		}
	}

	private static void alignFishbowls() {
		int j = 0;

		for (Fishbowl f : fishbowls) {
			f.i = 0;
			f.j = j++;
		}
	}

	private static void adjustFish() {
		int n = 0, m = 0;
		for (Fishbowl f : fishbowls) {
			if (f.i > n)
				n = f.i;
			if (f.j > m)
				m = f.j;
		}
		n++; m++;
		int[][] arr = new int[n][m];
		int[][] newArr = new int[n][m];
		for (Fishbowl f : fishbowls) {
			arr[f.i][f.j] = f.cnt;
			newArr[f.i][f.j] = f.cnt;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0)
					continue;
				for (int d = 0; d < 4; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if (ni < 0 || ni >= n || nj < 0 || nj >= m)
						continue;
					if (arr[ni][nj] == 0) // 4. �̰͵� �������
						continue;
					int max = Math.max(arr[i][j], arr[ni][nj]);
					int min = Math.min(arr[i][j], arr[ni][nj]);
					int dif = (max - min) / 5;
					if (dif > 0) {
						if (arr[i][j] > arr[ni][nj]) { // 5. �ι� ������ָ� �ȵ�.. �ƴϸ� visited üũ�ϴ���
							newArr[i][j] -= dif;	//2. arr�� ����� 3.  dif�� +�̳� - ����� ��
						} else {
							newArr[i][j] += dif;
						}
					}
				}
			}
		}
		fishbowls.clear();
		for (int j = 0; j < m; j++) {
			for (int i = 0 ; i < n; i++) {
				if (newArr[i][j] == 0)
					continue;
				fishbowls.add(new Fishbowl(i, j, newArr[i][j]));
			}
		}
	}

	private static void stackFishbowls() {
		fishbowls.get(0).i = 1;
		for (int i = 1; i < N ; i++) {
			fishbowls.get(i).j -= 1;
		}
		while(true) {
			Collections.sort(fishbowls);
			int n = 0, m = 0, cnt = 0;
			for (Fishbowl f : fishbowls) {
				if (f.i > n)
					n = f.i;
				if (f.j > m)
					m = f.j;
			}
			n++; m++;
			for (Fishbowl f : fishbowls) {
				if(f.i == n - 1) cnt++;
			}
			if((m - cnt) < n) break;
			for(int idx = 0 ; idx < n * cnt ; idx++) {
				Fishbowl f = fishbowls.get(idx);
				f.i = n - 1 - f.i;	// ���� �ε��� ��� Ʋ��(ȸ��)
				int tmp = f.i;
				f.i = f.j;
				f.j = n - 1 - tmp;
				f.i = cnt - 1 - f.i;
				f.i++;
			}
			for(int idx = n * cnt ; idx < N ; idx++) {
				Fishbowl f = fishbowls.get(idx);
				f.j -= cnt;
			}
			
		}
	}

	private static void addFishInBowls() {
		int min = Integer.MAX_VALUE;
		for (Fishbowl f : fishbowls) {
			if (f.cnt < min)
				min = f.cnt;
		}
		for (Fishbowl f : fishbowls) {
			if (f.cnt == min)
				f.cnt++;
		}
	}

	private static boolean isEndPoint() {
		boolean isEnd = false;
		int max = 0, min = Integer.MAX_VALUE;
		for (Fishbowl f : fishbowls) {
			if (f.cnt > max)
				max = f.cnt;
			if (f.cnt < min)
				min = f.cnt;
		}
		if ((max - min) <= K)
			isEnd = true;
		return isEnd;
	}

	static class Fishbowl implements Comparable<Fishbowl> {
		int i, j, cnt;

		public Fishbowl(int i, int j, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}

		public int compareTo(Fishbowl o) {
			if (this.j == o.j)
				return this.i - o.i;
			return this.j - o.j;
		}
	}
}
