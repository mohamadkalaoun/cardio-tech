package com.external.api.cardio.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.external.api.cardio.modals.Study;
import com.external.api.cardio.service.StudyService;
import com.external.api.cardio.service.UrgentNotificationService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudyController {
	
	StudyService studyService;
	UrgentNotificationService urgentNotificationService;
	
	public StudyController(StudyService studyService, UrgentNotificationService urgentNotificationService) {
		this.studyService = studyService;
		this.urgentNotificationService = urgentNotificationService;
	}
	
	@GetMapping("/study/{id}")
	public ResponseEntity<Study> getStudyById(@PathVariable("id") int id) {
		Study studyData = studyService.findById(id);
		if(studyData.getId() != null)
		{
			return new ResponseEntity<>(studyData, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/study/{id}")
	public ResponseEntity<Study> updateStudy(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
		Study _study = studyService.findById(id);
		if(!file.isEmpty())
		{
			try
			{
				InputStream inputStream = file.getInputStream();
				InputStream inputStream4Noti = file.getInputStream();
				Path tempFile = Files.createTempDirectory("").resolve(UUID.randomUUID().toString() + ".tmp");
				Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
				String textResult = new String(Files.readAllBytes(tempFile));
				_study.setHeartBeatsValues(textResult);
				studyService.save(_study);
				urgentNotificationService.invokeNotification(inputStream4Noti, _study);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		//			_study.setTitle(study.getTitle());
		//			_study.setPublished(study.isPublished());
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return null;
	}
}