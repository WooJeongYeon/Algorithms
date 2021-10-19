import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210914
 */
public class BJ1149_RGB거리 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		final int COLOR_NUM = 3;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		int[][] values = new int[N + 1][COLOR_NUM];
		int ans = -1;
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < COLOR_NUM ; j++) {
				values[i][j] = Integer.parseInt(st.nextToken());
				values[i][j] = Math.min(values[i - 1][(j + 1) % 3] + values[i][j], values[i - 1][(j + 2) % 3] + values[i][j]);
			}
		}
		
		ans = Math.min(values[N][0], values[N][1]);
		ans = Math.min(ans, values[N][2]);
		System.out.println(ans);
	}
}