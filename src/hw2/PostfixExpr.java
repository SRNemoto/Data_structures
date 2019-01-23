package hw2;

public class PostfixExpr {

	public static void main(String[] args) {
		char test = '3';
		char test2 = '+';
		int sum = Character.getNumericValue(test) + Character.getNumericValue(test2);
		System.out.println(sum + "");
	}

	public static int postfixExpr(String expr) {
		MyIntStack stack = new MyIntStack();

		for (int i = 1; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (Character.getNumericValue(expr.charAt(i)) > 0)
				stack.push(Character.getNumericValue(expr.charAt(i)));
			else {
				switch (ch) {
				case '*':
					stack.push(stack.pop() * stack.pop());
					break;
				case '/':
					int divisor = stack.pop();
					int dividend = stack.pop();
					stack.push(dividend / divisor);
					break;
				case '+':
					stack.push(stack.pop() + stack.pop());
					break;
				case '-':
					int subtrahend = stack.pop();
					int minuend = stack.pop();
					stack.push(minuend - subtrahend);
					break;
				}
			}

		}
		return 0;
	}
}
