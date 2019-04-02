package com.tiedros.project.bgjobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.tiedros.project.dao.BookmarkDAO;
import com.tiedros.project.entity.WebLink;
import com.tiedros.project.entity.WebLink.DownloadStatus;
import com.tiedros.project.util.HttpConnect;
import com.tiedros.project.util.IOUtil;

public class WebPageDownloaderTask  implements Runnable{
	
	private static BookmarkDAO dao= new BookmarkDAO();
	private static final long TIME_FRAME=3000000000L;//3 seconds
	private boolean downloadAll=false;
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(5);
	
	private static class Downloader<T extends WebLink> implements Callable<T> {
		private T weblink;
		public Downloader(T weblink) {
			this.weblink = weblink;
		}
		public T call() {
			try {
				if (!weblink.getUrl().endsWith(".pdf")) {
					weblink.setDownloadStatus(WebLink.DownloadStatus.FAILED);
					String htmlPage = HttpConnect.download(weblink.getUrl());
				weblink.setHtmlPage(htmlPage);
				}else {
					weblink.setDownloadStatus(WebLink.DownloadStatus.NOT_ELIGIBLE);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return weblink;
		}
	}
	
	
	public WebPageDownloaderTask(boolean downloadAll) {
		this.downloadAll=downloadAll;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			
			// Get weblinks 
			List<WebLink> webLink = getWebLinks();
			
			//Download concurrently
			if(webLink.size() > 0) {
				download(webLink);
			}else {
				System.out.println("No new weblinks to download");
			}
			//wait
			try {
				TimeUnit.SECONDS.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		downloadExecutor.shutdown();
		
	}

	private void download(List<WebLink> webLink) {
		List<Downloader<WebLink>> task=getTask(webLink);
		List<Future<WebLink>> futures= new ArrayList<>();
		
		try {
			futures=downloadExecutor.invokeAll(task,TIME_FRAME,TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Future<WebLink> future : futures) {
			try {
				if (!future.isCancelled()) {
					WebLink webLink2=future.get();
					String webPage=webLink2.getHtmlPage();
					if(webPage != null) {
						IOUtil.write(webPage, webLink2.getId());
						webLink2.setDownloadStatus(WebLink.DownloadStatus.SUCCESS);
						System.out.println("Download Success: "+ webLink2.getUrl());
					}else {
						System.out.println("Web page not downloaded: "+ webLink2.getUrl());
						
					}
				} else {
					System.out.println("\n\nTask is cancelled --> " + Thread.currentThread());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	private List<Downloader<WebLink>> getTask(List<WebLink> webLink) {
		List<Downloader<WebLink>> task= new ArrayList<>();
	 
		for(WebLink link: webLink) {
			task.add(new Downloader<WebLink>(link));
		}
		return task;
	}

	private List<WebLink> getWebLinks() {
		List<WebLink> webLinks=null;
		
		if(downloadAll) {
			webLinks=dao.getAllWebLinks();
			downloadAll=false;
		}else {
			webLinks=dao.getWebLinks(WebLink.DownloadStatus.NOT_ATTEMPTED);
		}
		return webLinks;
	}
}
