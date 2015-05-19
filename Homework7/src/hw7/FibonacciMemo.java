package hw7;

import hw7.Stopwatch;

import java.security.InvalidParameterException;
import java.util.HashMap; // Import Java's HashMap so we can use it
import java.util.HashSet;
import java.util.TreeMap;

public class FibonacciMemo {

	/**
	 * The classic recursive implementation with no memoization. Don't care
	 * about graceful error catching, we're only interested in performance.
	 * 
	 * @param n
	 * @return The nth fibonacci number
	 */
	public static int fibNoMemo(int n) {
		if (n <= 1) {
			return n;
		}
		return fibNoMemo(n - 2) + fibNoMemo(n - 1);
	}

	/**
	 * Your optimized recursive implementation with memoization. You may assume
	 * that n is non-negative.
	 * 
	 * @param n
	 * @return The nth fibonacci number
	 */
	public static int fibMemo(int n) {
		if (n >= 47) {
			throw new InvalidParameterException();
		}
		// YOUR CODE HERE
		// Check memo
		if (memo.containsKey(n))
			return memo.get(n);

		if (n <= 1) {
			return n;
		}
		int answer = fibMemo(n - 2) + fibMemo(n - 1);
		memo.put(n, answer);
		return answer;
	}

	private static HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();

	/**
	 * Answer the following question as a returned String in this method: Why
	 * does even a correctly implemented fibMemo not return 2,971,215,073 as the
	 * 47th Fibonacci number?
	 */
	public static String why47() {
		String answer = "Because it's larger than the upper limit of the int datatype";
		return answer;
	}

	public static void main(String[] args) {
		// Optional testing here
		String m = "Fibonacci's real name was Leonardo Pisano Bigollo.";
		m += "\n" + "He was the son of a wealthy merchant.\n";
		System.out.println(m);
		for (int i = 0; i < 47; i += 1) {
			System.out.println(i + ": " + FibonacciMemo.fibTimeTrialNoMemo(i)
					+ " No Memo");
			System.out.println(i + ": " + FibonacciMemo.fibTimeTrialMemo(i)
					+ " w/ Memo");
			System.out.println("");
		}
		// 46th Fibonacci = 1,836,311,903
		// 47th Fibonacci = 2,971,215,073
	}

	/**
	 * Returns time needed to put N random strings of length L into the TreeMap
	 * treeMap.
	 */
	public static double fibTimeTrialNoMemo(int N) {
		Stopwatch sw = new Stopwatch();
		System.out.print(" " + FibonacciMemo.fibNoMemo(N) + " ");
		return sw.elapsedTime();
	}

	public static double fibTimeTrialMemo(int N) {
		Stopwatch sw = new Stopwatch();
		System.out.print(" " + FibonacciMemo.fibMemo(N) + " ");
		return sw.elapsedTime();
	}
}
