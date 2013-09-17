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

public class ExpectedCount implements Serializable, Comparable<ExpectedCount> {

	private Long _id;
	private Integer _totalSent;

	public ExpectedCount(Tuple tuple) {
		_id = tuple.getLong(0);
		_totalSent = tuple.getInteger(1);
	}

	public ExpectedCount() {

	}

	public ExpectedCount(JSONObject json) throws DataException {
		try { json = json.getJSONObject("ExpectedCount"); } 
		catch (Throwable t) {
			throw new DataException("Invalid JSON structure for ExpectedCount constructor");
		}
		if (json.has("id")) {
			try { _id = json.getLong("id"); } catch (Throwable t) {}
		}
		if (json.has("totalSent")) {
			try { _totalSent = json.getInt("totalSent"); } catch (Throwable t) {}
		}
	}

	public ExpectedCount(Long _id, Integer _totalSent) {	
		this._id = _id;
		this._totalSent = _totalSent;
	}

	public Values asValues() {
		return new Values(_id, _totalSent);
	}

	public Long getId() { 
		return _id;
	}
	
	public void setId(Long value) {
		this._id = value;
	}

	public Integer getTotalSent() { 
		return _totalSent;
	}
	
	public void setTotalSent(Integer value) {
		this._totalSent = value;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {

		if (_id == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeLong(_id);
		}

		if (_totalSent == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_totalSent);
		}

	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

		if (in.readBoolean()) {
			_id = in.readLong();
		} else {
			_id = null;
		}

		if (in.readBoolean()) {
			_totalSent = in.readInt();
		} else {
			_totalSent = null;
		}

	}
	
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("ExpectedCount [id = " + _id + "; totalSent = " + _totalSent + "]");
		return sb.toString();
		
	}
	
	public JSONObject toJson() throws DataException {
	
		JSONObject json = new JSONObject();

		try { 
 			if (_id != null) {
 				json.putOpt("id", _id);
 			}
 			if (_totalSent != null) {
 				json.putOpt("totalSent", _totalSent);
 			}
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting ExpectedCount to JSON",e);
 		}
 		
 		JSONObject result = new JSONObject();
		try { 		
			result.put("ExpectedCount", json);
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting ExpectedCount to JSON",e);
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
		return new Fields("id", "totalSent");
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

		if (obj instanceof ExpectedCount) {
			ExpectedCount other = (ExpectedCount) obj;
		
		}

		return super.equals(obj);

		// End equals logic 

	}

	public int compareTo(ExpectedCount o) {

		// Begin compare logic 


		// return -1 if this < that
		//         0 if this = that
		//         1 if this > that

		if (o instanceof ExpectedCount) {
			ExpectedCount other = (ExpectedCount) o;
		
		}
	
		return 0;

		// End compare logic 

	}

	public static ExpectedCount sample() {
	
		return new ExpectedCount(1L, 0);
	
	}

// Begin custom methods 


// End custom methods 
	
}