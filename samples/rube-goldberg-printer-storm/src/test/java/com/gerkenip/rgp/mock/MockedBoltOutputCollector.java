package com.gerkenip.rgp.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gerkenip.rgp.bean.*;

import backtype.storm.task.IOutputCollector;
import backtype.storm.tuple.Tuple;

public class MockedBoltOutputCollector implements IOutputCollector {

	public boolean acked = false;
	public boolean failed = false;
	public Throwable error = null;

	public List<AnonymizedMessage> emittedToAnonymous = new ArrayList<AnonymizedMessage>();
	public List<ExpectedCount> emittedToTotals = new ArrayList<ExpectedCount>();
	public List<MessageReference> emittedToMessageReferences = new ArrayList<MessageReference>();
	public List<MessageReference> emittedToApprovals = new ArrayList<MessageReference>();

	public List<Object> others = new ArrayList<Object>();
	
	public MockedBoltOutputCollector() {

	}

	@Override
	public List<Integer> emit(String streamId, Collection<Tuple> anchors, List<Object> tuple) {
		
		List<Integer> result = new ArrayList<Integer>();
		
		if (streamId == null) { return result; }
		
		if (streamId.equals("Anonymous")) {
			emittedToAnonymous.add(new AnonymizedMessage(new MockedTuple(streamId, tuple)));
			return result;
		}
		if (streamId.equals("Totals")) {
			emittedToTotals.add(new ExpectedCount(new MockedTuple(streamId, tuple)));
			return result;
		}
		if (streamId.equals("MessageReferences")) {
			emittedToMessageReferences.add(new MessageReference(new MockedTuple(streamId, tuple)));
			return result;
		}
		if (streamId.equals("Approvals")) {
			emittedToApprovals.add(new MessageReference(new MockedTuple(streamId, tuple)));
			return result;
		}
		
		others.add(tuple);
		return result;
	}

	@Override
	public void emitDirect(int taskId, String streamId, Collection<Tuple> anchors, List<Object> tuple) {

	}

	@Override
	public void ack(Tuple input) {
		acked = true;
	}

	@Override
	public void fail(Tuple input) {
		failed = true;
	}

	@Override
	public void reportError(Throwable error) {
		this.error = error;
	}

}
