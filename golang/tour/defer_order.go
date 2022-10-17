package main

import "fmt"

func main() {
	// 先执行 defer 2 再执行 defer 1
	defer fmt.Println("1")
	defer fmt.Println("2")

	fmt.Println("main running")

	b, a := test(10)
	fmt.Println(a, b)
}

func test(x int) (a, b int) {
	defer fmt.Println("123")
	defer fmt.Println("456")

	fmt.Println("test running")

	return 0, 1
}
