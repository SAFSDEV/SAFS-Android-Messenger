/**
 * Copyright (C) SAS Institute, All rights reserved.
 * General Public License: https://www.gnu.org/licenses/gpl-3.0.en.html
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
**/
package org.safs.android.messenger.client;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.Properties;

import org.safs.android.messenger.MessageUtil;
import org.safs.android.messenger.MultipleParcelsHandler;

import android.os.Message;
import android.os.Parcelable;

/**
 * 
 * @see org.safs.android.messenger.client.MessengerRunner
 * @author Carl Nagle, SAS Institute, Inc.
 * @since   FEB 04, 2012	(CarlNagle)	Initial version
 *   <br>	APR 25, 2013	(LeiWang)	Handle message of big size. 
 */
public class MessengerHandler extends MultipleParcelsHandler{

	MessengerListener listener = null;
	
	public MessengerHandler(MessengerListener listener) {
		super(listener);
		this.listener = listener;
	}

	protected void debug(String message){
		if(listener!=null){
			listener.onReceiveDebug(message);
		}else{
			System.err.println(message);
		}
	}
	
	@Override
	public void handleWholeMessage(Message msg){
		
		listener.prepareNotification(msg.what);
		switch (msg.what){
			case MessageUtil.ID_ENGINE_DISPATCHPROPS:
			try{
				Properties props = new Properties();
					props.load(new CharArrayReader(MessageUtil.getParcelableProps((Parcelable)msg.obj)));
					listener.onRemoteDispatchProps(props);
				}catch(NullPointerException x){
					debug("DispatchProps message did NOT have required Properties Parcel!");
				}catch(IOException x){
					debug("DispatchProps IOException "+ x.getMessage());
				}
				break;
			case MessageUtil.ID_ENGINE_DISPATCHFILE:
				listener.onRemoteDispatchFile(MessageUtil.getParcelableMessage((Parcelable)msg.obj));
				break;
	
			case MessageUtil.ID_ENGINE_SHUTDOWN:
				listener.onRemoteEngineShutdown();
				break;
	
			case MessageUtil.ID_SERVER_SHUTDOWN:
				listener.onServiceShutdown();
				break;
	
			case MessageUtil.ID_ENGINE_MESSAGE:
				listener.onRemoteMessage(MessageUtil.getParcelableMessage((Parcelable)msg.obj));
				break;
	
			case MessageUtil.ID_SERVER_CONNECTED:
				listener.onRemoteConnected();
				break;
	
			case MessageUtil.ID_SERVER_DISCONNECTED:
				listener.onRemoteDisconnected();
				break;
		}

	}
}
