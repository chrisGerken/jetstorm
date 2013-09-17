package com.gerkenip.rgp.bolt;

import backtype.storm.tuple.Tuple;

import com.gerkenip.rgp.bean.*;

public interface IMessagePrinterBolt {

    public void ack();

    public void fail();

	public int getTaskId();

}