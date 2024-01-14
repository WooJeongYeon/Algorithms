#include <stdio.h>
#include <algorithm>
#define SIZE 1000001

using namespace std;

struct Info {
    int val;
    int idx;
};

int N, cnt = -1, lastV = 2 * 10e9;
int ans[SIZE] = {0};
Info arr[SIZE];

bool compare(Info a, Info b) {
    return a.val < b.val;
}

int main() {
    scanf("%d", &N);
    for(int i = 0 ; i < N ; i++) {
        scanf("%d", &arr[i].val);
        arr[i].idx = i;
    }

    sort(arr, arr + N, compare);

    for(int i = 0 ; i < N ; i++) {
        if(arr[i].val != lastV) {
            cnt++;
            lastV = arr[i].val;
        }
        ans[arr[i].idx] = cnt;
    }

    for(int i = 0 ; i < N ; i++) {
        printf("%d ", ans[i]);
    }
    printf("\n");

    return 0;
}