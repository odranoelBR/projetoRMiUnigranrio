package controle;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceEchoRemoto extends Remote
{
    public String echo(String msg) throws RemoteException;
}

