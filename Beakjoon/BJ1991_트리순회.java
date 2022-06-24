package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 배열에 저장하면 2^26개라 안될듯...(한줄로 쭉 나열된 경우)
// LinkedList로 관리해보자...? 이러면 순회하기 어려울거같은데
// 배열로 일단 해보까..? 근데 그러면 찾는데 시간 많이걸릴거같은데... -> 셋팅할 떄는 map쓰자
// 오른쪽 자식으로 되는경우 생각해야 함(2 ^ N - 1)

// sb 세개쓰면 되서 1번만 순회돌면 되구...
// 문자 -> 정수로 바꿔서 인덱스로 쓰고 이차원배열 써서 저장하면 되자나...ㅜㅠㅜㅠㅠㅠ 이렇게 할필요 없어!!!

public class BJ1991_트리순회 {
    static int N;
    static char[] arr;
    static Map<Character, Integer> map;
    static StringBuilder sb;
    static int SIZE;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        SIZE = 1 << N;
        arr = new char[SIZE];
        map = new HashMap<>();
        sb = new StringBuilder();

        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            char parent = st.nextToken().charAt(0);
            char child1 = st.nextToken().charAt(0);
            char child2 = st.nextToken().charAt(0);

            int parentIdx = map.getOrDefault(parent, 1);
            if(parentIdx == 1)
                arr[1] = parent;
            if(child1 != '.') {
                int childIdx1 = parentIdx * 2;
                arr[childIdx1] = child1;
                map.put(child1, childIdx1);
            }
            if(child2 != '.') {
                int childIdx2 = parentIdx * 2 + 1;
                arr[childIdx2] = child2;
                map.put(child2, childIdx2);
            }
        }

        preOrder(1);
        sb.append("\n");
        inOrder(1);
        sb.append("\n");
        postOrder(1);

        System.out.println(sb);

    }

    static void preOrder(int idx) {
        if(arr[idx] == 0)
            return;

        sb.append(arr[idx]);
        if(idx * 2 < SIZE)
            preOrder(idx * 2);
        if(idx * 2 + 1 < SIZE)
            preOrder(idx * 2 + 1);
    }

    static void inOrder(int idx) {
        if(arr[idx] == 0)
            return;


        if(idx * 2 < SIZE)
            inOrder(idx * 2);
        sb.append(arr[idx]);
        if(idx * 2 + 1 < SIZE)
            inOrder(idx * 2 + 1);
    }

    static void postOrder(int idx) {
        if(arr[idx] == 0)
            return;

        if(idx * 2 < SIZE)
            postOrder(idx * 2);
        if(idx * 2 + 1 < SIZE)
            postOrder(idx * 2 + 1);
        sb.append(arr[idx]);
    }
}
