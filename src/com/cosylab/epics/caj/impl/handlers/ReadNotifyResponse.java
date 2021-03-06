/*
 * Copyright (c) 2004 by Cosylab
 *
 * The full license specifying the redistribution, modification, usage and other
 * rights and obligations is included with the distribution of this project in
 * the file "LICENSE-CAJ". If the license is not included visit Cosylab web site,
 * <http://www.cosylab.com>.
 *
 * THIS SOFTWARE IS PROVIDED AS-IS WITHOUT WARRANTY OF ANY KIND, NOT EVEN THE
 * IMPLIED WARRANTY OF MERCHANTABILITY. THE AUTHOR OF THIS SOFTWARE, ASSUMES
 * _NO_ RESPONSIBILITY FOR ANY CONSEQUENCE RESULTING FROM THE USE, MODIFICATION,
 * OR REDISTRIBUTION OF THIS SOFTWARE.
 */

package com.cosylab.epics.caj.impl.handlers;

import gov.aps.jca.CAStatus;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import com.cosylab.epics.caj.CAJContext;
import com.cosylab.epics.caj.impl.Transport;
import com.cosylab.epics.caj.impl.requests.ReadNotifyRequest;

/**
 * @author <a href="mailto:matej.sekoranjaATcosylab.com">Matej Sekoranja</a>
 * @version $id$
 */
public class ReadNotifyResponse extends AbstractCAJResponseHandler {

	/**
	 * @param context
	 */
	public ReadNotifyResponse(CAJContext context) {
		super(context, "Read notify response");
	}

	/**
	 * @see com.cosylab.epics.caj.impl.handlers.AbstractCAJResponseHandler#internalHandleResponse(java.net.InetSocketAddress, com.cosylab.epics.caj.impl.Transport, java.nio.ByteBuffer[])
	 */
	protected void internalHandleResponse(
		InetSocketAddress responseFrom,
		Transport transport,
		ByteBuffer[] response) {
		
		//NotifyResponseRequest nrr = (NotifyResponseRequest)context.getResponseRequest(parameter2);
		ReadNotifyRequest nrr = (ReadNotifyRequest)context.getResponseRequest(parameter2);
		if (nrr == null)
			return;
				
		int status;
		if (transport.getMinorRevision() < 1)
			status = CAStatus.NORMAL.getStatusCode();
		else
			status = parameter1;
			
		nrr.response(status, dataType, dataCount, response[1]);					
		
	}

}
