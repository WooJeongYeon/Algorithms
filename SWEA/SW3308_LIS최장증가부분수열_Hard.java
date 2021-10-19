import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * Date : 210916
 */
public class SW3308_LIS최장증가부분수열_Hard {
	static int TC;
	static int N;
	static int[] arr;
	static ArrayList<Integer> lis;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(bf.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			N = Integer.parseInt(bf.readLine());
			arr = new int[N];								// 모든 원소의 값은 다르다
			lis = new ArrayList<>();					
			st = new StringTokenizer(bf.readLine());
			for(int i = 0 ; i < N ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0 ; i < N ; i++) {
				// 중복값이 없으므로 탐색 실패 : 음수값 ==> 삽입위치로 환산
				int idx = -(Collections.binarySearch(lis, arr[i]) + 1);
				if(idx >= lis.size()) lis.add(arr[i]);		// 추가된 위치가 맨 뒤라면 뒤에 추가
				else {										// 이미 적합한 자리를 구했으므로 그냥 set만 하면 됨!
					lis.set(idx, arr[i]);
				}
			}
			
			sb.append("#" + tc + " " + lis.size() + "\n");
		}
		System.out.println(sb);
	}
}
