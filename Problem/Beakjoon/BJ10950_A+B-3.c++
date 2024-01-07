#include <stdio.h>

int main() {
    int tc, A, B;
    scanf("%d", &tc);
    
    for(int t = 0 ; t < tc ; t++) {
        scanf("%d %d", &A, &B);
        printf("%d", A + B);
    }
}