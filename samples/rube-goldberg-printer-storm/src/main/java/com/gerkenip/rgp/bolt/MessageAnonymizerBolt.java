package com.gerkenip.rgp.bolt;

import com.gerkenip.rgp.bean.*;
import com.gerkenip.rgp.logic.*;
import com.gerkenip.rgp.topology.RubeGoldbergPrinterTopology;
import com.gerkenip.rgp.util.IRubeGoldbergPrinterLogger;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map; 

public class MessageAnonymizerBolt implements IRichBolt, IMessageAnonymizerBolt {

	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private Map<String, Object> config;
    private static final IRubeGoldbergPrinterLogger log = RubeGoldbergPrinterTopology.getLogger();
	private MessageAnonymizerBoltLogic logic = new MessageAnonymizerBoltLogic();
	private Tuple anchor = null;
	private int taskId;	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void prepare(Map config, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.config = config;
         try { taskId = context.getThisTaskId(); }
        catch (Throwable t) { taskId = 1; }
        logic.prepare(config,context,this);		
    }

	/*
	 *  Emits an instance of AnonymizedMessage to stream Anonymous
	 */
	public void emitToAnonymous(AnonymizedMessage anonymizedMessage) {
		Values values = anonymizedMessage.asValues();
		collector.emit("Anonymous",anchor,values);
	}


	/*
	 *  Emits an instance of AnonymizedMessage to stream Anonymous
	 */
	public void emitToAnonymousWithoutAnchor(AnonymizedMessage anonymizedMessage) {
		Values values = anonymizedMessage.asValues();
		collector.emit("Anonymous",values);
	}

	/*
	 *  Emits an instance of ExpectedCount to stream Totals
	 */
	public void emitToTotals(ExpectedCount expectedCount) {
		Values values = expectedCount.asValues();
		collector.emit("Totals",anchor,values);
	}


	/*
	 *  Emits an instance of ExpectedCount to stream Totals
	 */
	public void emitToTotalsWithoutAnchor(ExpectedCount expectedCount) {
		Values values = expectedCount.asValues();
		collector.emit("Totals",values);
	}

    @Override
    public void execute(Tuple tuple) {
    	anchor = tuple;
	    try {
	    	if ("Messages".equals(tuple.getSourceStreamId())) {
	    		Message message = new Message(tuple);
	            logic.readFromMessages(message, this);
	    	}
		} catch (Exception e) { 
			log.severe("MessageAnonymizerBolt", "execute", "Error executing Tuple", e); 
	    }
	}
	
    @Override
    /*
    *  NOTE: This method is not guaranteed to get called! Do not depend on it!
    */
    public void cleanup() {
		logic.cleanup(this);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream("Anonymous", AnonymizedMessage.asFields());
        declarer.declareStream("Totals", ExpectedCount.asFields());
    }
    
    public void ack() {
    	collector.ack(anchor);
    }
    
    public void fail() {
    	collector.fail(anchor);
    }

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return config;
	}
	
	@Override
	public int getTaskId() {
		return taskId;
	}

}
