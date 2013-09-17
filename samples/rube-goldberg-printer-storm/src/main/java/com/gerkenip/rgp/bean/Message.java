package com.gerkenip.rgp.bean;

// Begin imports 

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.gerkenip.rgp.exception.DataException;

import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

// End imports 

public class Message implements Serializable, Comparable<Message> {

	private String _messageText;
	private Integer _count;

	public Message(Tuple tuple) {
		_messageText = tuple.getString(0);
		_count = tuple.getInteger(1);
	}

	public Message() {

	}

	public Message(JSONObject json) throws DataException {
		try { json = json.getJSONObject("Message"); } 
		catch (Throwable t) {
			throw new DataException("Invalid JSON structure for Message constructor");
		}
		if (json.has("messageText")) {
			try { _messageText = json.getString("messageText"); } catch (Throwable t) {}
		}
		if (json.has("count")) {
			try { _count = json.getInt("count"); } catch (Throwable t) {}
		}
	}

	public Message(String _messageText, Integer _count) {	
		this._messageText = _messageText;
		this._count = _count;
	}

	public Values asValues() {
		return new Values(_messageText, _count);
	}

	public String getMessageText() { 
		return _messageText;
	}
	
	public void setMessageText(String value) {
		this._messageText = value;
	}

	public Integer getCount() { 
		return _count;
	}
	
	public void setCount(Integer value) {
		this._count = value;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {

		if (_messageText == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_messageText.length());
			out.write(_messageText.getBytes());
		}

		if (_count == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_count);
		}

	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

		if (in.readBoolean()) {
			byte b[] = new byte[in.readInt()];
			in.read(b);
			_messageText = new String(b);
		} else {
			_messageText = null;
		}

		if (in.readBoolean()) {
			_count = in.readInt();
		} else {
			_count = null;
		}

	}
	
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("Message [messageText = " + _messageText + "; count = " + _count + "]");
		return sb.toString();
		
	}
	
	public JSONObject toJson() throws DataException {
	
		JSONObject json = new JSONObject();

		try { 
 			if (_messageText != null) {
 				json.putOpt("messageText", _messageText);
 			}
 			if (_count != null) {
 				json.putOpt("count", _count);
 			}
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting Message to JSON",e);
 		}
 		
 		JSONObject result = new JSONObject();
		try { 		
			result.put("Message", json);
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting Message to JSON",e);
 		}
		return result;
	}

	public String toJsonString() {
		try {
			return toJson().toString();
		} catch (DataException e) {
			return "{ \"error\":\"+e.toString()+\"}";
		}
	}
	
	public static Fields asFields() {
		return new Fields("messageText", "count");
	}
	
	@Override
	public int hashCode() {

		// Begin hashCode logic 

		return super.hashCode();

		// End hashCode logic 

	}

	@Override
	public boolean equals(Object obj) {

		// Begin equals logic 

		if (obj instanceof Message) {
			Message other = (Message) obj;
		
		}

		return super.equals(obj);

		// End equals logic 

	}

	public int compareTo(Message o) {

		// Begin compare logic 


		// return -1 if this < that
		//         0 if this = that
		//         1 if this > that

		if (o instanceof Message) {
			Message other = (Message) o;
		
		}
	
		return 0;

		// End compare logic 

	}

	public static Message sample() {
	
		return new Message("Howdy", 0);
	
	}

// Begin custom methods 


// End custom methods 
	
}