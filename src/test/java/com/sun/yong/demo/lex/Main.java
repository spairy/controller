package com.sun.yong.demo.lex;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		FibonacciGenerater generater = new LoopFibonacciGenerater();
		generater.generate(10);
		
		generater = new RecursiveFibonacciGenerator();
		generater.generate(10);
		
		generater  = new DynamicRecursiveFibonacciGenerator();
		generater.generate(10);
		
		/*Writer writer = null;
		try {
			writer = new FileWriter("fibonacci.txt");
			writer.write("Hello world");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}*/
	}
}

interface FibonacciGenerater {
	static final long A = 0;
	static final long B = 1;
	void generate(int last);
}

class LoopFibonacciGenerater implements FibonacciGenerater {

	@Override
	public void generate(int last) {
		if (last < 0) {
			throw new IllegalArgumentException();
		}
		
		if (last >= 0) {
			System.out.println(A);
		}
		
		if (last >= 1) {
			System.out.println(B);
		}
		
		long a = A;
		long b = B;
		long current;
		for (int i = 2; i <= last; i++) {
			current = a + b;
			System.out.println(current);
			
			a = b;
			b = current;
		}
	}
	
}

class RecursiveFibonacciGenerator implements FibonacciGenerater {

	@Override
	public void generate(int last) {
		if (last < 0) {
			throw new IllegalArgumentException();
		}
		
		for (int i = 0; i <= last; i++) {
			long num = calculate(i);
			System.out.println(num);
		}
	}
	
	private long calculate(int index) {
		System.out.println("Log: calculate Fibonacci index: " + index);
		if (index == 0) {
			return A;
		} else if (index == 1) {
			return B;
		} else {
			return calculate(index -2) + calculate(index - 1);
		}
	}
}

class DynamicRecursiveFibonacciGenerator implements FibonacciGenerater {

	private List<Long> fibonacciList = new ArrayList<>();
	
	@Override
	public void generate(int last) {
		if (last < 0) {
			throw new IllegalArgumentException();
		}
		
		/*for (int i = 0; i <= last; i++) {
			long num = calculate(i);
			//System.out.println(num);
		}*/
		calculate(last);
	}
	
	private long calculate(int index) {
		//System.out.println("Log: calculate dynamic Fibonacci index: " + index);
		if (index == 0) {
			if (fibonacciList.size() < 1) {
				fibonacciList.add(A);
				System.out.println(A);
			}
		} else if (index == 1) {
			if (fibonacciList.size() < 2) {
				calculate(0);
				fibonacciList.add(B);
				System.out.println(B);
			}
		} else {
			if (fibonacciList.size() < index + 1) {
				long a = calculate(index - 2);
				long b = calculate(index - 1);
				fibonacciList.add(a + b);
				System.out.println(a+b);
			}
		}
		return fibonacciList.get(index);
	}
}
