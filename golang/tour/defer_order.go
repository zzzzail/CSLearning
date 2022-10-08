package main

import "fmt"

func main() {
	// 先执行 defer 2 再执行 defer 1
	defer fmt.Println("1")
	defer fmt.Println("2")

	fmt.Println("main running")
}
