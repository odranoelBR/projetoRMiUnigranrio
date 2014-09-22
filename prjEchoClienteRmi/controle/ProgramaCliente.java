package controle;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

public class ProgramaCliente
{
	public static void main(String[] args) throws Exception
	{
		//System.setSecurityManager(new RMISecurityManager());
		String urlServidor = "127.0.0.1";
		String nomeObjRemoto = "MeuEchoRemoto";
		InterfaceEchoRemoto objetoEchoRemoto = (InterfaceEchoRemoto)Naming.lookup("rmi://" + urlServidor + "/" + nomeObjRemoto);
		Scanner leitor = new Scanner(System.in);


		System.out.println("Digite uma operacao (ex.: 1 + 1 * a - f ...):");
		String mensagem = leitor.nextLine();

		System.out.println("Enviando a String: "+ mensagem);
		
		String resultado = objetoEchoRemoto.echo(mensagem);
		System.out.println("Foi recebido: " + resultado + "\n");
	}
}
