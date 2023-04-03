package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import manager.UserManager;

public class Session {
	
	private static List<Session> sessions = new ArrayList<>();
	public static boolean sessionExistante = false;
	private static Map<String, Object> sessionData = new HashMap<>();

	public static void setSessionExistante(boolean value) {
		sessionExistante = value;
	}

	public static int sessionId;

	public Session() {
	}

	// constructeur avec l'identifiant de la session
	public Session(int sessionId) {
		this.sessionId = sessionId;
	}

	public void setAttribute(String key, Object value) {
		sessionData.put(key, value);
	}

	public static Object getAttribute(String key) {
		return sessionData.get(key);
	}

	public void invalidate() {
		sessionData.clear();
	}

	public static Session createSession(User user) {
	    int sessionId = user.getSessionId();
	    Session session = new Session(sessionId);
	    session.setAttribute("user", user);
	    Session.setSessionExistante(true);
	    sessions.add(session);
	    return session;
	}

	
	
	public int getSessionId() {
	    return sessionId;
	}

	
	public static Session getSessionExistante(User user) {
	    for (Session session : sessions) {
	        if (session.getSessionId() == user.getSessionId()) {
	            return session;
	        }
	    }
	    return null;
	}

	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}



}
