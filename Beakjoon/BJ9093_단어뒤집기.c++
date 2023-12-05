#include <stdio.h>
#include <string.h>

int main() {
    int N, len, now;
    char str[1001];
    scanf("%d\n", &N);
    for(int i = 0 ; i < N ; i++) {
        now = 0;
        fgets(str, sizeof(str), stdin);
    
        len = strlen(str);
        for(int j = 0 ; j <= len ; j++) {
            if(str[j] == ' ' || str[j] == '\n') {
                for(int k = 0 ; k < now ; k++) {
                    printf("%c", str[j - k - 1]);
                }
                printf(" ");
                now = -1;
            }
            now++;
        }
        printf("\n");
    }
}