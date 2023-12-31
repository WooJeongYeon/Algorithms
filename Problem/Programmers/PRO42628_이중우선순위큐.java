package programmers;
import java.util.*;

public class PRO42628_이중우선순위큐 {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        ArrayList<Integer> list = new ArrayList<>();
        for(String op : operations) {
            String[] split = op.split(" ");
            if(split[0].equals("I")) {
                list.add(Integer.parseInt(split[1]));
                Collections.sort(list);
                continue;
            }
            if(list.isEmpty()) continue;
            if(Integer.parseInt(split[1]) == 1) {
                list.remove(list.size() - 1);
            } else {
                list.remove(0);
            }
        }

        if(!list.isEmpty()) {
            answer[0] = list.get(list.size() - 1);
            answer[1] = list.get(0);
        }
        return answer;
    }
}
