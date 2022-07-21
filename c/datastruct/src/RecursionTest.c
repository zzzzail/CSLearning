// Fibonacci 斐波那锲数列
long int fact(int n) {
    if (n == 0) return 1;
    else return fact(n - 1) * fact(n - 2);
}

// 例3.4
float fu(int n) {
    if (n < 2)
        return (float) (n + 1);
    else
        return fu(n / 2) * fu(n / 4);
}