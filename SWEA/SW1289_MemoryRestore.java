package day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 2021.08.02
 * Level : SWEA D3
 * Method : 배열 
 */
public class SW1289_MemoryRestore {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 0 ; tc < TC ; tc++) {
			String s = in.readLine();
			int change = 0;
			if(s.charAt(0) == '1') change++;
			for(int i = 0 ; i < s.length() - 1 ; i++) {
				if(s.charAt(i) != s.charAt(i + 1)) change++;
			}
			System.out.println("#" + (tc + 1) + " " + change);
		}
		for(int i = 0 ; i < sb.length() ; i++) {
		}
	}
}
