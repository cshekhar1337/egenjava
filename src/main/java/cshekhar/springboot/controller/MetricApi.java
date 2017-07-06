package cshekhar.springboot.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import cshekhar.springboot.DAO.MetricDao;
import cshekhar.springboot.model.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshekhar on 7/5/17.
 */

@RestController
@RequestMapping(value = "/metrics")
public class MetricApi {
    @Autowired
    MetricDao ml;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Metric> create(@RequestBody Metric metric) {

        if (metric == null)
            return new ResponseEntity<Metric>(metric, HttpStatus.BAD_REQUEST);
        if (metric.getTimeStamp() <= 0 || metric.getValue() <= 0)
            return new ResponseEntity<Metric>(metric, HttpStatus.BAD_REQUEST);

        ml.createMetric(metric);

        return new ResponseEntity<Metric>(metric, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/read")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<List<Metric>> read() {

        List<Metric> metricList = ml.read();

        if (metricList.size() == 0)
            return new ResponseEntity<List<Metric>>(metricList, HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Metric>>(metricList, HttpStatus.OK);
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @RequestMapping(value = "/readByTimeRange/{startTime}/{endTime}")
    public ResponseEntity<List<Metric>> readByTimeRange(@PathVariable Long startTime, @PathVariable Long endTime) {

        if (startTime == null || endTime == null)
            return new ResponseEntity<List<Metric>>(new ArrayList<Metric>(), HttpStatus.BAD_REQUEST);

        List<Metric> metricList = ml.readByRange(startTime, endTime);

        if (metricList.size() == 0)
            return new ResponseEntity<List<Metric>>(metricList, HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Metric>>(metricList, HttpStatus.OK);
    }






}