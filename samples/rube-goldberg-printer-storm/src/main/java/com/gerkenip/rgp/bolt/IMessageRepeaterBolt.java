package com.gerkenip.rgp.bolt;

import backtype.storm.tuple.Tuple;

import com.gerkenip.rgp.bean.*;

public interface IMessageRepeaterBolt {

	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToMessageReferences(MessageReference messageReference);
	
	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToMessageReferencesWithoutAnchor(MessageReference messageReference);

    public void ack();

    public void fail();

	public int getTaskId();

}