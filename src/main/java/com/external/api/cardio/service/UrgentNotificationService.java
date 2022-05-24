package com.external.api.cardio.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.external.api.cardio.modals.Study;
import com.external.api.cardio.modals.UrgentNotification;
import com.external.api.cardio.repository.UrgentNotificationRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Transactional
@Service
public class UrgentNotificationService {
	
	private static final Logger LOGGER = Logger.getLogger(UrgentNotification.class.getName());
	private UrgentNotificationRepository urgentNotificationRepository;
	
	public UrgentNotificationService(UrgentNotificationRepository urgentNotificationRepository) {
		this.urgentNotificationRepository = urgentNotificationRepository;
	}
	
	public List<UrgentNotification> findAll() {
		return urgentNotificationRepository.findAll();
	}
	
	public List<UrgentNotification> findAll(String filterText) {
		if(filterText == null || filterText.isEmpty())
		{
			return urgentNotificationRepository.findAll();
		}
		else
		{
			return urgentNotificationRepository.search(filterText);
		}
	}
	
	public long count() {
		return urgentNotificationRepository.count();
	}
	
	public void delete(UrgentNotification device) {
		urgentNotificationRepository.delete(device);
	}
	
	public void save(UrgentNotification device) {
		if(device == null)
		{
			LOGGER.log(Level.SEVERE, "UrgentNotification is null. Are you sure you have connected your form to the application?");
			return;
		}
		urgentNotificationRepository.save(device);
	}
	
	public void invokeNotification(InputStream is , Study study) {
		try
		{
			Reader inputStreamReader = new InputStreamReader(is);
			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
			CSVReader reader = new CSVReaderBuilder(inputStreamReader).withCSVParser(parser).build();
			List<String[]> entries;
			entries = reader.readAll();
			String[] stringValues = entries.get(0);
			int[] values = Arrays.stream(stringValues).mapToInt(Integer::parseInt).toArray();
			for (int i : values)
			{
				if(i < 50 || i > 100)
				{
					UrgentNotification urg = new UrgentNotification();
					String msg = "Abnormal Heart beat sent : "+i;
					urg.setMsg(msg);
					urg.setTime(LocalDateTime.now());
					urg.setPatientName(study.getPatientName());
					urg.setSerialNumber(study.getDeviceSerialNumber());
					urgentNotificationRepository.save(urg);
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void populateTestData() {
		if(urgentNotificationRepository.count() == 0)
		{
			urgentNotificationRepository.saveAll(Stream.of("Gabrielle, sdyfg76e336eqtw1321, Very bad situation", "Rayan, 5as4d6f46ds, Heart beats dfropped suddenly",
			        "Yurem ,d5sa4f6d5sa4f, Very urgent!", "Kelly, asdf47sdf8asdfsdf5, Hurry UPPPP").map(name -> {
				        
				        String[] split = name.split(",");
				        UrgentNotification device = new UrgentNotification();
				        device.setSerialNumber(split[1]);
				        device.setPatientName(split[0]);
				        device.setMsg(split[2]);
				        return device;
			        }).collect(Collectors.toList()));
		}
	}
	
}