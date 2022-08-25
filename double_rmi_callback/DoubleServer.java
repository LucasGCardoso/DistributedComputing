import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;

public class DoubleServer extends UnicastRemoteObject implements DoubleInterfaceServer {
	private static volatile String result;
	private static volatile boolean changed;
	private static volatile String remoteHostName, remoteHostPort;

	public DoubleServer() throws RemoteException {
	}
	
	public static void main(String[] args) throws RemoteException {
		if (args.length != 1) {
			System.out.println("Usage: java DoubleServer <server ip>");
			System.exit(1);
		}

		try {
			System.setProperty("java.rmi.server.hostname", args[0]);
			LocateRegistry.createRegistry(52369);
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}

		try {
			String server = "rmi://" + args[0] + ":52369/Hello";
			Naming.rebind(server, new DoubleServer());
			System.out.println("Double Server is ready.");
		} catch (Exception e) {
			System.out.println("Double Serverfailed: " + e);
		}
		
		while (true) {
			if (changed == true) {
				changed = false;

				String connectLocation = "rmi://" + remoteHostName + ":" + remoteHostPort + "/Hello2";

				DoubleInterfaceClient hello2 = null;
				try {
					System.out.println("Calling client back at : " + connectLocation);
					hello2 = (DoubleInterfaceClient) Naming.lookup(connectLocation);
				} catch (Exception e) {
					System.out.println ("Callback failed: ");
					e.printStackTrace();
				}

				try {
					hello2.Result(result);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {}
		}
	}
	
	public String Double(int value, int port) {
		int double_value = value * 2;
		result = "Double of " + Integer.toString(value) + " is " + Integer.toString(double_value);
		changed = true;
		try {
			remoteHostName = getClientHost();
			remoteHostPort = Integer.toString(port);
		} catch (Exception e) {
			System.out.println ("Failed to get client IP");
			e.printStackTrace();
		}
		
		return "Done";
	}
}
