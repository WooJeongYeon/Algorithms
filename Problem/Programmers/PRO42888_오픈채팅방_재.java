import java.util.HashMap;
import java.util.Map;
public class PRO42888_오픈채팅방_재 {
	class Solution {
	    public String[] solution(String[] record) {
	        Map<String, String> map = new HashMap<>();
	        int size = record.length;
	        for(int i = 0 ; i < record.length ; i++) {
	            if(record[i].charAt(0) == 'C') size--;
	            if(record[i].charAt(0) == 'L')  continue;
	            String id = record[i].split(" ")[1];
	            String name = record[i].split(" ")[2];
	            map.put(id, name);
	        }
	        String[] answer = new String[size];
	        int idx = 0;
	        for(int i = 0 ; i < record.length ; i++) {
	            String id = record[i].split(" ")[1];
	            if(record[i].charAt(0) == 'E') {
	                answer[idx++] = map.get(id) + "님이 들어왔습니다.";
	            }
	            if(record[i].charAt(0) == 'L') {
	                answer[idx++] = map.get(id) + "님이 나갔습니다.";
	            }
	        }
	        return answer;
	    }
	}
}
