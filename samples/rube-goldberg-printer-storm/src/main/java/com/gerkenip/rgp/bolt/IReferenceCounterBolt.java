package com.gerkenip.rgp.bolt;

import backtype.storm.tuple.Tuple;

import com.gerkenip.rgp.bean.*;

public interface IReferenceCounterBolt {

	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToApprovals(MessageReference messageReference);
	
	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToApprovalsWithoutAnchor(MessageReference messageReference);

    public void ack();

    public void fail();

	public int getTaskId();

}