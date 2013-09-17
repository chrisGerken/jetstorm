package com.gerkenip.rgp.spout;

import com.gerkenip.rgp.bean.*;

import java.util.Map;

public interface IMessageProviderSpout {

    public Map<String, Object> getComponentConfiguration();

    /*
     * Unreliably emit an instance of Message to stream Messages.  
     */
	public void emitToMessages(Message message);


    /*
     * Reliably emit an instance of Message to stream Messages.
     * The second parameter is to be used as a message ID for
     * notification of message ack or fail.  
     */
	public void emitToMessages(Message message, Message messageID);

	public int getTaskId();

}
