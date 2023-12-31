package day0812;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * Date : 210812
 */
public class BJ15828_Router {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		final int N = in.nextInt();
		Queue<Integer> queue = new LinkedList<Integer>();	// 큐 생성
		while(true) {	// -1이 입력될 때까지 무한반복
			int n = in.nextInt();
			if(n > 0 && queue.size() < N) queue.add(n); // n이 양수이고, 버퍼에 여유가 있다면 요소를 더함
			else if(n == 0) queue.poll();		// 0이면 큐에서 요소를 꺼냄
			else if(n == -1) break;	// -1이면 중단
		}
		if(queue.isEmpty()) sb.append("empty");	// 비었으면 empty 출력
		else
			while(!queue.isEmpty()) 	// 아니라면 남아있는 요소들을 모두 꺼내서 출력
				sb.append(queue.poll() + " ");
		System.out.println(sb);
	}
}
