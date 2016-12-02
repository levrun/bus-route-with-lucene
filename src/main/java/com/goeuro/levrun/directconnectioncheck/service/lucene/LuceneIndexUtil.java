package com.goeuro.levrun.directconnectioncheck.service.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class LuceneIndexUtil {

    private final Logger logger = LoggerFactory.getLogger(LuceneIndexUtil.class);

    public LuceneIndexUtil() {
    }

    public static final String ROUTES = "routes";

    public void add(IndexWriter writer, String content) throws IOException {
        Document doc = new Document();
        doc.add(new TextField(ROUTES, content, Store.YES));
        writer.addDocument(doc);
    }

    public boolean search(Directory index, String searchString1, String searchString2)
            throws IOException, ParseException {

        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        Query query = new QueryParser(ROUTES, new StandardAnalyzer()).parse(searchString1 + " AND " + searchString2);
        TopDocs result = searcher.search(query, 1);
        ScoreDoc[] hits = result.scoreDocs;
        for (ScoreDoc hit : hits) {
            Document document = searcher.doc(hit.doc);
            StringBuilder sb = new StringBuilder("Result");
            sb.append(" | ").append(ROUTES).append(":").append(document.get(ROUTES));
            logger.info(sb.toString());
        }

        if(hits.length == 1) {
            return true;
        } else {
            return false;
        }

    }

}