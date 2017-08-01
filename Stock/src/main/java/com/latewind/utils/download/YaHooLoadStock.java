package com.latewind.utils.download;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.latewind.entity.stock.StockCodeName;
import com.latewind.enums.StockCode;
import com.latewind.enums.StockLoadUrl;

public class YaHooLoadStock extends AbstractDownLoadStock {

	private StockLoadUrl stockLoadUrl = StockLoadUrl.Base_URL_YAHOO;

	private final BlockingQueue<StockUrl> stockDeque = new ArrayBlockingQueue<StockUrl>(100);
	private final ExecutorService executor = Executors.newFixedThreadPool(10);
	private List<StockCodeName> stockCodeNames = null;

	private volatile boolean over = false;

	private final StockUrl POISON = new StockUrl(null, null);

	private final int CUSTOMER_NUM = 3;

	private CountDownLatch enDownLatch = new CountDownLatch(CUSTOMER_NUM);

	Logger logger = Logger.getLogger(YaHooLoadStock.class);

	public YaHooLoadStock() {

	}

	public YaHooLoadStock(List<StockCodeName> stockCodeNames) {

		this.stockCodeNames = stockCodeNames;
	}

	public void start() {
		executor.execute(new StockUrlProductor());

		for (int i = 0; i < CUSTOMER_NUM; i++)
			executor.execute(new StockUrlCustomer());

		try {
			enDownLatch.await();
			setOver(true);
			stop();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void stop() {

		System.out.println("---------------------stop---------------------");
		executor.shutdownNow();
	}

	public void load(String stockCode) {

		StockUrl stockUrl = null;
		stockUrl = getStockUrl(stockCode);
		loadUseUrl(stockUrl);

	}

	private void loadUseUrl(StockUrl stockUrl) {

		URL url = null;
		try {
			url = new URL(stockUrl.getTargetUrl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			writer = new BufferedWriter(new FileWriter(stockUrl.getStorePath()));
			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}
			System.out.println(Thread.currentThread().getName() + ":" + stockUrl.getStockCode());
			reader.close();
			writer.close();
		} catch (IOException e) {

			logger.info(stockUrl.getStockCode());
			e.printStackTrace();
		}
	}

	private StockUrl getStockUrl(String stockCode) {

		String targetUrl;
		StockUrl stockUrl = null;
		String baseUrl = stockLoadUrl.url();
		if (StockCode.SH_A.match(stockCode)) {
			System.out.println(baseUrl + stockCode + ".ss");
			targetUrl = baseUrl + stockCode + ".ss";

		} else if (StockCode.SZ_A.match(stockCode)) {
			System.out.println(baseUrl + stockCode + ".sz");
			targetUrl = baseUrl + stockCode + ".sz";

		} else {

			targetUrl = null;

		}
		stockUrl = new StockUrl(stockCode, targetUrl);
		return stockUrl;

	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	class StockUrlProductor implements Runnable {

		@Override
		public void run() {
			Iterator<StockCodeName> it = stockCodeNames.iterator();
			while (it.hasNext() && !Thread.currentThread().isInterrupted()) {

				String stockCode = it.next().getStockCode();
				StockUrl stockUrl = getStockUrl(stockCode);
				try {
					stockDeque.put(stockUrl);
				} catch (InterruptedException e) {

					Thread.currentThread().interrupt();
				}
			}

			System.out.println("-----------------------Over-----------------------");
			for (int i = 0; i < CUSTOMER_NUM; i++) {
				try {
					stockDeque.put(POISON);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	class StockUrlCustomer implements Runnable {

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					StockUrl stockUrl = stockDeque.take();
					if (stockUrl == POISON)
						break;
					loadUseUrl(stockUrl);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

			enDownLatch.countDown();

		}

	}

	class StockUrl {
		final String stockCode;
		final String targetUrl;
		final String storePath;

		public StockUrl(String stockCode, String targetUrl) {
			this.stockCode = stockCode;
			this.targetUrl = targetUrl;
			this.storePath = "F:/data/stock/" + stockCode + ".csv";

		}

		public String getStockCode() {
			return stockCode;
		}

		public String getTargetUrl() {
			return targetUrl;
		}

		public String getStorePath() {
			return storePath;
		}

	}

}
