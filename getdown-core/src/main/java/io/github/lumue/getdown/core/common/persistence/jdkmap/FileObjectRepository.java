package io.github.lumue.getdown.core.common.persistence.jdkmap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import io.github.lumue.getdown.core.common.persistence.HasIdentity;
import io.github.lumue.getdown.core.common.persistence.ObjectBuilder;
import io.github.lumue.getdown.core.common.persistence.ObjectRepository;
import io.github.lumue.getdown.core.download.job.DownloadJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public abstract class FileObjectRepository<B extends ObjectBuilder<V>, K, V extends HasIdentity<K>> implements
		ObjectRepository<B, K, V> {

	private final String filename;

	private Map<K, V> objectMap;

	private final static Logger LOGGER = LoggerFactory.getLogger(FileObjectRepository.class);

	ExecutorService flushExecutorService = Executors.newSingleThreadExecutor();


	public FileObjectRepository(String filename) {
		this.filename = filename + ".json";
		this.objectMap = new ConcurrentHashMap<K, V>();
		restore();
	}

	@Override
	public Stream<V> stream() {
		return this.objectMap.values().stream();
	}


	@Override
	public V create(B builder) {
		V job = builder.build();
		objectMap.put(job.getHandle(), job);
		triggerFlush();
		return job;
	}


	@Override
	public List<V> list() {
		return java.util.Collections.unmodifiableList(new ArrayList<>(objectMap.values()));
	}


	@Override
	public void remove(K handle) {
		objectMap.remove(handle);
		triggerFlush();
	}

	@Override
	public V get(K handle) {
		return objectMap.get(handle);
	}

	protected Map<K, V> getMap() {
		return objectMap;
	}

	@SuppressWarnings("unchecked")
	private synchronized void restore() {
		
		if(!(new File(filename).exists()))
			return;
		
		byte[] contentSnapshot = loadContent();

		if (contentSnapshot==null)
			return;

		try {
			ObjectInputStream inputStream=new ObjectInputStream(new ByteArrayInputStream(contentSnapshot));
			objectMap=(ConcurrentHashMap<K, V>) inputStream.readObject();
			inputStream.close();
		} catch (Exception e)
		{
			LOGGER.error("Object deserialisation failed during restore", e);
		}


	}



	private byte[] loadContent() {
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(filename));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return encoded;
	}

	private synchronized void triggerFlush() {
		final byte[] contentSnapshot = createContentSnapshot();

		if (contentSnapshot == null)
			return;

		flushExecutorService.execute(new Runnable() {
			@Override
			public void run() {
				flush(contentSnapshot);
			}
		});
	}

	protected byte[] createContentSnapshot() {
		try {
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(objectMap);
			objectOutputStream.close();
			return outputStream.toByteArray();
		}catch (IOException e)
		{
			LOGGER.error("Object serialisation failed during createContentSnapshot", e);
			return null;
		}
	}



	private synchronized void flush(final byte[] contentSnapshot) {
		try
		{
			Files.write(Paths.get(filename), contentSnapshot);
		} catch (IOException e)
		{
			LOGGER.error("Object serialisation failed during flush", e);
		} 
	}
}