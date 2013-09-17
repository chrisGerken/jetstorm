package com.gerkenip.rgp.spout;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.junit.Test;

import backtype.storm.spout.SpoutOutputCollector;

import com.gerkenip.rgp.bean.*;
import com.gerkenip.rgp.mock.*;

public class MessageProviderSpoutTest {
 
	@Test
	public void testSerialization() {
		
		try {
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new MessageProviderSpout() );
		} catch (Throwable t) {
			fail("Class MessageProviderSpout is not serializable");
		}

	}

	@Test
	public void testNextTuple() {
		
//		MessageProviderSpout spout = new MessageProviderSpout();

//		MockedSpoutOutputCollector collector = new MockedSpoutOutputCollector();
//		HashMap<String,Object> config = new HashMap<String, Object>();
		
//		spout.open(config,null,new SpoutOutputCollector(collector));
//		spout.nextTuple();
//		spout.close();
		
		// Validate side effects of nextTuple() call by interrogating collector
		
	}

}
