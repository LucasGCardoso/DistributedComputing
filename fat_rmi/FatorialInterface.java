import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FatorialInterface extends Remote {
 public int obtemFatorial(int valor) throws RemoteException;
}