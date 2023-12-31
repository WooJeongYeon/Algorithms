import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.01
 * Level : BaekJoon 실버1
 * Method : SW4013_특이한자석에서 TC만 없어지고 개수만 늘은 문제
 * Result : 시간걱정했는데 통과하네
 * Plus1 : LinkedList로 하는 경우 배열 옮기는게 더 간단해진대(메소드 있음)
 * 			magnet[i].addFirst(magnet[i].removeLast())
 * 			magnet[i].addLast(magnet[i].removeFirst())
 */
public class BJ15662_톱니바퀴2 {
	static int T, K, ans;
	static ArrayList<Integer>[] magnetics; // 자석들의 자성정보
	static int[] dirInfo; // 방향 정보
	static final int BLADENUM = 8; // 날의 개수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		magnetics = new ArrayList[T];
		ans = 0;
		for (int i = 0; i < T; i++) { // 자석들의 자성정보 입력받음
			magnetics[i] = new ArrayList<Integer>();
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < BLADENUM; j++) {
				magnetics[i].add(ch[j] - '0');
			}
		}
		K = Integer.parseInt(br.readLine()); // 회전횟수

		for (int i = 0; i < K; i++) { // K번만큼 회전
			st = new StringTokenizer(br.readLine(), " ");
			dirInfo = new int[T]; // 회전 후의 방향을 저장할 배열
			int start = Integer.parseInt(st.nextToken());
			dirInfo[start - 1] = Integer.parseInt(st.nextToken()); // 처음 회전위치의 회전방향을 저장해줌

			getDir(start - 1); // 각 자석의 회전방향 얻음
			rotation(); // 회전방향으로 회전
		}

		calc(); // 답 계산

		System.out.println(ans);

	}

	static void getDir(int start) { // 회전했을 때의 각 자석들의 회전할 방향을 저장
		for (int i = start - 1; i >= 0; i--) { // 시작점을 기준으로 왼쪽
			if (magnetics[i].get(2) + magnetics[i + 1].get(6) == 1) { // 맞다은 극의 합이 1이면(다른 극이라면)
				dirInfo[i] = -dirInfo[i + 1]; // 방향 바꿔서 저장
			} else
				break; // 같은 극이면 더이상 진행할 필요X
		}
		for (int i = start + 1; i < T; i++) { // 시작점을 기준으로 오른쪽
			if (magnetics[i - 1].get(2) + magnetics[i].get(6) == 1) {
				dirInfo[i] = -dirInfo[i - 1];
			} else
				break;
		}
	}

	static void rotation() { // 해당 방향으로 회전
		for (int i = 0; i < T; i++) { // 모든 자석에 대해
			int tmp;
			if (dirInfo[i] == 1) { // 시계방향이면
				tmp = magnetics[i].remove(magnetics[i].size() - 1); // 맨 뒤에걸 빼서
				magnetics[i].add(0, tmp); // 앞에 넣음
			} else if (dirInfo[i] == -1) { // 반시계방향이면
				tmp = magnetics[i].remove(0); // 맨 앞에걸 빼서
				magnetics[i].add(tmp); // 뒤에 넣음
			}
		}
	}

	static void calc() { // 결과를 계산
		for (int i = 0; i < T; i++) {
			ans += (magnetics[i].get(0) & 1); // 0번째 날의 각 자석의 점수 - 1, 2, 4, 8(2의 배수)
		}
	}
}
