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

public class MessagePrinterBoltTest  {
	
		// Begin declarations 

		// End declarations 

	@Test
	public void testSerialization() {
		
		try {
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new MessagePrinterBolt() );
		} catch (Throwable t) {
			fail("Class MessagePrinterBoltLogic is not serializable");
		}

	}

	@Test
	public void testReadFromApprovals() {

		// Begin testReadFromApprovals logic 

//		HashMap<String,Object> config = new HashMap<String,Object>();
//		MockedBoltOutputCollector collector = new MockedBoltOutputCollector();
//		
//		MockedTuple tuple = new MockedTuple("Approvals", MessageReference.sample().asValues()); 
//		MessagePrinterBolt bolt = new MessagePrinterBolt();

//		bolt.prepare(config,null,new OutputCollector(collector));
//		bolt.execute(tuple);
//		bolt.cleanup()

			// Validate execution side effects by interrogating collector
					
		// End testReadFromApprovals logic 

	}

// Begin custom methods 


// End custom methods 

}
