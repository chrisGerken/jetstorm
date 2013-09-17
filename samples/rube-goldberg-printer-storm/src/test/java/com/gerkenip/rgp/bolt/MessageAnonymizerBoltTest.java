package com.gerkenip.rgp.bolt;

	// Begin imports 

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.junit.Test;

import backtype.storm.task.OutputCollector;

import com.gerkenip.rgp.bean.*;
import com.gerkenip.rgp.mock.*;

	// End imports 

public class MessageAnonymizerBoltTest  {
	
		// Begin declarations 

		// End declarations 

	@Test
	public void testSerialization() {
		
		try {
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new MessageAnonymizerBolt() );
		} catch (Throwable t) {
			fail("Class MessageAnonymizerBoltLogic is not serializable");
		}

	}

	@Test
	public void testReadFromMessages() {

		// Begin testReadFromMessages logic 

//		HashMap<String,Object> config = new HashMap<String,Object>();
//		MockedBoltOutputCollector collector = new MockedBoltOutputCollector();
//		
//		MockedTuple tuple = new MockedTuple("Messages", Message.sample().asValues()); 
//		MessageAnonymizerBolt bolt = new MessageAnonymizerBolt();

//		bolt.prepare(config,null,new OutputCollector(collector));
//		bolt.execute(tuple);
//		bolt.cleanup()

			// Validate execution side effects by interrogating collector
					
		// End testReadFromMessages logic 

	}

// Begin custom methods 


// End custom methods 

}
