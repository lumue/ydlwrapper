package io.github.lumue.getdown.webapp.webapi;

import io.github.lumue.getdown.core.download.DownloadService;
import io.github.lumue.getdown.core.download.job.DownloadJob;
import io.github.lumue.getdown.core.download.task.DownloadTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/download")
@CrossOrigin
public class DownloadJsonRpcController {

	private final DownloadService downloadService;


	@Autowired
	public DownloadJsonRpcController(DownloadService downloadService) {
		super();
		this.downloadService = downloadService;
	}

	private final static Logger LOGGER = LoggerFactory.getLogger(DownloadJsonRpcController.class);

	/**
	 * Add and start a download
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public DownloadJobView addDownload(@RequestParam(value = "url", required = true) String url) {

        LOGGER.debug("adding and starting download job for {}", url);

		DownloadTask download = downloadService.addDownloadTask(url);
		DownloadJob job=downloadService.startDownload(download.getHandle());

        LOGGER.debug("download job for {} added and started", url);

		return DownloadJobView.wrap(job);
	}

	/**
	 * Add and start a download
	 * @param url
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST)
	public DownloadJobView postDownload(@RequestBody String url) {
		return this.addDownload(url);
	}

	/**
	 * stop and remove all downloads
	 */
	@RequestMapping( method = RequestMethod.DELETE)
	public void deleteDownloads() {
		downloadService.removeAll();
	}

	/**
	 * get all downloads
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Iterable<DownloadJobView> listDownloads() {

		return downloadService
				.streamDownloads()
				.map(DownloadJobView::wrap)
				.collect(Collectors.toList());
	}



	/**
	 * get waiting
	 * @return
	 */
	@RequestMapping(value = "/state/waiting", method = RequestMethod.GET)
	public Iterable<DownloadJobView> listWaitingDownloads() {
		return downloadService
				.streamWaitingDownloads()
				.map(DownloadJobView::wrap)
				.collect(Collectors.toList());
	}

	/**
	 * get running downloads
	 * @return
	 */
	@RequestMapping(value = "/state/running", method = RequestMethod.GET)
	public Iterable<DownloadJobView> listRunningDownloads() {
		return downloadService
				.streamRunningDownloads()
				.map(DownloadJobView::wrap)
				.collect(Collectors.toList());
	}

	
	@RequestMapping(value = "/{handle}", method = RequestMethod.GET)
	public DownloadJobView getDownload(@PathVariable String handle) {
		DownloadJob downloadJob=downloadService.getDownload(handle);
		return DownloadJobView.wrap(downloadJob);
	}

	@RequestMapping(value = "/{handle}", method = RequestMethod.DELETE)
	public void removeDownload(@PathVariable String handle) {
		downloadService.removeDownload(handle);
	}
	
	@RequestMapping(value = "/{handle}/cancel", method = RequestMethod.POST)
	public void cancelDownload(@PathVariable String handle) {
        LOGGER.debug("canceling download job with handle {}", handle);
		downloadService.cancelDownload(handle);
	}

	@RequestMapping(value = "/{handle}/restart", method = RequestMethod.POST)
	public void restartDownload(@PathVariable String handle) {
        LOGGER.debug("restarting download job with handle {}", handle);
		downloadService.restartDownload(handle);
	}
	



}
