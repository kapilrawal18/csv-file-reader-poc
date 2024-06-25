package com.rsvpagentprocessing.rsvpagentprocessing.controller;

import com.rsvpagentprocessing.rsvpagentprocessing.service.RSVPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class RSVPController {
    @Autowired
    private RSVPService rsvpService;

    @GetMapping("/saveData")
    public Object saveData() throws IOException {
        return rsvpService.saveFileData();
    }

    @GetMapping("/get/actions")
    public Object getActions() {
        Object actions = rsvpService.getActions();
        return actions;
    }

    @GetMapping("/get/action/occurrences")
    public Object getActionOccurrences(@RequestParam String time, String date) {
        Object actionOccurrences = rsvpService.getActionOccurrences(time, date);
        return actionOccurrences;
    }

    @GetMapping("/get/all/logs")
    public Object getAllLogs(@RequestParam String time, String date) {
        Object allLogs = rsvpService.getAllLogs(time, date);
        return allLogs;
    }

    @GetMapping("/get/action/minMax")
    public Object getMinMaxActions(@RequestParam String time, String date) {
        Object minMaxActions = rsvpService.getMinMaxActions(time, date);
        return minMaxActions;
    }

    @GetMapping("/get/mail/data")
    public Object mailBoxData() {
       Object mailBoxData = rsvpService.getMailBoxData();
       return mailBoxData;
    }

    @GetMapping("/get/timer4/status")
    public Object getTimer4Status(@RequestParam String startTime, String endTime, String startDate, String endDate) {
        Object timer4Status = rsvpService.getTimer4Status(startTime, endTime, startDate, endDate);
        return timer4Status;
    }

    @GetMapping("/get/timer1/status")
    public Object getTimer1Status(@RequestParam String startTime, String endTime, String startDate, String endDate) {
        Object timer1Status = rsvpService.getTimer1Status(startTime, endTime, startDate, endDate);
        return timer1Status;
    }

}
