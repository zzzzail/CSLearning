#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {

    // char ch1, ch2;
    // scanf("%3c%3c", &ch1, &ch2);
    // printf("ch1=%c;ch2=%c\n", ch1, ch2);

    // // 字符指针
    // char *p;
    // p = "hahahhah";
    // puts(p);

    // // 这是字符数组
    // char arr[80] = {"hahahah"};
    // puts(arr);

    FILE *fp; 
    if ( (fp = fopen("./a.txt", "rw")) == NULL ) {
        printf("Cannot open this file\n");
        exit(1);
    }
    // char ch;
    // while ( (ch = getchar()) != '#' ) {
    //     fputc(ch, fp);
    //     putchar(ch);
    // }

    // while ( (ch = fgetc(fp)) != EOF ) {
    //     putchar(ch);
    // }

    char cp[5];
    while ( (fgets(cp, 5, fp)) != NULL ) {
        puts(cp);
    }

    // 关闭文件流
    fclose(fp);
    return 0;
}