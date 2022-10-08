package main

import "sync"

func main() {
	var a string
	// 等待组 等待一系列 goroutine 结束
	var wg sync.WaitGroup
	wg.Add(1)

	// 这是一个闭包，最后面的括号是调用
	go func() {
		a = "Hello World!"
		wg.Done()
	}()

	// 等待 goroutine 完成
	wg.Wait()
	println(a)
}
