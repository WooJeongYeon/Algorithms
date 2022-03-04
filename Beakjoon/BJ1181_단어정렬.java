package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1181_단어정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        int N = Integer.parseInt(in.readLine());
        for(int i = 0 ; i < N ; i++) {
            set.add(in.readLine());
        }
        for(String s : set) {
            list.add(s);
        }
        Collections.sort(list, (o1, o2) -> {
            if(o1.length() == o2.length()) return o1.compareTo(o2);
            return o1.length() - o2.length();
        });
        for(String s : list) {
            sb.append(s + "\n");
        }
        System.out.println(sb.toString());
    }
}
