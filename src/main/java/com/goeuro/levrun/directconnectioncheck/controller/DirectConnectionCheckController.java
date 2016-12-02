package com.goeuro.levrun.directconnectioncheck.controller;

import com.goeuro.levrun.directconnectioncheck.model.DirectConnectionResult;
import com.goeuro.levrun.directconnectioncheck.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Api(value="A micro service which is able to answer whether there\n" +
        "is a bus route providing a direct connection between two given stations")
@RestController
@EnableSwagger2
public class DirectConnectionCheckController {

    private final Logger logger = LoggerFactory.getLogger(DirectConnectionCheckController.class);

    @Autowired
    private RouteService service;

    @ApiOperation(notes="Check if there is direct connection between two given stations", value = "/api/direct", code = 200)
    @RequestMapping(value = "/api/direct", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<DirectConnectionResult> checkDirectRoute(@RequestParam("dep_sid") int departureId,
                                                                   @RequestParam("arr_sid") int arrivalId) {

        DirectConnectionResult result = service.checkDirectRoute(departureId, arrivalId);
        if(result == null) {
            logger.info("Application is not yet initialised properly, please wait");
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
