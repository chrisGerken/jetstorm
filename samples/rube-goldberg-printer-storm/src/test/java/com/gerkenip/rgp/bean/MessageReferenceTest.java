package com.gerkenip.rgp.bean;

// Begin imports 

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

// End imports 

public class MessageReferenceTest {

	@Test
	public void testJsonification() {
		try {
			new MessageReference(new JSONObject(MessageReference.sample().toJsonString()));
		} catch (Exception e) {
			fail("Failed JSONification");
		}
	}
	
	@Test
	public void testTupilization() {
		MessageReference.sample().asValues();
	}

	@Test
	public void testSerialization() {
		
		try {

			serDeser(MessageReference.sample());
			serDeser(new MessageReference());
			
		} catch (Throwable t) {
			fail("Object not serializable: "+t);
		}

	}

	private MessageReference serDeser(MessageReference obj) throws Throwable {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		return (MessageReference) ois.readObject();
	}
	
	// Begin custom methods
	
	
	// End custom methods

	
}
