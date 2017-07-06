package cshekhar.springboot.controller;

import cshekhar.springboot.DAO.AlertDao;
import cshekhar.springboot.model.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshekhar on 7/5/17.
 */

@RestController
@RequestMapping(value = "/alerts")
public class AlertApi {
    @Autowired
    AlertDao al;
    @RequestMapping(value = "/read")
    public ResponseEntity<List<Alert>> read() {

        List<Alert> alertList = al.read();
        System.out.println(alertList.get(0));


        if (alertList.size() == 0)
            return new ResponseEntity<List<Alert>>(alertList, HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Alert>>(alertList, HttpStatus.OK);
    }

    @RequestMapping(value = "/readByTimeRange/{startTime}/{endTime}")
    public ResponseEntity<List<Alert>> readByTimeRange(@PathVariable Long startTime, @PathVariable Long endTime) {

        if (startTime == null || endTime == null)
            return new ResponseEntity<List<Alert>>(new ArrayList<Alert>(), HttpStatus.BAD_REQUEST);

        List<Alert> alertList = al.readByRange(startTime, endTime);

        if (alertList.size() == 0)
            return new ResponseEntity<List<Alert>>(alertList, HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Alert>>(alertList, HttpStatus.OK);
    }
}
