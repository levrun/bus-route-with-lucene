package com.goeuro.levrun.directconnectioncheck.service;

import com.goeuro.levrun.directconnectioncheck.model.DirectConnectionResult;
import com.goeuro.levrun.directconnectioncheck.service.lucene.LuceneInMemoryIndexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class RouteService {

    private final Logger logger = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private LuceneInMemoryIndexer indexer;

    public DirectConnectionResult checkDirectRoute(Integer departureId, Integer arrivalId) {

        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            boolean exist = indexer.search(" " + String.valueOf(departureId) + " ", " " + String.valueOf(arrivalId) + " ");
            stopWatch.stop();
            logger.info("Lucene search request processed in " + stopWatch.getTotalTimeMillis() + " ms");

            if (exist) {
                return new DirectConnectionResult(departureId, arrivalId, true);
            } else {
                return new DirectConnectionResult(departureId, arrivalId, false);
            }

        } catch (Exception e) {
            logger.error("Exception " + e.getMessage());
        }

        return null;

    }

}
