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

import java.util.Properties;

import org.safs.android.messenger.MultipleParcelListener;
import org.safs.sockets.DebugListener;
/** 
 * 
 * @author Carl Nagle, SAS Institute, Inc.
 * @since  Feb 02, 2012
 *
 */
public interface MessengerListener extends DebugListener, MultipleParcelListener{

	public void prepareNotification(int what);
	
	public void onRemoteConnected();
	public void onRemoteDisconnected();
	
	public void onRemoteDispatchFile(String filepath);
	public void onRemoteDispatchProps(Properties props);
	
	public void onRemoteMessage(String message);

	/** Notification that the Remote Controller has shutdown and is no longer available. */
	public void onRemoteShutdown();
	/** Notification that the Messenger Service has shutdown and is no longer available. */
	public void onServiceShutdown();
	/** Remote request/command to tell the engine to perform a normal shutdown. */
	public void onRemoteEngineShutdown();
	
}
