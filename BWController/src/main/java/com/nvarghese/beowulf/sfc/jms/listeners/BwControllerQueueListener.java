package com.nvarghese.beowulf.sfc.jms.listeners;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nvarghese.beowulf.common.jobs.NewScanJob;
import com.nvarghese.beowulf.sfc.services.NewScanService;

public class BwControllerQueueListener implements MessageListener {

	static Logger logger = LoggerFactory.getLogger(BwControllerQueueListener.class);

	@Override
	public void onMessage(Message message) {

		if (message instanceof ObjectMessage) {
			ObjectMessage objMessage = (ObjectMessage) message;
			routeObjectMessage(objMessage);

		}

	}

	private void routeObjectMessage(ObjectMessage objMessage) {

		try {
			Object object = objMessage.getObject();
			if (object instanceof NewScanJob) {

				final NewScanJob newScanJob = (NewScanJob) object;				
				new Thread() {

					public void run() {

						NewScanService newScanService = new NewScanService();
						newScanService.startScan(newScanJob);
					}
				}.start();

			}
		} catch (JMSException e) {

		}

	}

}
