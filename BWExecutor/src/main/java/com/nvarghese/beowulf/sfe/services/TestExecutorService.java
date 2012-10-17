package com.nvarghese.beowulf.sfe.services;

import java.net.UnknownHostException;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Datastore;
import com.nvarghese.beowulf.common.BeowulfCommonConfigManager;
import com.nvarghese.beowulf.common.ds.DataStoreUtil;
import com.nvarghese.beowulf.common.rpc.BwControllerRpcInterface;
import com.nvarghese.beowulf.common.scan.dao.WebScanDAO;
import com.nvarghese.beowulf.common.scan.model.WebScanDocument;
import com.nvarghese.beowulf.common.webtest.JobStatus;
import com.nvarghese.beowulf.common.webtest.sfe.jobs.TestJob;
import com.nvarghese.beowulf.common.webtest.sfe.jobs.TestJobDAO;
import com.nvarghese.beowulf.common.webtest.sfe.jobs.TestJobDocument;
import com.nvarghese.beowulf.sfe.SfeManager;

public class TestExecutorService {

	static Logger logger = LoggerFactory.getLogger(TestExecutorService.class);

	public void processTestJob(TestJob testJob) {

		BwControllerService bwControllerService = new BwControllerService();
		WebScanDAO webScanDAO = new WebScanDAO(SfeManager.getInstance().getDataStore());
		WebScanDocument webScanDocument = webScanDAO.getWebScanDocument(testJob.getWebScanObjId());

		try {
			BwControllerRpcInterface bwContollerRpcClient = bwControllerService.getRpcClient(webScanDocument.getBwControllerIPAddress(),
					webScanDocument.getBwControllerPort());
			Datastore ds = DataStoreUtil.createOrGetDataStore(BeowulfCommonConfigManager.getDbServers(), testJob.getDatabaseName());

			TestJobDAO testJobDAO = new TestJobDAO(ds);
			TestJobDocument testJobDocument = testJobDAO.getTestJobDocument(testJob.getTestJobObjId());
			if (testJobDocument != null) {

				try {
					testJobDocument.setJobStatus(JobStatus.PROCESSING);
					testJobDAO.updateTestJobDocument(testJobDocument);

					routeTestJob(ds, webScanDocument, testJobDocument);

					testJobDocument.setJobStatus(JobStatus.COMPLETED);
					testJobDAO.updateTestJobDocument(testJobDocument);
				} catch (TestJobException e) {
					logger.error("Failed to execute test job with id: {}. Reason: {}", testJobDocument.getId(), e.getMessage());
					e.printStackTrace();
					testJobDocument.setJobStatus(JobStatus.ERROR);
					testJobDAO.updateTestJobDocument(testJobDocument);
				}

			} else {

			}

			String reply = bwContollerRpcClient.sayHello("BW-Executor System");
			logger.info("RPC Message was sent to server. Server replied: {}", reply);

		} catch (ConfigurationException e) {
			logger.error("Failed to create/get datastore for scan instance. Reason: {}", e.getMessage(), e);
		} catch (UnknownHostException e) {
			logger.error("Failed to create RPC client for the bw-controller. Reason: {}", e.getMessage(), e);
		}

	}

	private void routeTestJob(Datastore ds, WebScanDocument webScanDocument, TestJobDocument testJobDocument) throws TestJobException {

		if(testJobDocument.get)

	}

}