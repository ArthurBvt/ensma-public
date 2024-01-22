package fr.ensma.a3.ia.mymultichat.wsock;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
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

import fr.ensma.a3.ia.mymultichat.business.game.EResponseType;
import fr.ensma.a3.ia.mymultichat.business.game.Game;
import fr.ensma.a3.ia.mymultichat.business.game.Player;
import fr.ensma.a3.ia.mymultichat.business.messages.ChatMessage;
import fr.ensma.a3.ia.mymultichat.wsock.coder.ChatMessageDecoder;
import fr.ensma.a3.ia.mymultichat.wsock.coder.ChatMessageEncoder;

/**
 * ChatServerEndPoint
 */
@ServerEndpoint(value = "/ws/multichat/{canalandpseudo}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatServerEndPoint {

	static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	static HashMap<Integer, Game> games;
	static {
		games = new HashMap<Integer, Game>();
		games.put(2, new Game());
		games.put(3, new Game());
	}

	@OnOpen
	public void onOpen(@PathParam("canalandpseudo") String canalandpseudo, Session sess,
			EndpointConfig endpointConfig) {
		String[] params = canalandpseudo.split(":");
		sess.getUserProperties().put("pseudo", params[1]);
		sess.getUserProperties().put("canalId", Integer.valueOf(params[0]));
		sess.setMaxIdleTimeout(0);
		System.out.println(params[1] + " vient de se connecter au canal " + params[0]);
		clients.add(sess);

		Game game = games.get(Integer.valueOf(params[0]));
		if (!game.isConnected(params[1])) {
			game.registerPlayer(new Player(params[1]));
		}
	}

	private void sendMessage(ChatMessage msg, Session client) {
		try {
			client.getBasicRemote().sendObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Réaction du serveur à la réception d'un message.
	@OnMessage
	public void onMessage(ChatMessage mess, Session sess) {
		if (games.containsKey(sess.getUserProperties().get("canalId"))) {
			Game game = games.get((int) sess.getUserProperties().get("canalId"));
			ChatMessage m = new ChatMessage();
			m.setCanalId((int) sess.getUserProperties().get("canalId"));
			m.setLePseudo("Gestion de partie");
			ChatMessage m2 = new ChatMessage();
			m2.setCanalId((int) sess.getUserProperties().get("canalId"));
			m2.setLePseudo("Gestion de partie");

			if (game.isWaitingState()) {

				if (mess.getLeContenu().equals("start")) {
					game.start();
					for (Session client : clients) {
						m.setLeContenu(sess.getUserProperties().get("pseudo") + " a lancé la partie.");
						sendMessage(m, client);
						m.setLeContenu("Au tour de " + game.getCurrentPlayer().getName() + " de jouer.");
						sendMessage(m, client);
					}
				} else {
					m.setLeContenu("<start> pour lancer la partie.");
					sendMessage(m, sess);
				}

			} else {
				if (!game.getPlayer((String) sess.getUserProperties().get("pseudo")).isEmpty()) {
					Player p = game.getPlayer((String) sess.getUserProperties().get("pseudo")).get();
					if (game.getCurrentPlayer().equals(p)) {
						try {
							int guess = Integer.parseInt(mess.getLeContenu());
							EResponseType rep = game.guess(p, guess);
							m2.setLeContenu("C'est au tour de " + game.getCurrentPlayer().getName() + " de jouer.");
							if (rep == EResponseType.ITS_LESS) {
								m.setLeContenu(p.getName() + " a tenté " + guess + ". Résultat: C'est moins !");
							} else if (rep == EResponseType.ITS_MORE) {
								m.setLeContenu(p.getName() + " a tenté " + guess + ". Résultat: C'est plus !");
							} else {
								m.setLeContenu(p.getName() + " a tenté " + guess + ". Résultat: C'est gagné !");
								m2.setLeContenu("<start> pour lancer une nouvelle partie.");
							}
							for (Session client : clients) {
								sendMessage(m, client);
								sendMessage(m2, client);
							}
						} catch (NumberFormatException e) {
							m.setLeContenu("Une valeur entière est attendue.");
							sendMessage(m, sess);
						}
					} else {
						m.setLeContenu("C'est au tour de " + game.getCurrentPlayer().getName() + " de jouer.");
						sendMessage(m, sess);
					}
				}
			}

		} else {
			for (Session client : clients) {
				if (!sess.getId().equals(client.getId())
						&& client.getUserProperties().get("canalId") == sess.getUserProperties().get("canalId")) {
					sendMessage(mess, client);
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
