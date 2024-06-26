package io.github.lumue.getdown.core.download.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Takes care of jobs that should be executed.
 * jobs are added to a queue, and then taken one by one and passed to
 * the prepare and download executors.
 */
public class AsyncValidateTaskJobRunner implements Runnable {


	private final ThreadPoolTaskExecutor prepareExecutor;

	private final AtomicBoolean shouldRun = new AtomicBoolean(false);

	//queue capacity can be small.. running a job should be virtually nonblocking, since prepare an download are implemented as async operations
	private final BlockingQueue<ValidateTaskJob> taskQueue = new PriorityBlockingQueue<>(10, new Comparator<ValidateTaskJob>() {
		@Override
		public int compare(ValidateTaskJob o1, ValidateTaskJob o2) {
			return o1.getTask().getCreationTime().compareTo(o2.getTask().getCreationTime());
		}
	});


	private static Logger LOGGER = LoggerFactory.getLogger(AsyncValidateTaskJobRunner.class);
	private final Executor jobRunner;


	public AsyncValidateTaskJobRunner(
			int maxThreadsPrepare) {
		super();
		this.prepareExecutor = executor("validate-task-executor",maxThreadsPrepare);
		this.jobRunner = executor("validate-job-runner",1);
	}


	private void runValidation(final ValidateTaskJob validateJob) {
		AsyncValidateTaskJobRunner.LOGGER.debug("validating " +validateJob);
			CompletableFuture.runAsync(validateJob, prepareExecutor);
			
	}

	public void submitJob(final ValidateTaskJob validateTaskJob) {
		String jobUrl = validateTaskJob.getTask().getSourceUrl();
		AsyncValidateTaskJobRunner.LOGGER.debug("queueing job " + jobUrl + " for validation");


		taskQueue.add(validateTaskJob);
	}
	
	
	
	
	
	private static ThreadPoolTaskExecutor executor(String name, Integer threads) {
		final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix(name);
		threadPoolTaskExecutor.setThreadGroupName(name);
		threadPoolTaskExecutor.setCorePoolSize(threads);
		threadPoolTaskExecutor.setMaxPoolSize(threads);
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}

	@Override
	public void run() {
		while (shouldRun.get()) {
			try {
				runValidation(taskQueue.take());
			} catch (InterruptedException e) {
				LOGGER.warn("interrupted while accessing task validation queue", e);
			}
		}
	}

	public void stop() {
		shouldRun.compareAndSet(true, false);
	}


	
	@PostConstruct
	public void start() {
		shouldRun.compareAndSet(false, true);
		jobRunner.execute(this);
	}
}
