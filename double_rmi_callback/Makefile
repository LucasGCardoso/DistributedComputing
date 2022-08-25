all:			DoubleClient.class DoubleServer.class \
			DoubleInterfaceClient.class DoubleInterfaceServer.class

DoubleInterfaceClient.class:	DoubleInterfaceClient.java
			@javac DoubleInterfaceClient.java

DoubleInterfaceServer.class:	DoubleInterfaceServer.java
			@javac DoubleInterfaceServer.java

DoubleClient.class:	DoubleClient.java
			@javac DoubleClient.java

DoubleServer.class:	DoubleServer.java
			@javac DoubleServer.java

clean:
			@rm -f *.class *~

