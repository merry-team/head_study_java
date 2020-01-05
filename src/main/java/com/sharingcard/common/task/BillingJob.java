package com.sharingcard.common.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//import com.sharingcard.card.service.AccountFileService;


import javax.annotation.Resource;

@Component
public class BillingJob implements Job {
	protected static final Logger logger = LoggerFactory.getLogger(BillingJob.class);
//	@Resource
//	AccountFileService accountFileService;
	
	//@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
			logger.debug("计费开始");
//			accountFileService.billing();
			logger.debug("计费结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("计费出错"+e.getMessage());
		}
	}

}