#include <stdio.h>
#define SIZE 100001
#define abs(x) (x > 0 ? x : -x)  // 이거 안묶으니까 계속 이상하게 됬었는데...??? -> #define은 전처리기가 단순 치환하므로 예상과 다른 결과값이 나올 수 있음
#define swap(a, b) {int tmp = a; a = b; b = tmp;}


int heap[SIZE];
int size = 0, N, x;

void push(int n) {
    heap[++size] = n;
    int idx = size;
    while(idx / 2 > 0) {
        int parent = idx / 2;
        if((abs(heap[parent]) > abs(heap[idx])) || (abs(heap[parent]) == abs(heap[idx]) && heap[parent] > heap[idx])) {
            bool result = false;
            if(abs(heap[parent]) > abs(heap[idx])) result = true; else result = false;
            swap(heap[parent], heap[idx]);
        } else {
            break;
        }
        idx /= 2;
    }
}

int pop() {
    if(size == 0) {
        return 0;
    }
    int ans = heap[1];
    heap[1] = heap[size];
    heap[size--] = 0;
    int idx = 1;

    while(idx * 2 <= size) {
        int left = idx * 2;
        int right = idx * 2 + 1;
        int minV = heap[idx];
        int nextIdx;
        if(left <= size) {
            if(abs(heap[left]) < abs(minV) || (abs(heap[left]) == abs(minV) && heap[left] < minV)) {
                minV = heap[left];
                nextIdx = left;
            }
        }
        if(right <= size) {
            if(abs(heap[right]) < abs(minV) || (abs(heap[right]) == abs(minV) && heap[right] < minV)) {
                minV = heap[right];
                nextIdx = right;
            }
        }
        if(minV == heap[idx]) {
            break;
        }
        swap(heap[idx], heap[nextIdx]);
        idx = nextIdx;
    }

    return ans;
}

int main() {
    scanf("%d", &N);
    for(int i = 0 ; i < N ; i++) {
        scanf("%d", &x);
        if(x == 0) {
            printf("%d\n", pop());
        } else {
            push(x);
        }
    }
}