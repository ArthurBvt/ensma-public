package fr.ensma.a3.ia.mymultichat.restclient;
// TODO: Documentation

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.util.List;
import java.util.Scanner;

import javax.websocket.DeploymentException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import fr.ensma.a3.ia.mymultichat.business.canalchat.ChatCanalDesc;
import fr.ensma.a3.ia.mymultichat.business.messages.ChatMessage;
import fr.ensma.a3.ia.mymultichat.wsock.ChatClientEndPoint;

/**
 * Hello world!
 *
 */
public class App {
    // FIXME: utilisation d'un fichier properties
    private static final String REST_URI_HELLO = "http://localhost:8082/rest/hello";
    //private static final String REST_URI_CHAT = "http://localhost:8082/rest/chatcanal";
    private static final String REST_URI_LIST = "http://localhost:8082/rest/list";
    private static final String WS_URI = "ws://localhost:8082/ws/multichat";

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out, true, UTF_8);
        out.println("Client REST");
        Client restclient = ClientBuilder.newClient();
        
        String rep = restclient.target(REST_URI_HELLO).request(MediaType.TEXT_PLAIN_TYPE).get(String.class);
        out.println(rep);
        
        /*
        ChatCanalDesc canal_res = restclient.target(REST_URI_CHAT).path(String.valueOf(1)).request(MediaType.APPLICATION_JSON).get(ChatCanalDesc.class);
        out.println(canal_res);
        */
        
        out.println("\nListe de tous les cannaux disponibles :");
        List<ChatCanalDesc> list = restclient.target(REST_URI_LIST).request(MediaType.APPLICATION_JSON).get(new GenericType<List<ChatCanalDesc>>(){});
        for (ChatCanalDesc c : list)
        	out.println(c);
        
        restclient.close();
        out.println("\nFin connexion...\n");
        
        out.println("Connexion WebSocket :");
        Scanner scan = new Scanner(System.in);
        
        out.println("Sur quel cannal se connecter ?");
        int cannalId = scan.nextInt();
        scan.nextLine();
        
        out.println("Donne ton pseudo : ");
		String pseudo = scan.nextLine();

        try {
            final ChatClientEndPoint clientendpoint = new ChatClientEndPoint(URI.create(WS_URI + "/" + cannalId + ":" + pseudo));
            
            out.println("Bienvenu sur MultiChat - Canal-" + cannalId + " !!");
            
            String blabla;
            clientendpoint.getSession().getUserProperties().put("Pseudo", pseudo);
            do {
                out.print("Message : ");
                blabla = scan.nextLine();
                clientendpoint.getSession().getBasicRemote().sendText(formatMessage(pseudo, blabla));
            } while (!blabla.equalsIgnoreCase("quit"));
            clientendpoint.getSession().close();
        } catch (DeploymentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            scan.close();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static Gson gson = new Gson();

    private static String formatMessage(String pseu, String bla) {
        ChatMessage m = new ChatMessage();
        m.setCanalId(0);
        m.setLePseudo(pseu);
        m.setLeContenu(bla);
        return gson.toJson(m);
    }
}
