// 거의 피보나치의 수 급인디... 예전에 타일놓기 비슷한거 한적이씀
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210909
 */
public class BJ1904_01타일 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		final int NUM = 15746;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[2];
		int result = 0;
		arr[0] = 1;
		arr[1] = 2;
		for(int i = 3 ; i <= N ; i++) {
			result = (arr[0] + arr[1]) % NUM;
			arr[0] = arr[1];
			arr[1] = result;
		}
		if(N <= 2) result = arr[N - 1];
		System.out.println(result);
		
	}
}
