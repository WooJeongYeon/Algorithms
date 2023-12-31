import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/*
 * Date : 210914
 */
public class BJ1463_1로만들기 {
	static int N;
	static int calNum1, calNum2, calNum3;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		
		System.out.println("DP : " + makeDP() + ", " + calNum1);
		System.out.println("BFS : " + makeBFS() + ", " + calNum2);
		System.out.println("Recursive : " + makeRecursive(1, 1) + ", " + calNum3);
	}
	static int makeDP() {
		int[] cost = new int[N + 1];					// 현재까지의 연산 최솟값을 저장
		for(int i = N - 1 ; i >= 1 ; i--) {				// N - 1부터 연산 최솟값을 구해 마지막 1까지 진행
			calNum1++;
			int temp = Integer.MAX_VALUE;				// 최솟값을 저장하기 위한 변수
			if(3 * i <= N) {							// 이전에 3으로 나누는 연산이 가능했다면
				temp = Math.min(temp, cost[3 * i]);		// 최솟값 비교해 저장
			}
			if(2 * i <= N) {							// 이전에 2로 나누는 연산이 가능했다면
				temp = Math.min(temp, cost[2 * i]);		// 최솟값 비교해 저장
			}
			cost[i] = Math.min(temp, cost[i + 1]) + 1;	// i + 1인덱스를 가지는 비용과도 최솟값 비교해 저장(연산 1번을 하므로 이 때 1을 더함)
		}
		return cost[1];
	}
	static int makeBFS() {
		int time = 0;
		if(N == 1) return 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		while(true) {
			time++;
			int size = queue.size();
			for(int i = 0 ; i < size ; i++) {
				calNum2++;
				int now = queue.poll();
				if(now * 2 == N || now * 3 == N || (now + 1) == N) return time;
				if(now * 2 < N) queue.offer(now * 2);
				if(now * 3 < N) queue.offer(now * 3);
				queue.offer(now + 1);
			}
		}
	}
	static int makeRecursive(int time, int now) {
		calNum3++;
		int ans1, ans2, ans3;
		ans1 = ans2 = ans3 = Integer.MAX_VALUE;
		//System.out.println(time + " " + now);
		if(N == 1) return 0;
		if(now * 3== N || now * 2 == N || (now + 1) == N) {
			return time;
		}
		int ans = -1;
		if(now * 3 < N) {
			ans1 = makeRecursive(time + 1, now * 3);
		}
		if(now * 2 < N) {
			ans2 = makeRecursive(time + 1, now * 2);
		}
		ans3 = makeRecursive(time + 1, now + 1);
		return Math.min(ans1, Math.min(ans2, ans3));
	}
}
