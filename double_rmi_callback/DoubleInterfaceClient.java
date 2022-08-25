import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DoubleInterfaceClient extends Remote {
	public int Result(String val) throws RemoteException;
}
