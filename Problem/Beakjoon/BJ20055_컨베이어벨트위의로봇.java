import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * Date : 2022.02.01
 * Level : BaekJoon Gold 5
 * Difficulty : ����
 * Why : ���� ���ذ� �������
 * URL : https://www.acmicpc.net/problem/20055 
 * Select1 : 
 * Thinking : ����Ʈ �ϳ��� ������, �ø��� ��ġ �� �־ ��������
 * Method : ����
 * Error1 : �κ� �����̴� �ſ��� ������
 * Result : ���� ���ذ� ������µ� ���� Ǫ�ϱ� ������
 * Plus1 : LinkedList�� �ϴϱ� get�� ����ؼ� �׷��� �ð� ��û����. ArrayList���ϱ� �ð� Ȯ �پ���
 * 		=> ���� �ϳ��� �����ϴ� ���� ���� ArrayList�� ������� 
 */
// Linked�� array�� �ð�����...
public class BJ20055_�����̾Ʈ���Ƿκ� {
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
			if(arr[0] > 0 && arr[1] == 0 && lastArr[1] == 1) {	// ������ ����
				arr[1] = 1;	
				lastArr[1] = 0;
				arr[0]--;			// �̰� ������
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
