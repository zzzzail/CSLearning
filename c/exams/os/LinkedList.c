#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct node {
    char c;
    struct node *next;
} ListNode;
typedef ListNode *LinkedList;
void printLinkedList(LinkedList head) {
    LinkedList cur = head->next;
    while (cur != NULL) {
        printf("%c", cur->c);
        cur = cur->next;
    }
    printf("\n");
}
int main () {
    // 指针使用前必须初始化！！！！
    ListNode *h = (ListNode *) malloc(sizeof(ListNode));
    h->c = '\0';
    h->next = NULL;
    LinkedList head = h;
    LinkedList cur = head;
    // char str[100] = {"abcdef"};
    int N = 100;
    char str[N];
    gets(str);
    for (int i = 0; i < N; ++i) {
        char ch = str[i];
        if (ch == '\0') break;
        ListNode *h = (ListNode *) malloc(sizeof(ListNode));
        h->c = ch;
        h->next = NULL;
        cur->next = h;
        cur = cur->next;
    }
    printLinkedList(head);

    return 0;
}