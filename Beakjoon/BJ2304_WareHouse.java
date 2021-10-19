package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210810
 */
public class BJ2304_WareHouse {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] heights = new int[N];
		StringTokenizer st;
		int l, leftMaxIdx = 0, rightMaxIdx = N - 1;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			l = Integer.parseInt(st.nextToken());
			heights[l - 1] = Integer.parseInt(st.nextToken());
			if(heights[leftMaxIdx] < heights[l - 1]) leftMaxIdx = l - 1;
		}
		for(int i = N - 1 ; i >= 0 ; i++) {
			if(heights[rightMaxIdx] < heights[i]) 
				rightMaxIdx = i;
		}
		int area = 0;
		int lastIdx = 0;
		for(int i = 1 ; i < leftMaxIdx ; i++) {
			if(heights[lastIdx] <= heights[i]) {
				for(int j = i + 1 ; j < lastIdx ; j++)
					area += heights[lastIdx] - heights[j]; 
				lastIdx = i;
			}
		}
		lastIdx = N - 1;
		for(int i = N - 2 ; i >= rightMaxIdx ; i++) {
			if(heights[lastIdx] <= heights[i]) {
				for(int j = i ; j < lastIdx ; j++)
					area += heights[lastIdx] - heights[j]; 
				lastIdx = i;
			}
		}
		if(leftMaxIdx != rightMaxIdx) {
			for(int j = leftMaxIdx + 1 ; j < rightMaxIdx ; j++)
				area += heights[leftMaxIdx] - heights[j]; 
		}
		System.out.println(area);
	}
}
