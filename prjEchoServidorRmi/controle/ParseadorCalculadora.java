package controle;
import java.util.*;
public class ParseadorCalculadora
{
	// Associativity constants for operators
	private static final int LEFT_ASSOC = 0;
	private static final int RIGHT_ASSOC = 1;
	// Operators
	private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
	static
	{
		// Map<"token", []{precendence, associativity}>
		OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });
		OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });
		OPERATORS.put("*", new int[] { 5, LEFT_ASSOC });
		OPERATORS.put("/", new int[] { 5, LEFT_ASSOC });
	}
	// Test if token is an operator
	private static boolean isOperator(String token)
	{
		return OPERATORS.containsKey(token);
	}
	// Test associativity of operator token
	private static boolean isAssociative(String token, int type)
	{
		if (!isOperator(token))
		{
			throw new IllegalArgumentException("Invalid token: " + token);
		}
		if (OPERATORS.get(token)[1] == type) {
			return true;
		}
		return false;
	}
	// Compare precedence of operators.
	private static final int cmpPrecedence(String token1, String token2)
	{
		if (!isOperator(token1) || !isOperator(token2))
		{
			throw new IllegalArgumentException("Invalid tokens: " + token1
					+ " " + token2);
		}
		return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
	}
	// Convert infix expression format into reverse Polish notation
	public static String[] expToRPN(String[] inputTokens)
	{
		ArrayList<String> out = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		// For each token
		for (String token : inputTokens)
		{
			// If token is an operator
			if (isOperator(token))
			{
				// While stack not empty AND stack top element
				// is an operator
				while (!stack.empty() && isOperator(stack.peek()))
				{
					if ((isAssociative(token, LEFT_ASSOC) &&
							cmpPrecedence(token, stack.peek()) <= 0) ||
							(isAssociative(token, RIGHT_ASSOC) &&
									cmpPrecedence(token, stack.peek()) < 0))
					{
						out.add(stack.pop());
						continue;
					}
					break;
				}
				// Push the new operator on the stack
				stack.push(token);
			}
			// If token is a left bracket '('
			else if (token.equals("("))
			{
				stack.push(token); //
			}
			// If token is a right bracket ')'
			else if (token.equals(")"))
			{
				while (!stack.empty() && !stack.peek().equals("("))
				{
					out.add(stack.pop());
				}
				stack.pop();
			}
			// If token is a number
			else
			{
				// if(!isOperator(stack.peek())){
				// out.add(String.valueOf(token*10));
				// }
				out.add(token);
			}
		}
		while (!stack.empty())
		{
			out.add(stack.pop());
		}
		String[] output = new String[out.size()];
		return out.toArray(output);
	}
	
	public static String converteDecimalParaHexadecimal(Integer valor) {
		   char[] hexa = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		   int resto = -1;
		   StringBuilder sb = new StringBuilder();
		 
		   if (valor == 0) {
		      return "0";
		   }
		 
		   // enquanto o resultado da divisão por 16 for maior que 0 adiciona o resto ao início da String de retorno
		   while (valor > 0) {
		      resto = valor % 16;
		      valor = valor / 16;
		      sb.insert(0, hexa[resto]);
		   }
		 
		   return sb.toString();
		}
	
	public static String RPNtoDouble(String[] tokens)
	{
		Stack<String> stack = new Stack<String>();
		// For each token
		for (String token : tokens) //for each
		{
			// If the token is a value push it onto the stack
			if (!isOperator(token))
			{
				if (token.matches("^[a-f]")){
					switch (token) {
					case "a": token = "10" ;break;
					case "b": token = "11" ;break;
					case "c": token = "12" ;break;
					case "d": token = "13" ;break;
					case "e": token = "14" ;break;
					case "f": token = "15" ;break;

					default:
						break;
					}
				}
				
				stack.push(token);
			}
			else
			{
				// Token is an operator: pop top two entries
				Integer d2 = Integer.valueOf( stack.pop() );
				Integer d1 = Integer.valueOf( stack.pop() );
				
				//Get the result
				Integer result = token.compareTo("*") == 0 ? d1 * d2 :
					token.compareTo("/") == 0 ? d1 / d2 :
						token.compareTo("+") == 0 ? d1 + d2 :
							d1 - d2;
				// Push result onto stack
				stack.push( String.valueOf( result ));
			}
		}
		
		return converteDecimalParaHexadecimal(Integer.valueOf(stack.pop()));
//		return Double.valueOf(stack.pop());
	}
} 