import java.rmi.*;
import java.rmi.server.*;
public class RMI extends UnicastRemoteObject implements RMIinterface
{
	private String message;
	public RMI (String msg)  throws RemoteException
	{
		message = msg;
	}
	public String say() throws RemoteException
	{
		return message;
	}
}