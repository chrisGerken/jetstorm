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

public class AnonymizedMessageTest {

	@Test
	public void testJsonification() {
		try {
			new AnonymizedMessage(new JSONObject(AnonymizedMessage.sample().toJsonString()));
		} catch (Exception e) {
			fail("Failed JSONification");
		}
	}
	
	@Test
	public void testTupilization() {
		AnonymizedMessage.sample().asValues();
	}

	@Test
	public void testSerialization() {
		
		try {

			serDeser(AnonymizedMessage.sample());
			serDeser(new AnonymizedMessage());
			
		} catch (Throwable t) {
			fail("Object not serializable: "+t);
		}

	}

	private AnonymizedMessage serDeser(AnonymizedMessage obj) throws Throwable {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		return (AnonymizedMessage) ois.readObject();
	}
	
	// Begin custom methods
	
	
	// End custom methods

	
}
