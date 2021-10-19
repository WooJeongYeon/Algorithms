import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.04(재)
 * Level : BaekJoon 골드 4
 * Thinking - dijkstra, 플로이드 워셜
 * Method : 플로이드 워셜
 * Error1 : floyd메소드 호출 안함...
 * Error2 : (다른사람 코드 봄)v1이 1될 수도, v2가 N이 될 수도 있음... -> if(i == j) adjArr[i][j] = 0; 해서 자기 자신으로 가는 값에 0 저장
 * Plus1 : (다른사람 코드 봄)INF를 80만 * 3 + 1으로 해 놓으면 정상 값으로 3개 더해도 나올 수 없으므로 result1, result2에 if문 빼도 됨!  
 * Result : 전에 풀었다 안되서 미루다 푼건데... 문제를 더 잘 읽어봅시다...
 */
public class BJ1504_특정한최단경로_Floyd {
	static int N, E;
	static int v1, v2;
	static int[][] adjArr;
	static final int INF = 2400001;					// 80만 * 3 + 1
	static int result1, result2, result;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjArr = new int[N + 1][N + 1];
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				if(i == j) adjArr[i][j] = 0;			// 자기 자신으로 갈 수 있으므로 0 저장
				else adjArr[i][j] = INF;
			}
		}
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());	
			adjArr[start][end] = weight;				// 양방향
			adjArr[end][start] = weight;
		}
		st = new StringTokenizer(in.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		floyd();
		
		result1 = adjArr[1][v1] + adjArr[v1][v2] + adjArr[v2][N];			// 순서 바꿔서 해보기
		result2 = adjArr[1][v2] + adjArr[v2][v1] + adjArr[v1][N]; 
		result = Integer.min(result1, result2);
		if(result >= INF) result = -1;
		System.out.println(result);
	}
	
	static void floyd() {								// 플로이드 워샬
		for(int k = 1 ; k <= N ; k++) {
			for(int i = 1 ; i <= N ; i++) {
				if(i == k) continue;					// k랑 같은지 보는건 의미 없을듯
				for(int j = 1 ; j <= N ; j++) {
					if(j == k || j == i) continue;
					adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
				}
			}
		}
	}
		
}
