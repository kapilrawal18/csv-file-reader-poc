package com.rsvpagentprocessing.rsvpagentprocessing.service;

import com.rsvpagentprocessing.rsvpagentprocessing.model.RSVP;
import com.rsvpagentprocessing.rsvpagentprocessing.repository.RSVPRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RSVPService {
    @Autowired
    private RSVPRepository rsvpRepository;

    public Object saveFileData() throws IOException {
        ClassPathResource resource = new ClassPathResource("rsvp_agent_processing.log");
        InputStream inputStream = resource.getInputStream();

        String filePath = "C:\\Users\\Kapil.R\\Desktop\\rsvp-agent-processing\\src\\main\\resources\\rsvp_agent_processing.log";
        LineIterator textData = FileUtils.lineIterator(new File(filePath), "UTF-8");
        List<RSVP> rsvpList = new ArrayList<>();

        for (LineIterator it = textData; it.hasNext(); ) {
            String newLine = it.next();
            String[] splitedLine = newLine.split(":\\.")[0].split(" ");

            String date = splitedLine[0] + "/" + Year.now();
            String time = splitedLine[1];
            String level = splitedLine[2];
            String action = newLine.split(":\\.")[1].split(":")[0].replace(".", "");
            List<String> msgs = Arrays.asList(newLine.split(":\\.")[1].split(":"));
            String msg = msgs.subList(1, msgs.size()).stream().reduce("", (m1, m2) -> m1 + m2).trim();

            rsvpList.add(RSVP.builder()
                    .date(date).time(time).level(level).action(action).message(msg).build());

        }
        System.out.println(rsvpList);
        rsvpRepository.saveAll(rsvpList);
        inputStream.close();
        return "Data uploaded successfully.";
    }

    public Object getActions() {
        List<RSVP> data = rsvpRepository.findAll();
        List<String> distinctActions = data
                .stream()
                .map(RSVP::getAction)
                .distinct()
                .collect(Collectors.toList());
        return distinctActions;
    }

    public Object getActionOccurrences(String time, String date) {
        List<RSVP> data = rsvpRepository.findAll();
        List<RSVP> filteredData = getFilerDataByDateTime(time, date, data);
        if(!filteredData.isEmpty()){
            Map<String, Long> actionOccurrences = filteredData
                    .stream()
                    .collect(Collectors.groupingBy(RSVP::getAction, Collectors.counting()));
            return actionOccurrences;
        } else {
            throw new RuntimeException();
        }
    }

    public Object getAllLogs(String time, String date) {
        List<RSVP> data = rsvpRepository.findAll();
        List<RSVP> filteredData = getFilerDataByDateTime(time, date, data);

        if(!filteredData.isEmpty()){
            return filteredData;
        } else {
            throw new RuntimeException();
        }
    }

    public Object getMinMaxActions(String time, String date) {
        List<RSVP> data = rsvpRepository.findAll();
        List<RSVP> filteredData = getFilerDataByDateTime(time, date, data);

        String minActions = filteredData
                .stream()
                .map(RSVP::getAction)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .min(Comparator.comparing(Map.Entry::getValue))
                .get().getKey();

//        List<String> minActions = data
//                .stream()
//                .map(RSVP::getAction)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(x -> x.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());

        String maxAction = filteredData
                .stream()
                .map(RSVP::getAction)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get().getKey();
        String minMaxActions = "Top Action : " + maxAction + "\r\n" + "Flop Actions : " + minActions;

        return minMaxActions;
    }

    public Object getMailBoxData() {
        List<RSVP> data = rsvpRepository.findAll();
        Long rsvpUdpCount = data
                .stream()
                .map(RSVP::getMessage)
                .filter(x -> x.contains("mailbox allocated for rsvp-udp"))
                .count();
        Long rsvpCount = data
                .stream()
                .map(RSVP::getMessage)
                .filter(x -> x.contains("mailbox allocated for rsvp"))
                .count();

        String mailBoxCount = "Message count for rsvp : " + rsvpCount + "\r\n" + "Message count for rsvp-udp : " + rsvpUdpCount;
        return mailBoxCount;
    }

    public Object getTimer4Status(String startTime, String endTime, String startDate, String endDate) {
        List<RSVP> data = rsvpRepository.findAll();
        List<RSVP> filteredData = getFilerDataByStartEndDateTime (startTime, endTime, startDate, endDate, data);


        Long stopT4 = filteredData
                .stream()
                .map(RSVP::getMessage)
                .filter(x -> x.contains("Stop T4"))
                .count();
        String timer4Status = "Timer 4 stop count : " + stopT4;
        return timer4Status;
    }

    public Object getTimer1Status(String startTime, String endTime, String startDate, String endDate) {
        List<RSVP> data = rsvpRepository.findAll();
        List<RSVP> filteredData = getFilerDataByStartEndDateTime(startTime, endTime, startDate, endDate, data);

        Long startT1 = filteredData
                .stream()
                .map(RSVP::getMessage)
                .filter(x -> x.contains("started T1"))
                .count();
        String timer1Status = "Timer 1 start count : " + startT1;
        return timer1Status;
    }

    private static List<RSVP> getFilerDataByStartEndDateTime(String startTime, String endTime, String startDate, String endDate, List<RSVP> data) {
        List<RSVP> filteredData = new ArrayList<>();
        for(int i = 0; i< data.size(); i++){
            if(
                    data.get(i).getTime().equals(startTime) ||
                    data.get(i).getTime().equals(endTime) ||
                    data.get(i).getDate().equals(startDate) ||
                    data.get(i).getDate().equals(endDate)){
                filteredData.add(new RSVP(
                        data.get(i).getId(),
                        data.get(i).getDate(),
                        data.get(i).getTime(),
                        data.get(i).getLevel(),
                        data.get(i).getAction(),
                        data.get(i).getMessage()
                ));
            }
        }
        return filteredData;
    }

    private static List<RSVP> getFilerDataByDateTime(String time, String date, List<RSVP> data) {
        List<RSVP> filteredData = new ArrayList<>();
        for(int i = 0; i< data.size(); i++){
            if(
                    data.get(i).getTime().equals(time) &&
                    data.get(i).getDate().equals(date)){
                filteredData.add(new RSVP(
                        data.get(i).getId(),
                        data.get(i).getDate(),
                        data.get(i).getTime(),
                        data.get(i).getLevel(),
                        data.get(i).getAction(),
                        data.get(i).getMessage()
                ));
            }
        }
        return filteredData;
    }
}
