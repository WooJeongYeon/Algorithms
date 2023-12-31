import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210916
 */
public class SW1263_사람네트워크2 {
	static int TC, N;
	static int[][] D;
	static int MAX = 1000;								// 거리가 1000이 될 수 없으므로 최댓값으로 저장 
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(bf.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			D = new int[N][N];
			// 배열을 입력받음
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					D[i][j] = tmp == 1 ? tmp : MAX;		// 0인 경우 MAX값 저장(안 이어진 경우)
				}
			}
			// 플로이드 워셜 알고리즘으로 모든 최단경로를 구함
			for(int k = 0 ; k < N ; k++) {					// 경유지
				for(int i = 0 ; i < N ; i++) {				// 출발지
					if(i == k) continue;					// 경유지에서 시작하는 경우 컨틴뉴
					for(int j = 0 ; j < N ; j++) {			// 도착지
						if(j == k || j == i) continue;		// 경유지가 도착점이던가 시작점과 도착점이 같은 경우 컨틴뉴
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);		// 이전 최단경로와 현재 경유지를 포함한 경로 중 최단값을 저장
					}
				}
			}
			// CC의 값을 구해서 CC값들 중 최솟값 저장
			int ans = Integer.MAX_VALUE;				
			for(int i = 0 ; i < N ; i++) {					
				int cc = 0;
				for(int j = 0 ; j < N ; j++) {
					if(D[i][j] >= 1000) continue;
					cc += D[i][j];
				}
				ans = Math.min(ans, cc);
			}
			
			sb.append("#" + tc + " " + ans + "\n");		
		}
		System.out.println(sb);
	}
}
