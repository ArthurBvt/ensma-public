java -cp client-rest/target/client-rest-1.0-SNAPSHOT.jar:client-rest/target/dependency/* fr.ensma.a3.ia.mymultichat.restclient.App
sudo docker compose up --build
java -cp serveur-rest/target/classes:serveur-rest/target/dependency/* com.kumuluz.ee.EeApplication
