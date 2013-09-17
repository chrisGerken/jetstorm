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

public class ReferenceCounterBolt implements IRichBolt, IReferenceCounterBolt {

	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private Map<String, Object> config;
    private static final IRubeGoldbergPrinterLogger log = RubeGoldbergPrinterTopology.getLogger();
	private ReferenceCounterBoltLogic logic = new ReferenceCounterBoltLogic();
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
	 *  Emits an instance of MessageReference to stream Approvals
	 */
	public void emitToApprovals(MessageReference messageReference) {
		Values values = messageReference.asValues();
		collector.emit("Approvals",anchor,values);
	}


	/*
	 *  Emits an instance of MessageReference to stream Approvals
	 */
	public void emitToApprovalsWithoutAnchor(MessageReference messageReference) {
		Values values = messageReference.asValues();
		collector.emit("Approvals",values);
	}

    @Override
    public void execute(Tuple tuple) {
    	anchor = tuple;
	    try {
	    	if ("MessageReferences".equals(tuple.getSourceStreamId())) {
	    		MessageReference messageReference = new MessageReference(tuple);
	            logic.readFromMessageReferences(messageReference, this);
	    	}
	    	if ("Totals".equals(tuple.getSourceStreamId())) {
	    		ExpectedCount expectedCount = new ExpectedCount(tuple);
	            logic.readFromTotals(expectedCount, this);
	    	}
		} catch (Exception e) { 
			log.severe("ReferenceCounterBolt", "execute", "Error executing Tuple", e); 
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
        declarer.declareStream("Approvals", MessageReference.asFields());
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
