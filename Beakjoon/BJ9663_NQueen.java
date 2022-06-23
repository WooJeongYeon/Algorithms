import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ9663_NQueen {
	static int N, ans;
	static int[] idxArr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		idxArr = new int[N];
		
		dfs(0);
		
		System.out.println(ans);
	}
	
	static void dfs(int idx) {
		if(idx == N) {
			ans++;
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			boolean isPossible = true;
			for(int j = 0 ; j < idx ; j++) {
				if(idxArr[j] == i || Math.abs((double)(idxArr[j] - i) / (j - idx)) == 1.0) {
					isPossible = false;
					break;
				}
			}
			if(isPossible) {
				idxArr[idx] = i;
				dfs(idx + 1);
			}
		}
	}
}
