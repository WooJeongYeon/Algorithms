#include <stdio.h>
#define SIZE 1000001

int main() {
    int N, M, cnt = 0, len = 0;
    bool val = 1;
    char str[SIZE];

    scanf("%d\n%d\n%s", &N, &M, str);

    N = N * 2 + 1;

    for(int i = 0 ; i < M ; i++) {
        bool b = str[i] == 'I' ? 1 : 0;
        if(b == val) {
            val = !val;
            len++;
            if(b && N <= len) {
                cnt++;
            }
        } else {
            if(b) {
                val = 0;
                len = 1;
            } else {
                val = 1;
                len = 0;
            }
        }
    }

    printf("%d\n", cnt);
}