import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * Date : 2021.12.06
 * Level : SWEA 모의 SW 역량테스트
 * Difficulty : 중하
 * Why : 16진수 10진수로 변환하는것땜시
 * Time : 40분
 * URL : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo&
 * Thinking : 처음엔 set이랑 pq 쓸까? 하다가 그렇게 값이 큰것도 아니라 그냥 list로 사용 
 * Method : 진수변환
 * Error1 : 16진수로 변환하려는 s의 범위 잘못잡았었음(N/4인데 4로 잡았었다)
 * Result : 문법들을 잘 아는게 중요하네
 * 		- Collections.sort + Collections.reverse하면 내림차순 정렬됨
 * 		- Arrays.sort(arr,Collections.reverseOrder()) 도 내림차순 정렬 -> pq할당해줄 때 Collections.reverseOrder()로 최댓값 꺼내도록 할 수 있음
 * 		- 진수변환 Integer.parseInt(hex, 16) -> hex를 16진수로 인식해 10진수로 변환해줌!!
 * Plus1 : 돌릴 필요 없이 인덱스만 처리해줌 되고 s 뒤에 패딩처럼 앞에 N/4-1개 붙여주면 복잡하게 안해도 됨
 * Plus2 : 구간도 나눌 필요 없이 인덱스 0부터 시작해서 N / 4씩 처리해주면 됨... 생각을 하자ㅠㅠㅠ 문제 그대로 풀 필요 없었다 
 */
public class SW5658_보물상자비밀번호 {
	static int TC, N, K, ans;
	static String s;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			s = in.readLine();
			list = new ArrayList<>();
			for(int i = 0 ; i < N / 4 ; i++) {
				rotate();
				for(int j = 0 ; j < N ; j += N / 4) {
					String hex = s.substring(j, j + N / 4);
					int num = Integer.parseInt(hex, 16);
					if(!list.contains(num)) {
						list.add(num);
					}
				}
			}
			Collections.sort(list);
			ans = list.get(list.size() - K);
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		
		System.out.println(sb);
	}
	private static void rotate() {
		String newS = "";
		for(int i = 1 ; i < N ; i++) {
			newS += s.charAt(i); 
		}
		newS += s.charAt(0);
		s = newS;
	}
}
