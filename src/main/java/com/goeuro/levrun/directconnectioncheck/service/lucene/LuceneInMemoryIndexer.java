package com.goeuro.levrun.directconnectioncheck.service.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class LuceneInMemoryIndexer implements Indexer {

    private final Logger logger = LoggerFactory.getLogger(LuceneInMemoryIndexer.class);

    @Autowired
    private LuceneIndexUtil utils;

    private Directory index;

    public void create(String dataFilePath) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        this.index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);

        logger.info("Starting indexing data file with Lucene, please wait...");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(dataFilePath), StandardCharsets.UTF_8)) {
            int numberOfLines = Integer.valueOf(br.readLine());
            for (String line; (line = br.readLine()) != null; ) {
                utils.add(writer, line);
            }
        }

        writer.commit();
        writer.close();

        stopWatch.stop();
        logger.info("Indexing finished, application ready to get incoming requests");

    }

    public boolean search(String searchStr1, String searchStr2) throws IOException, ParseException {
        boolean exist = utils.search(index, searchStr1, searchStr2);
        if (exist) {
            logger.info("Route found");
        }
        return exist;
    }

    public Directory get() throws IOException {
        return index;
    }
}
