package com.hq.cloudeventhelper;

import com.hq.cloudeventhelper.common.CloudEventContentCategory;
import com.hq.cloudeventhelper.common.CloudEventDataContentType;
import com.hq.cloudeventhelper.common.CloudEventSpecVersion;
import com.hq.cloudeventhelper.core.CloudEvent;
import com.hq.cloudeventhelper.core.EnterpriseEvent;
import com.hq.cloudeventhelper.events.client.pointdesuivi.PointDeSuiviEventType;
import com.hq.cloudeventhelper.model.PointDeSuivi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootApplication
public class CloudeventhelperApplication implements CommandLineRunner {

	ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(CloudeventhelperApplication.class, args);
	}

	@Override
	public void run(String... args) {

		// Création d'un nouveau point de suivi
		PointDeSuivi pointDeSuivi = PointDeSuivi.builder()
				.id("123")
				.userId("USER1")
				.latitude("48.8588443")
				.longitude("2.2943506")
				.build();

		// Création de l'événement CloudEvents
		CloudEvent<PointDeSuivi> cloudEvent = CloudEvent.<PointDeSuivi>builder()
				.specversion(CloudEventSpecVersion.V1)
				.type(PointDeSuiviEventType.NOUVEAU_POINT_DE_SUIVI)
				.source("CL")
				.subject(pointDeSuivi.getId())
				.datacontenttype(CloudEventDataContentType.APPLICATION_JSON)
				.data(pointDeSuivi)
				.build();

		// Création de l'événement d'entreprise
		EnterpriseEvent<PointDeSuivi> enterpriseEvent = EnterpriseEvent.<PointDeSuivi>builder()
				.applicationId("APP1")
				.userId("USER1")
				.sessionId("SESSION1")
				.requestId("REQUEST1")
				.contentCategory(CloudEventContentCategory.USER_DATA)
				.cloudEvent(cloudEvent)
				.build();

		// Convertir l'événement d'entreprise en JSON
		try {
			String enterpriseEventJson = objectMapper.writeValueAsString(enterpriseEvent);
			log.info("Enterprise event JSON: {}", enterpriseEventJson);
		} catch (Exception e) {
			log.error("Error converting enterprise event to JSON", e);
		}
	}
}
