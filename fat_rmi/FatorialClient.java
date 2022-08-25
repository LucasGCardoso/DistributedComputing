import java.rmi.Naming;
import java.rmi.RemoteException;

public class FatorialClient {
	public static void main(String[] args) {
		int result;

		if (args.length != 1) {
			System.out.println("Usage: java FatorialClient <machine>");
			System.exit(1);
		}

		String connectLocation = "rmi://" + args[0] + ":52369/Hello";

		FatorialInterface hello = null;
		try {
			System.out.println("Connecting to client at : " + connectLocation);
			hello = (FatorialInterface) Naming.lookup(connectLocation);
		} catch (Exception e) {
			System.out.println ("FatorialClient failed: ");
			e.printStackTrace();
		}

		try {
			result = hello.obtemFatorial(3);
			System.out.println("Result is: " + result);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
