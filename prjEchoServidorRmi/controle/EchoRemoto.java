package controle;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class EchoRemoto extends UnicastRemoteObject implements InterfaceEchoRemoto
{
    public EchoRemoto() throws RemoteException
    {
    }
                          
    public String echo(String msg) throws RemoteException
    {
        
    	String reg = "((?<=[<=|>=|==|\\+|\\*|\\-|<|>|/|=])|(?=[<=|>=|==|\\+|\\*|\\-|<|>|/|=]))";

		ParseadorCalculadora parser = new ParseadorCalculadora();

		System.out.println("Digite sua expressão");
		String[] input = msg .split(reg);
		String[] output = parser.expToRPN(input);
		
		String retorno = parser.RPNtoDouble( output );
		
        return "[ECHO] O Resultado foi :" + String.valueOf(retorno);
    }

    
}
