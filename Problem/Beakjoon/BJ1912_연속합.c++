#include <stdio.h>

int main() {
    int n, last = 0, now = 0, max = -2.1e9;
    
    scanf("%d", &n);

    for(int i = 0 ; i < n ; i++) {
        scanf("%d", &now);
        last = last > 0 ? last + now : now;
        max = last > max ? last : max;
    }

    printf("%d", max);

    return 0;
}