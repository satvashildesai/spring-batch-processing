package com.example.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationImpl implements JobExecutionListener {
	private Logger log = LoggerFactory.getLogger(JobCompletionNotificationImpl.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("Before job execution: " + jobExecution.getJobId());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("After job execution: " + jobExecution.getJobId());
	}

}
