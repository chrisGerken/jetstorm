package com.gerkenip.rgp.logic;

	// Begin imports 

import com.gerkenip.rgp.bean.*;
import com.gerkenip.rgp.bolt.IMessageRepeaterBolt;
import com.gerkenip.rgp.topology.RubeGoldbergPrinterTopology;
import com.gerkenip.rgp.util.RubeGoldbergPrinterLogger;
import com.gerkenip.rgp.util.IRubeGoldbergPrinterLogger;

import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Tuple;

import java.io.Serializable;
import java.util.Map;

	// End imports 

public class MessageRepeaterBoltLogic implements Serializable {

	private static final long serialVersionUID = 1L;
		
		// Begin declarations 

    private static final IRubeGoldbergPrinterLogger log = RubeGoldbergPrinterTopology.getLogger();

		// End declarations 
		
    public void readFromTotals(ExpectedCount expectedCount, IMessageRepeaterBolt bolt) {

			// Begin readFromTotals() logic 


			// End readFromTotals() logic 

    }

    public void prepare(Map conf, TopologyContext context, IMessageRepeaterBolt bolt) {

			// Begin prepare() logic 


			// End prepare() logic 

    }

	/*
    *  NOTE: This method is not guaranteed to get called! Do not depend on it!
	*/
    public void cleanup(IMessageRepeaterBolt bolt) {

			// Begin cleanup() logic 


			// End cleanup() logic 

    }

// Begin custom methods 


// End custom methods 

}
