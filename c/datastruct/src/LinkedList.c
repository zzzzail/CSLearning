#include <stdio.h>
#include <stdlib.h>
#include "LinkedList.h"

LinkedList MergeList(LinkedList la, LinkedList lb) {
    LinkedList lc;
    ListNode *pa, *pb, *pc;
    pa = la->next;
    pb = lb->next;
    lc = pc = pa;
    while (pa != NULL && pb != NULL) {
        if (pa->data <= pb->data) {
            pc->next = pa;
            pc = pc->next;
            pa = pa->next;
        } else {
            pc->next = pb;
            pc = pc->next;
            pb = pb->next;
        }
    }
    pc->next = pa != NULL ? pa : pb; // 插入链表剩余部分
    free(lb); // 释放lb的头结点

    return lc;
}