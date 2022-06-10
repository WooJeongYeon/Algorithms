class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int starCnt = phone_number.length() - 4;
        for(int i = 0 ; i < starCnt ; i++) {
            answer += '*';
        }
        return answer + phone_number.substring(phone_number.length() - 4, phone_number.length());
    }
}