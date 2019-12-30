
package com.open.capacity.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * forkjoin
 */
@Slf4j
public class Test23 extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private static final int threshold = 5;// 临界值
	private int start;
	private int end;

	public Test23(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * 重写compute方法，判断是否将任务进行拆分计算
	 */
	@Override
	protected Integer compute() {
		int sum = 0;
		log.info("form :{} , to : {} ", start,end);
		boolean canCompute = (end - start) <= threshold ;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			int middle = (start+end) / 2;
			Test23 l = new Test23(start, middle);
			Test23 r = new Test23(middle + 1, end);
			//执行子任务
			l.fork();
			r.fork();
			//合并子任务
			sum = l.join() + r.join();
		}
		return sum;

	}
 

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int start = 1;
		int end= 15;
		
		ForkJoinPool pool = new ForkJoinPool();
		//大任务拆分小任务
		Test23 task = new Test23(start, end);

		Future<Integer> result = pool.submit(task);

		long ret = result.get() ;
		System.out.println("result: " + ret);

		System.out.println("result: " + IntStream.rangeClosed(1, 15).summaryStatistics().getSum());

	}
}
