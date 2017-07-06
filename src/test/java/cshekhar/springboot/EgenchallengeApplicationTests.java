package cshekhar.springboot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cshekhar.springboot.DAO.AlertDao;
import cshekhar.springboot.DAO.MetricDao;
import cshekhar.springboot.controller.MetricApi;
import cshekhar.springboot.model.Alert;
import cshekhar.springboot.model.Metric;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EgenchallengeApplicationTests {

	@Autowired
	private MetricApi metricService;

	@Autowired
	private AlertDao alertdao;

	@Autowired
	private MetricDao metricdao;



	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}


	@Test
	public void check_alert_Overweight() {

		long timeStamp = 89985697;
		Metric m = new Metric();
		m.setTimeStamp(timeStamp);
		m.setValue(934);
		Gson objGson = new Gson();
		String req = objGson.toJson(m);
        try {
            this.mockMvc.perform(post("/metrics/create")
                    .contentType(MediaType.APPLICATION_JSON).content(req))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<Alert> alertList = alertdao.read();
		for (Alert alert : alertList) {
			if (alert.getTimeStamp() == timeStamp && alert.getType().equals("OverWeight")) {
				assert (true);
				return;
			}
		}

		assert (false);
	}

	@Test
	public void check_alert_Underweight() {

		long timeStamp = 1363325438;
        Metric m = new Metric();
        m.setTimeStamp(timeStamp);
        m.setValue(13);
        Gson objGson = new Gson();
        String req = objGson.toJson(m);
        try {
            this.mockMvc.perform(post("/metrics/create")
                    .contentType(MediaType.APPLICATION_JSON).content(req))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Alert> alertList = alertdao.read();
		for (Alert alert : alertList) {
			if (alert.getTimeStamp() == timeStamp && alert.getType().equals("UnderWeight")) {
				assert (true);
				return;
			}
		}

		assert (false);
	}





	@Test
	public void contextLoads() {
	}


}
