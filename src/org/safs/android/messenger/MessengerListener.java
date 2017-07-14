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

import android.os.Messenger;

public interface MessengerListener extends MultipleParcelListener{

	public void onMessengerDebug(String message);
	public void onEngineDebug(String message);
	public void onEngineException(String message);
	public void onEngineMessage(String message);
	public void onEngineResult(int statuscode, String statusinfo);
	public void onEngineResultProps(char[] props);
	public void onEngineShutdown(int cause);
	public void onEngineReady();
	public void onEngineRegistered(Messenger messenger);
	public void onEngineUnRegistered();
	public void onEngineRunning();
}
