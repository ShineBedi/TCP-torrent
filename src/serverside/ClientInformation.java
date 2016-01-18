/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside;

/**
 *
 * @author Shine
 */




public class ClientInformation {
int clientId;
String clientPortNumber;
String clientFileVector;

public ClientInformation(int clientId, String clientPortNumber, String clientFileVector) {
	super();
	this.clientId = clientId;
	this.clientPortNumber = clientPortNumber;
	this.clientFileVector = clientFileVector;
}

public int getClientId() {
	return clientId;
}
public void setClientId(int clientId) {
	this.clientId = clientId;
}
public String getClientPortNumber() {
	return clientPortNumber;
}
public void setClientPortNumber(String clientPortNumber) {
	this.clientPortNumber = clientPortNumber;
}
public String getClientFileVector() {
	return clientFileVector;
}
public void setClientFileVector(String clientFileVector) {
	this.clientFileVector = clientFileVector;
}

}

