package com.gerkenip.rgp.spout;

import com.gerkenip.rgp.bean.*;
import com.gerkenip.rgp.logic.*;
import com.gerkenip.rgp.util.*;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;  

import com.gerkenip.rgp.topology.RubeGoldbergPrinterTopology;  

public class MessageProviderSpout implements IRichSpout, IMessageProviderSpout {

	private static final long serialVersionUID = 1L;
	private static Map<String, Object> config = null;
    private static ThreadLocal<SpoutOutputCollector> collector = new ThreadLocal<SpoutOutputCollector>();
    private volatile static boolean activated = false; 
	private MessageProviderSpoutLogic logic = new MessageProviderSpoutLogic();
	private int taskId;	

    private static final IRubeGoldbergPrinterLogger log = RubeGoldbergPrinterTopology.getLogger();

    @Override
    public void nextTuple() {

        try {

			logic.nextTuple(this);

        } catch (Exception e) {
            log.severe("MessageProviderSpout","nextTuple",e.toString());
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void open(Map config, TopologyContext topologyContext, SpoutOutputCollector collector) {
    	RubeGoldbergPrinterLogger.getInstance().configure(config);
        MessageProviderSpout.collector.set(collector);
         try { taskId = topologyContext.getThisTaskId(); }
        catch (Throwable t) { taskId = 1; }
        logic.open(config,topologyContext,this);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream("Users",new Fields("userId"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return config;
    }

    /*
     * Unreliably emit an instance of Message to stream Messages.  
     */
	public void emitToMessages(Message message) {
		Values values = message.asValues();
		collector.get().emit("Messages",values);
	}

    /*
     * Reliably emit an instance of Message to stream Messages.
     * The second parameter is to be used as a message ID for
     * notification of message ack or fail.  
     */
	public void emitToMessages(Message message, Message messageID) {
		Values values = message.asValues();
		collector.get().emit("Messages",values,messageID);
	}

    @Override
    public void close() {
        activated = false;
        logic.close(this);
    }

    @Override
    public void ack(Object o) {
        logic.ack(o,this);
    }

    @Override
    public void fail(Object o) {
        logic.fail(o,this);
    }


    @Override
    public void activate() {
        if (!activated) {
            activated = true;
	        logic.activate(this);
        }
    }

    @Override
    public void deactivate() {
        activated = false;
        logic.deactivate(this);
    }
	
	@Override
	public int getTaskId() {
		return taskId;
	}

}
