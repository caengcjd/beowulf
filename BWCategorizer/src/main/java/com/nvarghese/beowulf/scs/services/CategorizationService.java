package com.nvarghese.beowulf.scs.services;

import java.net.UnknownHostException;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Datastore;
import com.nvarghese.beowulf.common.BeowulfCommonConfigManager;
import com.nvarghese.beowulf.common.ds.DataStoreUtil;
import com.nvarghese.beowulf.common.http.txn.AbstractHttpTransaction;
import com.nvarghese.beowulf.common.http.txn.HttpTxnDAO;
import com.nvarghese.beowulf.common.http.txn.HttpTxnDocument;
import com.nvarghese.beowulf.common.rpc.BwControllerRpcInterface;
import com.nvarghese.beowulf.common.scan.dao.WebScanDAO;
import com.nvarghese.beowulf.common.scan.model.WebScanDocument;
import com.nvarghese.beowulf.common.webtest.CategorizerType;
import com.nvarghese.beowulf.common.webtest.JobStatus;
import com.nvarghese.beowulf.common.webtest.scs.jobs.CategorizationJobDAO;
import com.nvarghese.beowulf.common.webtest.scs.jobs.CategorizationJobDocument;
import com.nvarghese.beowulf.common.webtest.scs.jobs.CategorizerJob;
import com.nvarghese.beowulf.scs.ScsManager;

public class CategorizationService {

	static Logger logger = LoggerFactory.getLogger(CategorizationService.class);

	public void processCategorization(CategorizerJob categorizerJob) {

		BwControllerService bwControllerService = new BwControllerService();
		WebScanDAO webScanDAO = new WebScanDAO(ScsManager.getInstance().getDataStore());
		WebScanDocument webScanDocument = webScanDAO.getWebScanDocument(categorizerJob.getWebScanObjId());

		try {
			BwControllerRpcInterface bwContollerRpcClient = bwControllerService.getRpcClient(webScanDocument.getBwControllerIPAddress(),
					webScanDocument.getBwControllerPort());
			Datastore ds = DataStoreUtil.createOrGetDataStore(BeowulfCommonConfigManager.getDbServers(), categorizerJob.getDatabaseName());

			CategorizationJobDAO categJobDAO = new CategorizationJobDAO(ds);
			CategorizationJobDocument categJobDocument = categJobDAO.getCategorizationJobDocument(categorizerJob.getCategorizerJobObjId());
			if (categJobDocument != null) {

				try {
					categJobDocument.setJobStatus(JobStatus.PROCESSING);
					categJobDAO.updateCategorizationJobDocument(categJobDocument);

					routeCategorizationJob(ds, categJobDocument);

					categJobDocument.setJobStatus(JobStatus.COMPLETED);
					categJobDAO.updateCategorizationJobDocument(categJobDocument);
				} catch (CategorizationJobException e) {
					logger.error("Failed to execute categ job with id: {}. Reason: {}", categJobDocument.getId(), e.getMessage());
					e.printStackTrace();
					categJobDocument.setJobStatus(JobStatus.ERROR);
					categJobDAO.updateCategorizationJobDocument(categJobDocument);
				}

			} else {

			}

			String reply = bwContollerRpcClient.sayHello("BW-Categorization System");
			logger.info("RPC Message was sent to server. Server replied: {}", reply);

		} catch (ConfigurationException e) {
			logger.error("Failed to create/get datastore for scan instance. Reason: {}", e.getMessage(), e);
		} catch (UnknownHostException e) {
			logger.error("Failed to create RPC client for the bw-controller. Reason: {}", e.getMessage(), e);
		}

	}

	private void routeCategorizationJob(Datastore ds, CategorizationJobDocument categJobDocument) throws CategorizationJobException {

		if (categJobDocument.getCategorizerType() == CategorizerType.META) {
			processMetaCategorization(ds, categJobDocument);
		}

	}

	private void processMetaCategorization(Datastore ds, CategorizationJobDocument categJobDocument) {

		HttpTxnDAO txnDAO = new HttpTxnDAO(ds);
		HttpTxnDocument httpTxnDocument = txnDAO.getHttpTxnDocument(categJobDocument.getTxnObjId());
		if (httpTxnDocument != null) {

			AbstractHttpTransaction httpTransaction = AbstractHttpTransaction.getObject(httpTxnDocument);
			doProcessMetaCategorization(httpTransaction);

		} else {

		}

	}

	private void doProcessMetaCategorization(AbstractHttpTransaction httpTransaction) {

		logger.info("Running meta categorization on transaction with id: {}", httpTransaction.getObjId());

	}

}