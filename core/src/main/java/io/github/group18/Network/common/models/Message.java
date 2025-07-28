package io.github.group18.Network.common.models;

import java.util.HashMap;

public class Message {
	private Type type;
	private HashMap<String, Object> body;

	/*
	 * Empty constructor needed for JSON Serialization/Deserialization
	 */
	public Message() {}

	public Message(HashMap<String, Object> body, Type type) {
		this.body = body;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

    public void setType(Type type) {
        this.type = type;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(HashMap<String, Object> body) {
        this.body = body;
    }

    public <T> T getFromBody(String fieldName) {
		return (T) body.get(fieldName);
	}

	public int getIntFromBody(String fieldName) {
		return (int) ((double) ((Double) body.get(fieldName)));
	}

	public enum Type {
        command,
        Register,
        Is_Unique,
		response,
		file_request,
		download_request
	}
}
