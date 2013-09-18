package com.gerkenip.rgp.topology;

	// Begin imports
	
import com.gerkenip.rgp.spout.*;
import com.gerkenip.rgp.bolt.*;
import com.gerkenip.rgp.util.AlarmClock;
import com.gerkenip.rgp.util.RubeGoldbergPrinterLogger;
import com.gerkenip.rgp.util.IRubeGoldbergPrinterLogger;
import com.gerkenip.rgp.util.TaskHook;

import backtype.storm.Config;
import backtype.storm.ILocalCluster;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.NotAliveException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.BoltDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

	// End imports
	
public class RubeGoldbergPrinterTopology {

    static final String DEFAULT_TARGET  = "dev";

	public static boolean quiesce = false;

    private static Config config = null;
    static List<String> TARGETS = Arrays.asList(new String[] {"dev", "prod"});

    private static IRubeGoldbergPrinterLogger log = RubeGoldbergPrinterLogger.getInstance();
    
    private static ILocalCluster localCluster = new LocalCluster();

    public static void main(String[] args) throws Exception {

			// Begin topology execution code

    	String target = DEFAULT_TARGET;
    	
    	for (int i = 0; i < args.length; i++) {
    		System.out.println("args["+i+"] = "+args[i]);
    	}
     
    	if (args.length > 0 && TARGETS.contains(args[0])) {
    		target = args[0];
        } else if (args.length > 0) {
        	System.out.println("Invalid target environment specified");
        	return;
        }

        config = loadConfig(target);
       	
        if ("true".equals(config.get("backlog.monitor.enabled").toString())) {
            List<String> hooksList= new ArrayList<String>();
            hooksList.add(TaskHook.class.getName());
            config.put(Config.TOPOLOGY_AUTO_TASK_HOOKS, hooksList);
        }
        
       	if (isRunLocally()) {
       		submitTopologyLocal();
       	} else {
       		submitTopology();
       	}
       	
			// End topology execution code

    }
    
    public static void submitTopology() {
    
    	StormTopology topology = createTopology();
       	String topologyName = config.get("topology.name").toString();

       	try {
			StormSubmitter.submitTopology(topologyName, config, topology);
		} catch (AlreadyAliveException e) {
			log.severe("RubeGoldbergPrinterTopology","submitToplogy","Topology was already alive: "+e);
		} catch (InvalidTopologyException e) {
			log.severe("RubeGoldbergPrinterTopology","submitToplogy","Invalid topology: "+e);
		}

    }
    
    public static void submitTopologyLocal() {
    
    	StormTopology topology = createTopology();
       	String topologyName = config.get("topology.name").toString();

		try {
			getLocalCluster().submitTopology(topologyName, config, topology);
			
			int sleepMinutes;
			try {
				sleepMinutes = ((Integer) config.get("sleep.after.submit.minutes")).intValue();
			} catch (NumberFormatException e) {
				sleepMinutes = 1;
			}
			new AlarmClock(sleepMinutes * 60000,5*60000).go();
			
			getLocalCluster().killTopology(topologyName);
			getLocalCluster().shutdown();
		} catch (AlreadyAliveException e) {
			log.severe("RubeGoldbergPrinterTopology","submitTopologyLocal","Topology was already alive: "+e);
		} catch (InvalidTopologyException e) {
			log.severe("RubeGoldbergPrinterTopology","submitTopologyLocal","Invalid topology: "+e);
		} catch (NotAliveException e) {
			log.severe("RubeGoldbergPrinterTopology","submitTopologyLocal","Topology is not yet alive: "+e);
		}

    }

    public static Config loadConfig(String env) {
        log.fine("RubeGoldbergPrinterTopology","submitToplogy","Initializing " + env + " topology");
        Properties props = new Properties();
        Config config = new Config();
        try {
            props.load(RubeGoldbergPrinterTopology.class.getResourceAsStream("/" + env + ".properties"));
           for (Object prop : props.keySet()) {
				Object value = props.get(prop);
				try {
					// see if it's an Integer
					int intValue = Integer.parseInt(String.valueOf(value));
					config.put(prop.toString(), intValue);
				} catch (NumberFormatException e) {
					config.put(prop.toString(), value);					
				}
				
				log.fine("RubeGoldbergPrinterTopology","submitToplogy",prop.toString() + "=" + value);
			}
            config.put(Config.TOPOLOGY_FALL_BACK_ON_JAVA_SERIALIZATION, true);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static StormTopology createTopology() {

        TopologyBuilder builder = new TopologyBuilder();
		BoltDeclarer boltDeclarer;
		
		int parallelismHint = 0;

        parallelismHint = (Integer)config.get("MessageProvider.parallelismHint");
        builder.setSpout("MessageProvider", new MessageProviderSpout(), parallelismHint);

        parallelismHint = (Integer)config.get("MessageAnonymizer.parallelismHint");
        boltDeclarer = builder.setBolt("MessageAnonymizer", new MessageAnonymizerBolt(), parallelismHint);

        parallelismHint = (Integer)config.get("MessageRepeater.parallelismHint");
        boltDeclarer = builder.setBolt("MessageRepeater", new MessageRepeaterBolt(), parallelismHint);

        parallelismHint = (Integer)config.get("ReferenceCounter.parallelismHint");
        boltDeclarer = builder.setBolt("ReferenceCounter", new ReferenceCounterBolt(), parallelismHint);

        parallelismHint = (Integer)config.get("MessagePrinter.parallelismHint");
        boltDeclarer = builder.setBolt("MessagePrinter", new MessagePrinterBolt(), parallelismHint);

        return builder.createTopology();
    }
    
    public static IRubeGoldbergPrinterLogger getLogger() {
    	return log;
    }
    
    private static ILocalCluster getLocalCluster() {
    	return localCluster;
    }
    
    public static void setLocalCluster(ILocalCluster cluster) {
    	localCluster = cluster;
    }
    
    public static boolean isRunLocally() {
        return Boolean.parseBoolean(config.get("topology.run.locally").toString());
    }

	// Begin custom methods
	
	
	
	// End custom methods

}
