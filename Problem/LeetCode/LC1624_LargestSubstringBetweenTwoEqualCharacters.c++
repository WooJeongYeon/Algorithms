class Solution {
public:
    int maxLengthBetweenEqualCharacters(string s) {
        const int SIZE = 27;
        bool isUse[SIZE];
        int ans = -1;
        for(int i = 0 ; i < SIZE ; i++) {
            isUse[i] = false;
        }

        for(int i = 0 ; i < s.length() ; i++) {
            if(isUse[s[i] - 'a'])
                continue;
            for(int j = s.length() - 1 ; j > i ; j--) {
                if(s[i] == s[j]) {
                    ans = (j - i - 1) > ans ? (j - i - 1) : ans;
                    isUse[s[i] - 'a'] = true;
                    break;
                }
            }
        }

        return ans;
    }
};