import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210916
 */
public class BJ9205_맥주마시면서걸어가기 {
	static boolean visit[];
	static int[][] pos;
	static int TC, N;
	static String ans;
	static final int MAX_POWER = 1000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("input2.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(bf.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			N = Integer.parseInt(bf.readLine());
			pos = new int[N + 2][2];
			visit = new boolean[N + 1];
			for(int i = 0 ; i < N + 2 ; i++) {
				st = new StringTokenizer(bf.readLine());
				pos[i][1] = Integer.parseInt(st.nextToken()); 
				pos[i][0] = Integer.parseInt(st.nextToken());
			}
			if(isGoFestival(0, MAX_POWER)) ans = "happy";
			else ans = "sad";
			
			sb.append(ans + "\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean isGoFestival(int s, int power) {
		if(getDistance(s, N + 1) <= power) return true;
		for(int e = 1 ; e <= N ; e++) {
			if(!visit[e] && getDistance(s, e) <= power) {
				visit[e] = true;
				if(isGoFestival(e, MAX_POWER))
					return true;
			}
		}
		
		return false;
	}
	
	static int getDistance(int i, int j) {
		return Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]);
	}
}
