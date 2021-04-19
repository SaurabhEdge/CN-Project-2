

There are 6 java classes as follows:

LunarRover.java
DistanceVector.java
RIPencode.java
RouterTableGeneration.java
UDPClient.java
UDPReceiver.java


Compilation step:

javac *.java

java LunarRover [ip address] [port number] [rover-id]

java LunarRover 10.0.1.0 520 1


######       Few assumption I have taken while executing the code   ###########

-> Next hop will be displayed as rover-id

for example: 

IP address  next hop  cost 
10.0.1.0 	1	0
10.0.2.0	1	1

Next hop 1 imply that ip address 10.0.1.0 
Next hop 10 will imply that ip address 10.0.10.0


--> Subnet mask I have assumed as 24
