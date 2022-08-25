import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DoubleInterfaceServer extends Remote {
	public String Double(int value, int port) throws RemoteException;
}
