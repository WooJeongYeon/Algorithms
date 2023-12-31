package day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210812
 */
public class SW6808_CardGame_Boolean {
	static int[] myCards, yourCards, cards;
	static boolean[] selected;
	static int win, lose;
	static final int SIZE = 9;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(in.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		myCards = new int[SIZE];
		yourCards = new int[SIZE];
		cards = new int[SIZE];
		for(int tc = 1 ; tc <= TC ; tc++) {
			selected = new boolean[SIZE];
			st = new StringTokenizer(in.readLine());
			int flag = 0;
			win = 0; lose = 0;
			for(int i = 0 ; i < SIZE ; i++) {
				myCards[i] = Integer.parseInt(st.nextToken());
				flag = flag | 1 << (myCards[i] - 1);
			}
			int idx = 0;
			for(int i = 0 ; i < SIZE * 2 ; i++) {
				if((flag & 1 << i) != 0) continue;
				cards[idx++] = i + 1;
			}
			perm(0);
			sb.append("#" + tc + " " + win + " " + lose + "\n");
		}
		System.out.println(sb);
	}
	static void perm(int idx) {
		if(idx == SIZE) {
			int myScore = 0, yourScore = 0;
			for(int i = 0 ; i < SIZE ; i++) {
				int score = myCards[i] + yourCards[i];
				if(myCards[i] > yourCards[i]) myScore += score;
				else if(myCards[i] < yourCards[i]) yourScore += score;
			}
			if(myScore > yourScore) win++;
			else if(myScore < yourScore) lose++;
			return;
		}
		for(int i = 0 ; i < SIZE ; i++) {
			if(selected[i]) continue;
			yourCards[idx] = cards[i];
			selected[i] = true;
			perm(idx + 1);
			selected[i] = false;
		}
	}
}
