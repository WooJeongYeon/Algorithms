#include <stdio.h>

int main() {
    int N = 0;
    char s[51];

    scanf("%d",  &N);
    for(int i = 0 ; i < N ; i++) {
        scanf("%s", s);
        int j = 0;
        int cnt = 0;
        while(s[j] != '\0') {
            if(s[j] == '(') {
                cnt++;
            }
            else {
                cnt--;
                if(cnt == -1)
                    break;
            }
            j++;
        }
        if(cnt == 0)
            printf("YES\n");
        else
            printf("NO\n");
    }
}