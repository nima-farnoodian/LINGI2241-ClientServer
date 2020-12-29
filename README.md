# LINGI2241-ClientServer
In this project, we have been asked to create a client-server application where we analyse the
performance that is the response time of the client-server application. We have implemented two
versions of servers (simple server and an optimized server) which will accept the requests from the
client through the network and send back the requested result to the client.

## Getting started
- Clone this repo
- Build / package source
`mvn clean package`

- Start the server
`java -cp target/serveroptimization-1.0-SNAPSHOT.jar edu.ucl.nima.Server 4050`

- Run the client
`java -cp target/serveroptimization-1.0-SNAPSHOT.jar edu.ucl.nima.Client localhost 4050 optimized;1;"*.second.*"

## Warning messages
*Please note:*
The *ClientInBatch* requires five arguments that specifies a server host, server port, server version (simple or optimize), the number of clients, an input file that includes example regular expersion (just its address and name with '.txt'), and mean of expo. dist. (For Regular Experssion example format, Please see regs.txt. The experssions in this file are mostly heavy and intensive queries. We used this file for our simulation)
Also Notice that you should place your database under the directory LINGI2241-ClientServer/src/main/resources/. 
