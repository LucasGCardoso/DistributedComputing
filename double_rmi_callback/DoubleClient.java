import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;

public class DoubleClient extends UnicastRemoteObject implements DoubleInterfaceClient {
	private static volatile int i, j;

	public DoubleClient() throws RemoteException {
	}

	public static void main(String[] args) {
		int result;

		if (args.length != 3) {
			System.out.println("Usage: java DoubleClient <server ip> <client ip> <client port>");
			System.exit(1);
		}
	
		try {
			System.setProperty("java.rmi.server.hostname", args[1]);
			LocateRegistry.createRegistry(Integer.parseInt(args[2]));
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}

		try {
			String client = "rmi://" + args[1] + ":" + args[2] + "/Hello2";
			Naming.rebind(client, new DoubleClient());
			System.out.println("Double Server is ready.");
		} catch (Exception e) {
			System.out.println("Double Serverfailed: " + e);
		}

		String remoteHostName = args[0];
		String connectLocation = "rmi://" + remoteHostName + ":52369/Hello";

		DoubleInterfaceServer hello = null;
		try {
			System.out.println("Connecting to server at : " + connectLocation);
			hello = (DoubleInterfaceServer) Naming.lookup(connectLocation);
		} catch (Exception e) {
			System.out.println ("DoubleClient failed: ");
			e.printStackTrace();
		}

		i = 0;

		while (true) {
			try {
				hello.Double(i++, Integer.parseInt(args[2]));
				System.out.println("Call to server..." );
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {}
		}
	}
	
	public int Result(String val) {
		System.out.println("Called back, result is: " + val);
		
		return -1;
	}
}
