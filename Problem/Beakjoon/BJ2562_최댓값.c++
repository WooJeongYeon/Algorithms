#include <stdio.h>

int main() {
    int x, max = 0, idx;

    for(int i = 1 ; i <= 9 ; i++) {
        scanf("%d", &x);
        if(x > max) {
            max = x;
            idx = i;
        }
    }

    printf("%d\n%d\n", max, idx);

    return 0;
}