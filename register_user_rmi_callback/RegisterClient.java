import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;

public class RegisterClient extends UnicastRemoteObject implements RegisterInterfaceClient {
	private static volatile int i, j;

	public RegisterClient() throws RemoteException {
	}

	public static void main(String[] args) {
		int result;

		if (args.length != 3) {
			System.out.println("Usage: java RegisterClient <server ip> <client ip> <client port>");
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
			Naming.rebind(client, new RegisterClient());
			System.out.println("Register Server is ready.");
		} catch (Exception e) {
			System.out.println("Register Serverfailed: " + e);
		}

		String remoteHostName = args[0];
		String connectLocation = "rmi://" + remoteHostName + ":52369/Hello";

		RegisterInterfaceServer hello = null;
		try {
			System.out.println("Connecting to server at : " + connectLocation);
			hello = (RegisterInterfaceServer) Naming.lookup(connectLocation);
		} catch (Exception e) {
			System.out.println ("RegisterClient failed: ");
			e.printStackTrace();
		}

		i = 0;

		while (true) {
			try {
				hello.register(i++, Integer.parseInt(args[2]));
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
