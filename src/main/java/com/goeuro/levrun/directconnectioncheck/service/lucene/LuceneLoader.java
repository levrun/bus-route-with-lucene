package com.goeuro.levrun.directconnectioncheck.service.lucene;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LuceneLoader implements ApplicationRunner {

    @Autowired
    private LuceneInMemoryIndexer indexer;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String[] args = applicationArguments.getSourceArgs();
        String dataFilePath;
        if(args == null || args.length == 0) {
            dataFilePath = "example.txt";
        } else {
            dataFilePath = applicationArguments.getSourceArgs()[0];
        }

        indexer.create(dataFilePath);
    }
}
