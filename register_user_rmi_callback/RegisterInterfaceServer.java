import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterInterfaceServer extends Remote {
 public int register() throws RemoteException;
 public List<int> list_query(int user) throws RemoteException;
 public String id_query(int user, int id) throws RemoteException;
}
