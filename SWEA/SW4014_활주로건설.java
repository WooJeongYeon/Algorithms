import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.08
 * Level : SWEA
 * Thinking : 행으로도 열로도 돌려봄, 차이가 절대값 1 초과일 때, 1일 때, -1일 때, 0일때 4가지로 나눠서 해봄 
 * Method : 단순반복, 열을 돌려 행으로 만들어 같은 메소드 사용
 * Error1 : 높은곳에서 내려가는 것만 생각했었음 -> sub -1일때도 추가
 * Error2 : 맨 처음에 isTop이 true인가 false인가 -> 올라갈때 말고 내려갈때만 영행을 미치므로 true로 놓고 함
 * Error3 : 내려갔다 올라갈 때 경사로 겹치면 X -> down변수 추가해줌
 * Result : 바로 될줄 알았는데 안돼서 추가하고 추가하고... 여튼 과제인데 늦게제출했다. 스터디나 보충땜시 시간이엄서서...
 * Plus : 쌤은 현재 위치에서 경사로 길이만큼 앞으로 가서 유효성 체크해줬대... 이게 훨씬 좋아보이는데? -> 근데 쌤 코드 보니가... 이것도 복잡해보임ㅋㅋㅋㅋㅋ
 */
public class SW4014_활주로건설 {
	static int TC, N, X, ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());		
			X = Integer.parseInt(st.nextToken());		
			int[][] map = new int[N][N];
			int[][] rMap = new int[N][N];			// 같은 메소드 사용하려고 배열을 돌려서 저장
			ans = 0;	
			for(int i = 0 ; i < N ; i++) {					
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0 ; j < N ; j++) {						
					rMap[j][i] = map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0 ; i < N ; i++) {
				check(i, map);						// 행에 대해
				check(i, rMap);						// 열에 대해
			}
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		System.out.println(sb);
	}
	static void check(int i, int[][] map) {
		boolean isTop = true, down = false;
		int cnt = 1;
//		int last = map[i][0];
		for(int j = 1 ; j < N ; j++) {
			int sub = map[i][j - 1] - map[i][j];
			if(Math.abs(sub) > 1) return;
			else if(sub == -1) {
				if(cnt < X) return;
				if(down && cnt < X * 2) return;
				cnt = 1;
				isTop = true;
				down = false;
			}
			else if(sub == 1) {
				if(!isTop && cnt < X) return;
				cnt = 1;
				isTop = false;
				down = true;
			}
			else {
				cnt++;
			}
		}
		if(isTop || cnt >= X) {
//			System.out.println(Arrays.toString(map[i]));
			ans++;
		}
	}
}
