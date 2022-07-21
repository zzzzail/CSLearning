package com.bfxy.rapid.rpc.spring.test;

import com.bfxy.rapid.rpc.spring.annotation.Rapid;

@Rapid(name = "userService")
public class UserService {

	public void test() {
		System.err.println("user test...");
	}
}
