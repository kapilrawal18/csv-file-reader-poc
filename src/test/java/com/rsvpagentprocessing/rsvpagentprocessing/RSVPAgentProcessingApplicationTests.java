package com.rsvpagentprocessing.rsvpagentprocessing;

import com.rsvpagentprocessing.rsvpagentprocessing.model.RSVP;
import com.rsvpagentprocessing.rsvpagentprocessing.service.RSVPService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest
class RSVPAgentProcessingApplicationTests {

	@MockBean
	private RSVPService rsvpService;

	@Test
	void contextLoads() throws IOException {
		when(rsvpService.saveFileData()).thenReturn("Data uploaded successfully.");
		Assertions.assertEquals("Data uploaded successfully." , rsvpService.saveFileData());
	}

	@Test
	void getDistinctAction() {
		List<String> mockList = new ArrayList<>();
		mockList.add("locate_configFile");
		when(rsvpService.getActions()).thenReturn(mockList);
		Assertions.assertEquals(mockList , rsvpService.getActions());
	}

	@Test
	void getActionOccurrences() {
		String time = "08:51:01";
		String date = "03/22/2024";
		Map<String , Integer> mapData = new HashMap<>();
		mapData.put("locate_configFile",1);

		when(rsvpService.getActionOccurrences(time, date)).thenReturn(mapData);
		Assertions.assertEquals(mapData, rsvpService.getActionOccurrences(time, date));
	}

	@Test
	void getAllLogs() {
		String time = "08:51:01";
		String date = "03/22/2024";

		RSVP rsvp = RSVP.builder().date(date).time(time).level("INFO").action("main").message("*************** RSVP Agent started ***************").build();
		when(rsvpService.getAllLogs(time,date)).thenReturn(rsvp);
		Assertions.assertEquals(rsvp, rsvpService.getAllLogs(time,date));
	}

	@Test
	void getMinMaxActions() {
		String time = "08:51:01";
		String date = "03/22/2024";

		String minMaxActions = "Top Action : " + "main" + "\r\n" + "Flop Actions : " + "locate_configFile";
		when(rsvpService.getMinMaxActions(time, date)).thenReturn(minMaxActions);
		Assertions.assertEquals(minMaxActions, rsvpService.getMinMaxActions(time, date));
	}

	@Test
	void getMailBoxData() {
		String mailBoxCount = "Message count for rsvp : " + "15" + "\r\n" + "Message count for rsvp-udp : " + "7";
		when(rsvpService.getMailBoxData()).thenReturn(mailBoxCount);
		Assertions.assertEquals(mailBoxCount, rsvpService.getMailBoxData());
	}

	@Test
	void getTimer4Status() {
		String startTime = "08:52:52";
		String endTime = "08:53:22";
		String startDate = "03/22/2024";
		String endDate = "03/22/2024";

		String timer4Status = "Timer 4 stop count : " + "5";
		when(rsvpService.getTimer4Status(startTime,endTime,startDate,endDate)).thenReturn(timer4Status);
		Assertions.assertEquals(timer4Status, rsvpService.getTimer4Status(startTime,endTime,startDate,endDate));
	}

	@Test
	void getTimer1Status() {
		String startTime = "08:51:01";
		String endTime = "08:53:52";
		String startDate = "03/22/2024";
		String endDate = "03/22/2024";

		String timer1Status = "Timer 1 stop count : " + "7";
		when(rsvpService.getTimer1Status(startTime,endTime,startDate,endDate)).thenReturn(timer1Status);
		Assertions.assertEquals(timer1Status, rsvpService.getTimer1Status(startTime,endTime,startDate,endDate));
	}
}
