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

import org.safs.sockets.Message;

/** 
 *  
 * @author Lei Wang, SAS Institute, Inc
 * @since  Feb 16, 2012
 */
public class MessageResult {
	/**
	 * statuscode can be one of constants like STATUS_REMOTERESULT_XXX defined in MessageUtil
	 * @see org.safs.android.messenger.MessageUtil
	 */
	private int statuscode;
	/**
	 * statusinfo is any string to describe the running resutlt
	 * 
	 */	
	private String statusinfo;
	
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getStatusinfo() {
		return statusinfo;
	}
	public void setStatusinfo(String statusinfo) {
		this.statusinfo = statusinfo;
	}
	
	/**
	 * Reset or create a new MessageResult preset with STATUS_REMOTERESULT_OK.
	 * @param result MessageResult to be preset to OK.  If null a new MessageResult 
	 * will be created.
	 * @return  MessageResult reset with STATUS_REMOTERESULT_OK
	 * @see org.safs.sockets.Message#STATUS_REMOTERESULT_OK
	 */
	public static MessageResult getSuccessTestResult(MessageResult result){
		MessageResult myresult = null;
		if(result==null){
			myresult = new MessageResult();
		}else{
			myresult = result;
		}
		myresult.resetTestResult();
		myresult.setStatuscode(Message.STATUS_REMOTERESULT_OK);
		return myresult;
	}

	/**
	 * Reset or create a new MessageResult preset with STATUS_REMOTERESULT_FAIL.
	 * @param result MessageResult to be preset to FAIL.  If null a new MessageResult 
	 * will be created.
	 * @return  MessageResult reset with STATUS_REMOTERESULT_FAIL
	 * @see org.safs.sockets.Message#STATUS_REMOTERESULT_FAIL
	 */
	public static MessageResult getFailTestResult(MessageResult result){
		MessageResult myresult = null;
		if(result==null){
			myresult = new MessageResult();
		}else{
			myresult = result;
		}
		myresult.resetTestResult();
		myresult.setStatuscode(Message.STATUS_REMOTERESULT_FAIL);
		return myresult;
	}
	
	/**
	 * Reset statuscode to STATUS_REMOTERESULT_UNKNOWN.
	 * Reset statusinfo to an empty string ("").
	 * @see org.safs.sockets.Message#STATUS_REMOTERESULT_UNKNOWN
	 */
	public void resetTestResult(){
		statuscode = Message.STATUS_REMOTERESULT_UNKNOWN;
		statusinfo = "";
	}
	
}
