import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.08
 * Level : BaekJoon Gold 5
 * Difficulty : 쉬움(SW4013_특이한자석과 같은 문제 - 풀었었음)
 * Time : 45분
 * URL : https://www.acmicpc.net/problem/14891
 * Select1 : 
 * Thinking : 
 * Method : 
 * Help : 
 * Error1 : 
 * Result : 
 * Plus1 : 
 */
public class BJ14891_톱니바퀴 {
	static int K, ans;
	static int[][] gears;
	static int[] dirArr;
	static final int GEARNUM = 4;
	static final int COGNUM = 8;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		gears = new int[GEARNUM][COGNUM];
		for(int i = 0 ; i < GEARNUM ; i++) {
			String s = in.readLine();
			for(int j = 0 ; j < s.length() ; j++) {
				gears[i][j] = s.charAt(j) - '0';
			}
		}
		K = Integer.parseInt(in.readLine());
		for(int k = 0 ; k < K ; k++) {
			st = new StringTokenizer(in.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			rotateGears(n, d);
		}
		
		ans = getScoreSum();
		System.out.println(ans);
	}
	
	static void rotateGears(int n, int d) {
		dirArr = new int[GEARNUM];
		dirArr[n] = d;
		for(int i = n - 1 ; i >= 0 ; i--) {
			if(gears[i + 1][6] != gears[i][COGNUM - 6]) {
				dirArr[i] = -dirArr[i + 1];
			} else {
				break;
			}
		}
		for(int i = n + 1 ; i < GEARNUM ; i++) {
			if(gears[i][6] != gears[i - 1][COGNUM - 6]) {
				dirArr[i] = -dirArr[i - 1];
			} else {
				break;
			}
		}
		
		for(int i = 0 ; i < GEARNUM ; i++) {
			int tmp = gears[i][0];
			int idx = 0;
			for(int j = 0 ; j < COGNUM - 1 ; j++) {
				int nextIdx = (idx + COGNUM + -dirArr[i]) % COGNUM; 
				gears[i][idx] = gears[i][nextIdx];
				idx = nextIdx;
			}
			gears[i][idx] = tmp;
		}
	}
	
	static int getScoreSum() {
		int sum = 0;
		for(int i = 0 ; i < GEARNUM ; i++) {
			sum += gears[i][0] << i;
		}
		return sum;
	}
}
