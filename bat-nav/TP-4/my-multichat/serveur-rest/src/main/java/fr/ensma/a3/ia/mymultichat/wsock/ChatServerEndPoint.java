package fr.ensma.a3.ia.mymultichat.wsock;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import fr.ensma.a3.ia.mymultichat.business.messages.ChatMessage;
import fr.ensma.a3.ia.mymultichat.wsock.coder.ChatMessageDecoder;
import fr.ensma.a3.ia.mymultichat.wsock.coder.ChatMessageEncoder;

/**
 * ChatServerEndPoint
 */
@ServerEndpoint(value = "/ws/multichat/{canalandpseudo}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatServerEndPoint {

    static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(@PathParam("canalandpseudo") String canalandpseudo, Session sess, EndpointConfig endpointConfig) {
        String[] params = canalandpseudo.split(":");
        sess.getUserProperties().put("pseudo", params[1]);
        sess.getUserProperties().put("canalId", Integer.valueOf(params[0]));
        sess.setMaxIdleTimeout(0);
        System.out.println(params[1] + " vient de se connecter au canal " + params[0]);
        clients.add(sess);
    }

    // Réaction du serveur à la réception d'un message.
    @OnMessage
    public void onMessage(ChatMessage mess, Session sess) {
        for (Session client : clients) {
            if (!sess.getId().equals(client.getId()) && client.getUserProperties().get("canalId") == sess.getUserProperties().get("canalId")) {
                try {
                    client.getBasicRemote().sendObject(mess);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (EncodeException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClose
    public void onClose(Session sess) {
        System.out.println(sess.getUserProperties().get("pseudo") + " vient de se déconnecter ...");
        try {
            sess.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        clients.remove(sess);
        ChatMessage mess = new ChatMessage();
        for (Session client : clients) {
            mess.setLePseudo("LeServer");
            mess.setLeContenu((String) sess.getUserProperties().get("pseudo") + " nous a quitté ... (sniff)");
            try {
                client.getBasicRemote().sendObject(mess);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (EncodeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }
}
