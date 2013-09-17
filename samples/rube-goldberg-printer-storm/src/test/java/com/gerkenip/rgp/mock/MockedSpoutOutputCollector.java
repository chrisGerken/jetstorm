package com.gerkenip.rgp.mock;

import java.util.ArrayList;
import java.util.List;

import com.gerkenip.rgp.bean.*;

import backtype.storm.spout.ISpoutOutputCollector;

public class MockedSpoutOutputCollector implements ISpoutOutputCollector {
	
	public List<Message> emittedToMessages = new ArrayList<Message>();
	public List<Message> idsForMessages = new ArrayList<Message>();

	public List<Object> others = new ArrayList<Object>();

	public MockedSpoutOutputCollector() {
	}

	@Override
	public List<Integer> emit(String streamId, List<Object> tuple, Object messageId) {
		
		List<Integer> result = new ArrayList<Integer>();
		
		if (streamId == null) { return result; }
		
		if (streamId.equals("Messages")) {
			emittedToMessages.add(new Message(new MockedTuple(streamId, tuple)));
			if (messageId == null) {
				messageId = Message.sample();
			}
			idsForMessages.add((Message)messageId);
			return result;
		}
		
		others.add(tuple);
		return result;
		
	}

	@Override
	public void emitDirect(int taskId, String streamId, List<Object> tuple,
			Object messageId) {  
	}

	@Override
	public void reportError(Throwable error) {
	}

}
