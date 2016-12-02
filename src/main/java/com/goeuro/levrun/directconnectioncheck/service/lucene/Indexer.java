package com.goeuro.levrun.directconnectioncheck.service.lucene;

import org.apache.lucene.store.Directory;

interface Indexer {

    void create(String dataFilePath) throws Exception;
    Directory get() throws Exception;
    boolean search(String searchStr1, String searchStr2) throws Exception;

}
