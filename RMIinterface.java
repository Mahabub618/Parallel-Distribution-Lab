import java.rmi.*;

public  interface RMIinterface extends Remote
{
	public String say() throws RemoteException;
	
}