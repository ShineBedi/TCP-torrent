package Client;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class Client {
	
	private static Socket clientSocket = null;
	
	private static PrintStream os = null;
	
	private static DataInputStream is = null;

	private static BufferedReader inputLine = null;

	public static void main(String[] args) {
		Properties prop = new Properties();
		InputStream input = null;
		String host = args[0];
		String clientId = null;
		int serverPort = 0;
		String myPort = null;
		String vectorString = null;
		try {
			
			if (args[0].contains(".")) {
				gethostbyaddr(host);
			} else {
				gethostbyname(host);
			}
			
			input = new FileInputStream(args[1]);
			
			prop.load(input);
			clientId = prop.getProperty("CLIENTID");
			serverPort = Integer.parseInt(prop.getProperty("SERVERPORT"));
			myPort = prop.getProperty("MYPORT");
			vectorString = prop.getProperty("FILE_VECTOR");

			
			System.out.println(clientId);
			System.out.println(serverPort);
			System.out.println(myPort);
			System.out.println(vectorString);
			
			Socket socket = null;
			try {
				String userInput = "q";
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

				socket = new Socket(host, serverPort);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
			
				while (true) {
					System.out.println("Request file number or enter 'q' to quit: ");
					userInput = stdIn.readLine();
					
					if(!userInput.equals("q")){
						String filePositionChar = Character.toString(vectorString.charAt(Integer.parseInt(userInput)));
						if(filePositionChar.equals("1")){
							System.out.println("File is already with the user,search for a file not in the system:");
							continue;
						}
					}
					
					out.println(clientId+":"+userInput);
					
						String fromServer = in.readLine();
						System.out.println("Server response:\n" + fromServer);
						if (fromServer.equals("q")) {
							System.out.println("Request to close the client: " + clientId);
							socket.close();
							System.out.println("Client Closed!");
							break;
						}
					
					
				}
			} catch (UnknownHostException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			
			
			} finally {

			}

			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static void gethostbyname(String hostName) {
		System.out.println(hostName);

	}

	private static void gethostbyaddr(String hostAddr) {
		System.out.println(hostAddr);
	}

}
