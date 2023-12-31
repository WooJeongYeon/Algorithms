package day0819;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * Date : 210819
 */
public class BJ1406_Editor {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String s = in.readLine();
		List list = new List();
		list.insert(list.top, new Node(s.charAt(0)));
		Node point = list.top;	

		for(int i = 1 ; i < s.length() ; i++) {
			list.insert(point, new Node(s.charAt(i)));
			point = point.right;				
		}
		list.insert(point, new Node('0'));		// 마지막 노드 추가(이전 노드를 삭제 또는 삽입하므로)
		point = point.right;					// 마지막 노드를 가리키도록
		int M = Integer.parseInt(in.readLine());
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(in.readLine());
			char command = st.nextToken().charAt(0);
			switch(command) {
				case 'L' :
					if(point.left != null) point = point.left;
					break;
				case 'D' :
					if(point.right != null) point = point.right;
					break;
				case 'B' :
					if(point != list.top) {
						list.remove(point.left);
					}
					break;
				case 'P' :
					char c = st.nextToken().charAt(0);
					list.insert(point.left, new Node(c));
					break;
			}
//			System.out.println("complete: " + command + " " + point.c);
		}
		for(Node n = list.top ; n != null ; n = n.right) {
			//System.out.println(n.c);
			sb.append(n.c);
		}
		if(list.top != null) sb = sb.deleteCharAt(sb.length() - 1);		// 잘못 들어간 마지막 노드 삭제
		System.out.println(sb);
	}
	static class Node {
		Node left;
		Node right;
		char c;
		public Node(char c) {
			this.c = c;
		}
	}
	static class List {
		Node top;
		List() {
			top = null;
		}
		void insert(Node last, Node n) {	// 이전 노드가 주어졌을 때, 다음 노드 삭제
			if(top == null) top = n;
			else if(last == null) {
				
				n.right = top;
				top.left = n;
				top = n;
			}
			else if(last.right == null) {
				last.right = n;
				n.left = last;
			}
			else {
				n.left = last;
				n.right = last.right;
				last.right.left = n;
				last.right = n;
			}
		}
		void remove(Node n) {	// 해당 요소 삭제
			Node now = n;
			if(now.left == null) {
				top = n.right;
				if(top != null)
					top.left = null;
			}
			else if(n.right == null){
				now.left.right = null;
			} else {
				now.left.right = n.right;
				now.right.left = n.left;
			}
			n = null;
		}
		void print() {
			for(Node n = top ; n != null ; n = n.right) {
				//System.out.println(n.c);
				System.out.print(n.c + " ");
			}
			System.out.println();
		}
	}
}