import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.08
 * Level : BaekJoon Gold 5
 * Difficulty : 중하(SW4013_특이한자석과 같은 문제 - 풀었었음)
 * Time : 45분
 * URL : https://www.acmicpc.net/problem/14891
 * Select1 : 배열 회전 시계방향, 반시계방향 안나누고 한 for문으로 돌림
 * Thinking : 
 * Method : 배열, 시뮬레이션
 * Error1 : 방향을 반대로 생각하고 돌림 
 * Result : 논리만 대충 쓰고 코드로 옮겼다.
 * Plus1 : 이럴수가. 이걸 1달좀전에 풀었는데 그때는 ArrayList로 앞에 뒤에 뺐다꼈다했네
 * 			list생각도 못했는데ㅋㅋㅋㅋㅋㅋㅋ 예전이 더 낫다...ㅠㅠㅠ
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
