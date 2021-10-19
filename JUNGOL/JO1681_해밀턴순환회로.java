import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * JO1681 - 회사에서 출발하여 오늘 배달해야 하는 모든 장소를 한 번씩 들러 물건을 배달하고 회사에 돌아오기 위한 최소의 비용 구하기
 * Date : 210923
 * Thinking : 전에 풀었던 문제랑 거의 같은디 뭐지... 인접행렬 주어짐. 1번 장소에서 시작해서 1번 장소로 돌아오기. 순열(갈 수 있는 경우에)
 * 			이동할 수 없는 경우 0으로 표시되있음. 최소비용보다 큰 경우는 return 시켜버리기
 * Method : 
 * error1 : 틀렸는데 왜틀렸는지 모름ㅋㅋㅋㅋㅋ -> visit[0] = true 체크 안해줬다!
 */
public class JO1681_해밀턴순환회로 {
	static int N;
	static int[][] adjArr;
	static boolean[] visit;
	static int ans = Integer.MAX_VALUE, startIdx = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(bf.readLine());
		adjArr = new int[N][N];
		visit = new boolean[N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				adjArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit[0] = true;									// 회사에 방문체크
		delivery(0, 1, 0);									
		
		System.out.println(ans);
	}
	
	static void delivery(int lastIdx, int cnt, int cost) {	// 순열		
		if(cnt == N) {		// 모든 장소에 다 배달했지만
			if(adjArr[lastIdx][startIdx] == 0) return;		// 회사로 못돌아가면 리턴
			ans = Math.min(ans, cost + adjArr[lastIdx][startIdx]);	// 회사까지의 비용을 더해 최솟값과 비교, 작은값 저장
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			// 방문 했거나, 못가거나, 해당 장소로 가면 답보다 커져버리면 다음으로~
			if(visit[i] || adjArr[lastIdx][i] == 0 || cost + adjArr[lastIdx][i] > ans) continue;
			visit[i] = true;
			delivery(i, cnt + 1, cost + adjArr[lastIdx][i]);
			visit[i] = false;
		}
	}
}
