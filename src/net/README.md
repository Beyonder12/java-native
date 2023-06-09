curl localhost:8000/hello
(Invoke-WebRequest -Uri http://localhost:9000/hello)

(Invoke-WebRequest -Uri http://localhost:9000/hello).Content | % {[char]$_}

-join ((Invoke-WebRequest -Uri http://localhost:9000/hello).Content | % {[char]$_})


Invoke-WebRequest -Uri https://jdbc.postgresql.org/download/postgresql-42.2.20.jar -OutFile postgresql-42.2.20.jar

cd "C:\Users\IFG Life\Documents\Github\fajri\java-native\src"

javac -cp .`;`postgresql-42.2.20.jar SimpleHttpServer.java

java -cp .`;`postgresql-42.2.20.jar`;`src net.SimpleHttpServer