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

public class ExpectedCountTest {

	@Test
	public void testJsonification() {
		try {
			new ExpectedCount(new JSONObject(ExpectedCount.sample().toJsonString()));
		} catch (Exception e) {
			fail("Failed JSONification");
		}
	}
	
	@Test
	public void testTupilization() {
		ExpectedCount.sample().asValues();
	}

	@Test
	public void testSerialization() {
		
		try {

			serDeser(ExpectedCount.sample());
			serDeser(new ExpectedCount());
			
		} catch (Throwable t) {
			fail("Object not serializable: "+t);
		}

	}

	private ExpectedCount serDeser(ExpectedCount obj) throws Throwable {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		return (ExpectedCount) ois.readObject();
	}
	
	// Begin custom methods
	
	
	// End custom methods

	
}
