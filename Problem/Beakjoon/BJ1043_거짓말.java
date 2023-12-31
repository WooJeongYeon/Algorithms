package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 이걸 union-find(서로소 집합)로 으로 푸네
public class BJ1043_거짓말 {
    static int N, M, trueN, ans;
    static ArrayList<Integer>[] partyList;
    static boolean[] trueParty, truePerson;
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trueParty = new boolean[M];
        truePerson = new boolean[N];
        partyList = new ArrayList[M];
        for(int i = 0 ; i < M ; i++) {
            partyList[i] = new ArrayList<>();
        }
        queue = new LinkedList<>();
        st = new StringTokenizer(in.readLine(), " ");
        trueN = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < trueN ; i++) {
            int num = Integer.parseInt(st.nextToken()) - 1;
            truePerson[num] = true;
            queue.offer(num);
        }
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < num ; j++) {
                partyList[i].add(Integer.parseInt(st.nextToken()) - 1);
            }

        }
        while(!queue.isEmpty()) {
            int num = queue.poll();
            for(int i = 0 ; i < M ; i++) {
                if(!trueParty[i] && partyList[i].contains(num)) {
                    trueParty[i] = true;
                    for(Integer now : partyList[i]) {
                        if(!truePerson[now]) {
                            truePerson[now] = true;
                            queue.offer(now);
                        }
                    }
                }
            }
        }

        for(int i = 0 ; i < M ; i++) {
            if(!trueParty[i]){
                ans++;
            }
        }

        System.out.println(ans);
    }
}
