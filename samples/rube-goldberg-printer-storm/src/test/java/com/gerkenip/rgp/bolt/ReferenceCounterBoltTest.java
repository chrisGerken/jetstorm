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

public class ReferenceCounterBoltTest  {
	
		// Begin declarations 

		// End declarations 

	@Test
	public void testSerialization() {
		
		try {
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new ReferenceCounterBolt() );
		} catch (Throwable t) {
			fail("Class ReferenceCounterBoltLogic is not serializable");
		}

	}

	@Test
	public void testReadFromMessageReferences() {

		// Begin testReadFromMessageReferences logic 

//		HashMap<String,Object> config = new HashMap<String,Object>();
//		MockedBoltOutputCollector collector = new MockedBoltOutputCollector();
//		
//		MockedTuple tuple = new MockedTuple("MessageReferences", MessageReference.sample().asValues()); 
//		ReferenceCounterBolt bolt = new ReferenceCounterBolt();

//		bolt.prepare(config,null,new OutputCollector(collector));
//		bolt.execute(tuple);
//		bolt.cleanup()

			// Validate execution side effects by interrogating collector
					
		// End testReadFromMessageReferences logic 

	}

	@Test
	public void testReadFromTotals() {

		// Begin testReadFromTotals logic 

//		HashMap<String,Object> config = new HashMap<String,Object>();
//		MockedBoltOutputCollector collector = new MockedBoltOutputCollector();
//		
//		MockedTuple tuple = new MockedTuple("Totals", ExpectedCount.sample().asValues()); 
//		ReferenceCounterBolt bolt = new ReferenceCounterBolt();

//		bolt.prepare(config,null,new OutputCollector(collector));
//		bolt.execute(tuple);
//		bolt.cleanup()

			// Validate execution side effects by interrogating collector
					
		// End testReadFromTotals logic 

	}

// Begin custom methods 


// End custom methods 

}
