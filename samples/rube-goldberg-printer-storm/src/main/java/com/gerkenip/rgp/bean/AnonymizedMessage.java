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

public class AnonymizedMessage implements Serializable, Comparable<AnonymizedMessage> {

	private String _text;
	private Long _id;

	public AnonymizedMessage(Tuple tuple) {
		_text = tuple.getString(0);
		_id = tuple.getLong(1);
	}

	public AnonymizedMessage() {

	}

	public AnonymizedMessage(JSONObject json) throws DataException {
		try { json = json.getJSONObject("AnonymizedMessage"); } 
		catch (Throwable t) {
			throw new DataException("Invalid JSON structure for AnonymizedMessage constructor");
		}
		if (json.has("text")) {
			try { _text = json.getString("text"); } catch (Throwable t) {}
		}
		if (json.has("id")) {
			try { _id = json.getLong("id"); } catch (Throwable t) {}
		}
	}

	public AnonymizedMessage(String _text, Long _id) {	
		this._text = _text;
		this._id = _id;
	}

	public Values asValues() {
		return new Values(_text, _id);
	}

	public String getText() { 
		return _text;
	}
	
	public void setText(String value) {
		this._text = value;
	}

	public Long getId() { 
		return _id;
	}
	
	public void setId(Long value) {
		this._id = value;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {

		if (_text == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_text.length());
			out.write(_text.getBytes());
		}

		if (_id == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeLong(_id);
		}

	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

		if (in.readBoolean()) {
			byte b[] = new byte[in.readInt()];
			in.read(b);
			_text = new String(b);
		} else {
			_text = null;
		}

		if (in.readBoolean()) {
			_id = in.readLong();
		} else {
			_id = null;
		}

	}
	
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("AnonymizedMessage [text = " + _text + "; id = " + _id + "]");
		return sb.toString();
		
	}
	
	public JSONObject toJson() throws DataException {
	
		JSONObject json = new JSONObject();

		try { 
 			if (_text != null) {
 				json.putOpt("text", _text);
 			}
 			if (_id != null) {
 				json.putOpt("id", _id);
 			}
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting AnonymizedMessage to JSON",e);
 		}
 		
 		JSONObject result = new JSONObject();
		try { 		
			result.put("AnonymizedMessage", json);
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting AnonymizedMessage to JSON",e);
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
		return new Fields("text", "id");
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

		if (obj instanceof AnonymizedMessage) {
			AnonymizedMessage other = (AnonymizedMessage) obj;
		
		}

		return super.equals(obj);

		// End equals logic 

	}

	public int compareTo(AnonymizedMessage o) {

		// Begin compare logic 


		// return -1 if this < that
		//         0 if this = that
		//         1 if this > that

		if (o instanceof AnonymizedMessage) {
			AnonymizedMessage other = (AnonymizedMessage) o;
		
		}
	
		return 0;

		// End compare logic 

	}

	public static AnonymizedMessage sample() {
	
		return new AnonymizedMessage("Howdy", 1L);
	
	}

// Begin custom methods 


// End custom methods 
	
}