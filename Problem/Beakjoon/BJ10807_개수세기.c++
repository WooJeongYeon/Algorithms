#include <stdio.h>

int main() {
    int N, v, ans = 0;
    int arr[100];
    scanf("%d", &N);
    for(int i = 0 ; i < N ; ++i) {
        scanf("%d", &arr[i]);
    }
    scanf("%d", &v);
    for(int i = 0 ; i < N ; ++i) {
        if(arr[i] == v) {
            ans++;
        }
    }
    printf("%d\n", ans);
}