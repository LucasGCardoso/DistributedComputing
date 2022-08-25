import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Fatorial extends UnicastRemoteObject implements FatorialInterface {
	private static final long serialVersionUID = 1L;

	public Fatorial() throws RemoteException {
	}

	public int obtemFatorial(int a) {
		if(a == 1){
            return 1;
        }
        return a*obtemFatorial(a-1);
	}
}
