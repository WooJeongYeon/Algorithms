import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// N�� �̻����� �ڸ� �� �ִ� ���� �ִ�
public class BJ1654_�����ڸ��� {
	static int K, N;
	static long min, max, now, ans;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[K];
		for(int i = 0 ; i < K ; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		min = 1;
		max = Integer.MAX_VALUE;
		while(min <= max) {
			now = (min + max) / 2;
			int cnt = 0;
			for(int i = 0 ; i < K ; i++) {
				cnt += arr[i] / now;
			}
			if(cnt == N) {
				ans = Math.max(ans, now);
				min = now + 1;
			} else if(cnt > N) {	
				ans = Math.max(ans, now);
				min = now + 1;
			} else {
				max = now - 1;
			}
		}
		System.out.println(ans);
	}
}
