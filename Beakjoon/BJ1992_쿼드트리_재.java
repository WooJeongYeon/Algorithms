import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Date : 2021.10.08(재)
 * Level : BaekJoon 실버 5
 * Why : 분할정복 한지 넘 오래되서 기억이 잘 안나서
 * Result : 저번 거랑 비슷한데 좀 더 합쳐지고 재귀부분에서 for문 대신 쓴거?랑 String 반환하는 정도 다르네. ㄱㅊㄱㅊ
 */
public class BJ1992_쿼드트리_재 {
	static int N;
	static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new char[N][];
		for(int i = 0 ; i < N ; i++) {
			map[i] = in.readLine().toCharArray(); 
		}
		
		System.out.println(makeTree(0, 0, N));
	}
	static String makeTree(int i, int j, int n) {
		int cnt = getSum(i, j, n);
		if(cnt == n * n || cnt == 0) {
			return map[i][j] + "";
		}
		String s = "(";
		s += makeTree(i, j, n / 2);
		s += makeTree(i, j + n / 2, n / 2);
		s += makeTree(i + n / 2, j, n / 2);
		s += makeTree(i + n / 2, j + n / 2, n / 2);
		s += ")";
		return s;
	}
	static int getSum(int i, int j, int n) {
		int sum = 0;
		
		for(int r = 0 ; r < n ; r++) {
			for(int c = 0 ; c < n ; c++) {
				sum += map[i + r][j + c] - '0';
			}
		}
		
		return sum;
	}
	
}
