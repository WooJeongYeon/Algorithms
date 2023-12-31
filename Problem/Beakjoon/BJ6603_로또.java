import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 210922
 * Thinking : 조합! 계속 테케 받다가 0나오면 끝. 모든 방법을 사전순으로 출력. 각 테스트케이스 사이에는 빈 줄 하나 출력 
 * Method : 조합. visit배열 사용해서 마지막에 sb에 추가할지 vs 매개변수로 매번 s를 만들어줄지(이거 선택) 이렇게 선택하면 오름차순은 자동으로 됌!
 */
public class BJ6603_로또 {
	static int k;
	static int[] arr;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		sb = new StringBuilder();
		k = Integer.parseInt(st.nextToken());
		while(k != 0) {
			arr = new int[k];
			for(int i = 0 ; i < k ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			comb(0, 0, "");
			
			sb.append("\n");
			st = new StringTokenizer(in.readLine());
			k = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(sb);
	}
	static void comb(int idx, int cnt, String s) {
		if(cnt == 6) {
			sb.append(s + "\n");
			return;
		}
		if(idx == k) {
			return;
		}
		
		comb(idx + 1, cnt + 1, s + arr[idx] + " ");
		comb(idx + 1, cnt, s);
	}
}
