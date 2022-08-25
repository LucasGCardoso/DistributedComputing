import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DoubleInterfaceClient extends Remote {
	public int register_result(String val) throws RemoteException;
}
