import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * Date : 2022.02.01
 * Level : BaekJoon Gold 5
 * Difficulty : 중하
 * Why : 문제 이해가 어려웠음
 * URL : https://www.acmicpc.net/problem/20055 
 * Select1 : 
 * Thinking : 리스트 하나에 내구도, 올리는 위치 다 넣어서 관리하자
 * Method : 구현
 * Error1 : 로봇 움직이는 거에서 에러남
 * Result : 문제 이해가 어려웠는데 막상 푸니까 괜찮네
 * Plus1 : LinkedList로 하니까 get을 사용해서 그런지 시간 엄청나네. ArrayList쓰니까 시간 확 줄었음
 * 		=> 원소 하나에 접근하는 경우는 보통 ArrayList로 사용하자 
 */
// Linked랑 array랑 시간차이...
public class BJ20055_컨베이어벨트위의로봇 {
	static int N, K, time;
	static ArrayList<int[]> belts;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belts = new ArrayList<>();
		st = new StringTokenizer(in.readLine(), " ");
		for(int i = 0 ; i < 2 * N ; i++) {
			belts.add(new int[] {Integer.parseInt(st.nextToken()), 0});
		}
		
		while(true) {
			time++;
			rotateBelts();
			moveRobots();
			if(belts.get(0)[0] > 0) {
				belts.get(0)[1] = 1;
				belts.get(0)[0] -= 1;
			}
			if(!isValidate()) break;
		}
		
		System.out.println(time);
	}
	private static void rotateBelts() {
		belts.add(0, belts.remove(2 * N - 1));
		belts.get(N - 1)[1] = 0;
	}
	private static void moveRobots() {
		for(int i = N - 1 ; i > 0 ; i--) {
			int[] arr = belts.get(i);
			int[] lastArr = belts.get(i - 1);
			if(arr[0] > 0 && arr[1] == 0 && lastArr[1] == 1) {	// 마지막 조건
				arr[1] = 1;	
				lastArr[1] = 0;
				arr[0]--;			// 이거 빼먹음
			}
		}
		belts.get(N - 1)[1] = 0;
	}
	private static boolean isValidate() {
		int cnt = 0;
		for(int[] arr : belts) {
			if(arr[0] == 0) cnt++; 
		}
		if(cnt >= K) return false;
		else return true;
	}
}
