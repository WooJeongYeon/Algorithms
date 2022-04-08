package programmers;

// indexOf 정규표현식 안먹네

import java.util.Arrays;

public class PRO17696_파일명정렬 {
    public String[] solution(String[] files) {
        int N = files.length;
        String[] answer = new String[N];
        File[] fileArr = new File[N];

        for(int i = 0 ; i < N ; i++) {
            String f = files[i];
            String head = "", num = "", tail = "";
            int idx1 = 0, idx2 = f.length();
            for(int j = 0 ; j < f.length() ; j++) {
                if(f.charAt(j) >= '0' && f.charAt(j) <= '9') {
                    idx1 = j;
                    break;
                }
            }
            head = f.substring(0, idx1);
            for(int j = idx1 ; j < f.length() ; j++) {
                if((f.charAt(j) + "").matches("[^0-9]")) {
                    idx2 = j;
                    break;
                }
            }
            num = f.substring(idx1, idx2);
            if(idx2 < f.length()) {
                tail = f.substring(idx2, f.length());
            }

            fileArr[i] = new File(head, num, tail, i);
        }

        Arrays.sort(fileArr);

        for(int i = 0 ; i < N ; i++) {
            File file = fileArr[i];
            answer[i] = file.head + file.num + file.tail;
        }

        return answer;
    }
    class File implements Comparable<File> {
        String head, num, tail;
        int idx;
        public File(String head, String num, String tail, int idx) {
            this.head = head;
            this.num = num;
            this.tail = tail;
            this.idx = idx;
        }
        public int compareTo(File o) {
            if(this.head.toLowerCase().equals(o.head.toLowerCase())) {
                int a = Integer.parseInt(this.num);
                int b = Integer.parseInt(o.num);
                if(a == b) return this.idx - o.idx;
                return a - b;
            }
            return this.head.toLowerCase().compareTo(o.head.toLowerCase());
        }
    }
}
