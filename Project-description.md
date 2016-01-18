# TCP-torrent
TCP server-client ,P2P file search network(Java)

CIS 506: Advanced Computer Networks 3 Credit Hours
Fall 2015
Course Project

 

The code can be written in both C/C++ and Java.

 

In a content distribution network, a client is a user who wishes to download some files. Over the time he may have got a number of files, thus, when asked, he can also upload some of the file he has to other clients. The question is how to make other clients know that he has these files. There are many ways to do this, and in our project we will adopt the simplest method, in which there is a centralized server who keeps track of all the information of the clients in the network (this is the ``tracker’’ in BT). In this project, there will be n files and n is tentatively set to be 64. Each client maintains a 64-bit vector, called ``file vector’’ and indexed from 0 to 63, in which bit i is 1 if he has file i and 0 otherwise. The purpose of this project is to set up basic client-server communications.

 

For the client:

1.  Initialization. The client will be invoked with a command line which contains two arguments. The first argument could be the server’s IP address in dotted decimal notation or the server’s domain name. The client has to be able to figure out which of these two he was given. If it is given the IP address, it calls the gethostbyaddr() function to get the domain name and prints out to the user. If he is given the domain name, he calls gethostbyname() to find the server’s IP address and prints it out. The second argument is the name of a config file which he reads to find his id, server’s listening port, his own listening port, and his initial file vector, which he then prints out. The config files are different for different clients and will be supplied by me.

2.  Connect to the server. He tries to connect to the server by first creating a socket and then calling the connect() function. If the connection is successful, the client prints out a success message. Otherwise he quits. In this assignment, all connection is TCP.

3.  Report to the server. He then sends the server a message with information of his id, his listening port, and the file vector. He does this by calling the send() function.

4.  Wait for commands. He enters an infinite loop. He accepts two commands, ``f’’ and ``q’’. If the user types ``f’’, he will ask which file the user wants and the user will input the file index. If the user types ``q’’, he quits: He sends the server a message saying that he has quit, then wait until server closes the connection with him, then exits. He will use the select() function to get user inputs and to read message from server. If the user says that file, say, 10, is needed, the client first checks if he has file 10. If yes, he prints out a message like ``I already have 10.’’ Otherwise, he sends a message to the server saying that ``can you tell me who has file 10?’’. The server will reply with a list of clients with file 10. Then he prints out the list, then wait for the next command.

5.  Auxiliary functions. The client also reads commands from the server. For this project there is only one command: quit. If the server sends the client this command, the client will close the connection by calling the close() function and exit.


For the server:

1.  Initialization. Server first creates a socket, bind it to port 5000 (5000 is the so-called ``well-known’’ server listening port for our project), and call the listen() function to wait for connection requests.   

2.  Accept requests. If there is a connection request, server calls accept() to accept the request. The integer returned by accept() is the socket id through which server will communicate with this client. He then reads the message the client is supposed to send him which contains the client’s ID, listening port, and file vector.   

3.  Answer client queries.  If a client sends the server a query for a file, server first prints out the client’s ID, client’s IP address (the client’s IP address can be found when calling the accept() function and can be later stored), and the file index the client is asking for. Then he prints out all clients who have the file and who have reported to him, and send this list to the client.

4.  Respond to user command.  Server receives only one command from the user, ``q.’’ If the user types in this command, server sends to all clients a ``quit’’ message and waits until all clients have closed their connections with him, after which he will exit.

5.  Respond to client quit message.  If a client sends a ``quit’’ message to server, server prints out a message like ``(client id) at (IP address) has quit,’’  then close the connection with the client.
