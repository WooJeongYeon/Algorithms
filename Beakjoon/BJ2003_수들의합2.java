import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// long 형으로 받아야함
public class BJ2003_수들의합2 {
	static int N, cnt;
	static int startIdx, endIdx;
	static long M, sum;
	static long[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		sum = arr[0];
		while(true) {		// startIdx가 endIdx보다 커진다고 끝내면 안됨. 끝까지 다 가봐야함
			if(sum == M) cnt++;
			if(sum <= M || startIdx == endIdx) {
				if(endIdx == N - 1) break;
				sum += arr[++endIdx];
			}
			else {
				sum -= arr[startIdx++];
			}
		}
		System.out.println(cnt);
	}
}
