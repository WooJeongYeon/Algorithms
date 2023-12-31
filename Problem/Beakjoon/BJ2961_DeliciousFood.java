package day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210812
 */
public class BJ2961_DeliciousFood {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int N = Integer.parseInt(in.readLine());
		int[] sours = new int[N];
		int[] bitters = new int[N];
		StringTokenizer st;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			sours[i] = Integer.parseInt(st.nextToken());
			bitters[i] = Integer.parseInt(st.nextToken());
		}
		int minSub = Integer.MAX_VALUE;			// 최댓값 저장해둠
		for(int i = 1 ; i < 1 << N ; i++) {		// binary counting 사용한 부분집합
			int sour = 1;
			int bitter = 0;
			for(int j = 0 ; j < N ; j++) {
				if((i & 1 << j) != 0) {
					sour *= sours[j];
					bitter += bitters[j];
				}
			}
			int sub = Math.abs(sour - bitter);
			if(minSub > sub) minSub = sub;
		}
		System.out.println(minSub);
	}
}
