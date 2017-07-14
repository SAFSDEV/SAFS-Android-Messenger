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
package org.safs.android.messenger;

import org.safs.sockets.SocketProtocol;

import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
/**
 * It is used to handle the messages from 'Test Runner'.
 * 
 * @author Carl Nagle, SAS Institute, Inc.
 * @since   FEB 04, 2012	(CarlNagle)	Initial version
 *   <br>	APR 25, 2013	(LeiWang)	Handle message of big size.
 */
public class MessengerHandler extends MultipleParcelsHandler{

	MessengerListener listener = null;
	
	public MessengerHandler(Looper looper, MessengerListener listener) {
		super(looper, listener);
		this.listener = listener;
	}

	protected void debug(String message){
		listener.onMessengerDebug("HANDLER: "+ message);
	}

	public void handleWholeMessage(Message msg){
				
		switch (msg.what){
											
			case MessageUtil.ID_ENGINE_READY:
				listener.onEngineReady();
				break;
			
			case MessageUtil.ID_ENGINE_RUNNING:
				listener.onEngineRunning();
				break;
			
			case MessageUtil.ID_ENGINE_RESULT:
				listener.onEngineResult(msg.arg1, MessageUtil.getParcelableMessage((Parcelable) msg.obj));
				break;
									
			case MessageUtil.ID_ENGINE_RESULTPROPS:
				listener.onEngineResultProps(MessageUtil.getParcelableProps((Parcelable) msg.obj));
				break;
									
			case MessageUtil.ID_ENGINE_SHUTDOWN:
				listener.onEngineShutdown(SocketProtocol.STATUS_SHUTDOWN_REMOTE_CLIENT);
				break;
				
			case MessageUtil.ID_REGISTER_ENGINE:
				listener.onEngineRegistered(msg.replyTo);
				break;

			case MessageUtil.ID_UNREGISTER_ENGINE:
				listener.onEngineUnRegistered();
				break;

			case MessageUtil.ID_ENGINE_DEBUG:
				listener.onEngineDebug(MessageUtil.getParcelableMessage((Parcelable)msg.obj));
				break;

			case MessageUtil.ID_ENGINE_EXCEPTION:
				listener.onEngineException(MessageUtil.getParcelableMessage((Parcelable)msg.obj));
				break;

			case MessageUtil.ID_ENGINE_MESSAGE:
				listener.onEngineMessage(MessageUtil.getParcelableMessage((Parcelable)msg.obj));
				break;
		}

	}
}
