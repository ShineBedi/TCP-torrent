package serverside;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Serverside {
    public static void main(String[] args) throws IOException {
        ClientInformation[] clientInfoArray = new ClientInformation[5];
        Arrayinfo(clientInfoArray);
        int portNumber = 5000;
        System.out.println("The Server is listening..............");
        try{ 
            ServerSocket serversocket = new ServerSocket(portNumber);
            Socket clientSocket = serversocket.accept();     
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
         
            String inputLine;
            String output;
            while ((inputLine = in.readLine()) != null) {
            	
            	String[] inputlineSplit = inputLine.split(":");
            	System.out.println("Client "+inputlineSplit[0]+" requests file:"+inputlineSplit[1] );
            	if(inputlineSplit[1].equals("q")){       		
            		System.out.println("Closing signal received:"+inputLine);
            		out.println(inputlineSplit[1]);
            	}else{
            		int fileNumber = Integer.parseInt(inputlineSplit[1]);
            		
            		String Clientsend="Clients who have this file are:";
            		for(int i=0; i<4;i++){
            			String fileVector = clientInfoArray[i].getClientFileVector();
            			if(Character.toString(fileVector.charAt(fileNumber)).equals("1")){
            				int currentClientNumber = i+1;
            				Clientsend = Clientsend+" "+currentClientNumber;
            			}
            		}
            		 
            		out.println(Clientsend);
            	}            
            }
            System.out.println ("Server socket closed!");
            serversocket.close(); 
            
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or while closing the server");
            System.out.println(e.getMessage());
        }
        
    


    }

	private static void Arrayinfo(ClientInformation[] clientInfoArray) throws IOException {
		InputStream input= new FileInputStream("clientconfig.1");
		Properties prop = new Properties();
		prop.load(input);
		ClientInformation clientInformation1 = new ClientInformation(Integer.parseInt(prop.getProperty("CLIENTID")), prop.getProperty("MYPORT"), prop.getProperty("FILE_VECTOR"));
		clientInfoArray[0]=clientInformation1;
		
		input= new FileInputStream("clientconfig.2");
		Properties prop2 = new Properties();
		prop2.load(input);
		ClientInformation clientInformation2 = new ClientInformation(Integer.parseInt(prop2.getProperty("CLIENTID")), prop2.getProperty("MYPORT"), prop2.getProperty("FILE_VECTOR"));
		clientInfoArray[1]=clientInformation2;
		
		input= new FileInputStream("clientconfig.3");
		Properties prop3 = new Properties();
		prop3.load(input);
		ClientInformation clientInformation3 = new ClientInformation(Integer.parseInt(prop3.getProperty("CLIENTID")), prop3.getProperty("MYPORT"), prop3.getProperty("FILE_VECTOR"));
		clientInfoArray[2]=clientInformation3;
		
		
		input= new FileInputStream("clientconfig.4");
		Properties prop4 = new Properties();
		prop4.load(input);
		ClientInformation clientInformation4 = new ClientInformation(Integer.parseInt(prop4.getProperty("CLIENTID")), prop4.getProperty("MYPORT"), prop4.getProperty("FILE_VECTOR"));
		clientInfoArray[3]=clientInformation4;		 
	}}
        



 
    
  