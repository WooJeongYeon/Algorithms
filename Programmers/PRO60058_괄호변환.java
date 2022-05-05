package programmers;


public class PRO60058_괄호변환 {
    String ans;
    public String solution(String p) {
        ans = "";
        ans = recursive(p);
        return ans;
    }
    String recursive(String p) {
        if(p.equals("")) return p;
        int idx = getBalanceIdx(p);
        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1, p.length());
        if(isCorrect(u)) {
            return u + recursive(v);
        }

        String s = "(" + recursive(v) + ")";
        for(int i = 1 ; i < u.length() - 1 ; i++) {
            s += ((u.charAt(i) == '(') ? ')' : '(');
        }
        return s;
    }
    int getBalanceIdx(String s) {
        int cnt = 0;
        for(int i = 0 ; i < s.length() ; i++) {
            if(s.charAt(i) == '(') cnt++;
            else cnt--;
            if(cnt == 0) return i;
        }
        return -1;
    }
    boolean isCorrect(String s) {
        int cnt = 0;
        for(int i = 0 ; i < s.length() ; i++) {
            if(s.charAt(i) == '(') cnt++;
            else cnt--;
            if(cnt < 0) return false;
        }
        if(cnt == 0) return true;
        else return false;
    }
}
