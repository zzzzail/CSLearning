#include <stdio.h>

typedef struct {
    int i;
    char data[20];
    struct CACHE *next;
} CACHE;
CACHE MY_CACHE;
void apply(int n);
void printCache(CACHE *c);
int main() {
    apply(1);
    printCache(&MY_CACHE);
    return 0;
}
void apply(int n) {
    printf("申请第%d页面\n", n);
    if (NULL == MY_CACHE) { // 缓存中没有页面可以直接分配
        MY_CACHE.i = n; 
        *MY_CACHE.data = "到内存取到的数据";
        return;
    } 
    if (MY_CACHE.i == n) return; // 如果申请的缓存在第一个
    
    CACHE *pp = &MY_CACHE;
    CACHE *cp = &MY_CACHE;
    while ((cp = cp->next) != NULL) {
        if (cp->i == n) { // 缓存中有数据
            CACHE *t = cp->next;
            cp->next = &MY_CACHE;
            MY_CACHE = *cp;
            pp->next = t;
        }
        pp = cp;
    }
    if (MY_CACHE.i != n) { // 说明缓存中没有
        CACHE root = {n, "到内存取到的数据", NULL};
        root.next = &MY_CACHE;
        MY_CACHE = root;
    }
}
void printCache(CACHE *c) {
    CACHE *cp = c;
    while(NULL != cp) {
        printf("第%d个页面\n", cp->i);
        cp = cp->next;
    }
}