package controle;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;

public class ProgramaServidor 
{
	public static void main(String[] args) throws Exception
    {
		LocateRegistry.createRegistry(1099);
        //System.setSecurityManager(new RMISecurityManager());
        EchoRemoto er = new EchoRemoto();
        Naming.rebind("MeuEchoRemoto", er);
        System.out.println("Pronto para operação!");

    }

}
