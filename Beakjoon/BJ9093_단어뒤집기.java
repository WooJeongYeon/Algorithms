import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 2021.12.12
 * Level : BaekJoon Bronze 1
 * URL : https://www.acmicpc.net/problem/9093
 * Method : 문자열
 * Result : 스택으로 풀기도 하네
 */
public class BJ9093_단어뒤집기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] arr;
		int TC = Integer.parseInt(in.readLine());
		for(int tc = 0 ; tc < TC ; tc++) {
			arr = in.readLine().split(" ");
			for(int i = 0 ; i < arr.length ; i++) {
				String s = "";
				for(int j = arr[i].length() - 1 ; j >= 0; j--) {
					s += arr[i].charAt(j);
				}
				sb.append(s + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
