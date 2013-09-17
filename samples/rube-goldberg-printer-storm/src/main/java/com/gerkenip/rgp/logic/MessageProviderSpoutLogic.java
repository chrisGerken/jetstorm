package com.gerkenip.rgp.logic;

	// Begin imports 

import com.gerkenip.rgp.bean.*;
import com.gerkenip.rgp.spout.IMessageProviderSpout;
import com.gerkenip.rgp.util.RubeGoldbergPrinterLogger;
import com.gerkenip.rgp.util.IRubeGoldbergPrinterLogger;

import backtype.storm.task.TopologyContext;

import java.io.Serializable;
import java.util.Map;

	// End imports 

public class MessageProviderSpoutLogic implements Serializable {

		// Begin declarations
		 
	private static final long serialVersionUID = 1L;

    private static final IRubeGoldbergPrinterLogger log = RubeGoldbergPrinterLogger.getInstance();
    private boolean written = false;

		// End declarations 

    public void nextTuple(final IMessageProviderSpout spout) {

			// Begin nextTuple() logic 
			
        try {


        	
       		Thread.sleep(100);

    	} catch (InterruptedException e) {
    		log.finest("MessageProviderSpoutLogic","nextTuple", e.toString());
        } catch (Exception e) {
       		log.severe("MessageProviderSpoutLogic","nextTuple", e.toString());
        }

			// End nextTuple() logic 

    }

    public void open(Map map, TopologyContext topologyContext, IMessageProviderSpout spout) {

			// Begin open() logic 
 
 
			// End open() logic 

    }

    public void close(IMessageProviderSpout spout) {

			// Begin close() logic 


			// End close() logic 

    }

    public void activate(IMessageProviderSpout spout) {

			// Begin activate() logic 


			// End activate() logic 

    }

    public void deactivate(IMessageProviderSpout spout) {

			// Begin deactivate() logic 


			// End deactivate() logic 

    }

    public void ack(Object o, IMessageProviderSpout spout) {

			// Begin ack() logic 


			// End ack() logic 

    }

    public void fail(Object o, IMessageProviderSpout spout) {

			// Begin fail() logic 


			// End fail() logic 

    }

// Begin custom methods 


// End custom methods 

}
