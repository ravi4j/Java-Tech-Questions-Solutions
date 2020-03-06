package com.rs.tech.questions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BalanceBracketsSolution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BalanceBrackets balanceBrackets = new BalanceBrackets();
		int t = in.nextInt();
		for (int a0 = 0; a0 < t; a0++) {
			String s = in.next();
			System.out.println(balanceBrackets.isBalanced(s));
		}
	}
}

class BalanceBrackets {

	private static final String OPENING_BRACKETS = "([{";
	private static final String CLOSING_BRACKETS = ")]}";

	public String isBalanced(String input) {

		return _isBalanced(input) ? "YES" : "NO";
	}

	private boolean _isBalanced(String input) {

		Deque<Character> stack = new ArrayDeque<>();

		if (input == null || input.isEmpty()) {
			throw new RuntimeException("Input is null or empty");
		}

		char[] brackets = input.toCharArray();

		for (int i = 0; i < brackets.length; i++) {
			char bracket = brackets[i];
			if (_isOpeningBracket(bracket)) {
				stack.push(Character.valueOf(bracket));
				continue;
			}

			if (_isClosingBracket(bracket)) {
				if (stack.isEmpty())
					return false;
				char top = stack.peek().charValue();
				if (_isOpeningBracket(top)) {
					if (OPENING_BRACKETS.indexOf(top) != CLOSING_BRACKETS.indexOf(bracket)) {
						return false;
					} else {
						stack.pop();
					}
				} else {
					return false;
				}
			}
		}

		if (stack.size() != 0)
			return false;

		return true;
	}

	private boolean _isOpeningBracket(char ch) {
		return OPENING_BRACKETS.indexOf(ch) != -1;
	}

	private boolean _isClosingBracket(char ch) {
		return CLOSING_BRACKETS.indexOf(ch) != -1;
	}
}
