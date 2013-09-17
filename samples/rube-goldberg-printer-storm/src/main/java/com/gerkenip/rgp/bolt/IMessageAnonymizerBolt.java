package com.gerkenip.rgp.bolt;

import backtype.storm.tuple.Tuple;

import com.gerkenip.rgp.bean.*;

public interface IMessageAnonymizerBolt {

	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToAnonymous(AnonymizedMessage anonymizedMessage);
	
	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToAnonymousWithoutAnchor(AnonymizedMessage anonymizedMessage);

	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToTotals(ExpectedCount expectedCount);
	
	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToTotalsWithoutAnchor(ExpectedCount expectedCount);

    public void ack();

    public void fail();

	public int getTaskId();

}