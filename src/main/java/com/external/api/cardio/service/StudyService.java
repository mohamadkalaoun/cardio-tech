package com.external.api.cardio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.external.api.cardio.modals.Study;
import com.external.api.cardio.repository.StudyRepository;

@Transactional
@Service
public class StudyService {
	
	private static final Logger LOGGER = Logger.getLogger(StudyService.class.getName());
	private StudyRepository studyRepository;
	
	public StudyService(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}
	
	public Study findById(int id){
		return studyRepository.findById(id);
	}
	
	public List<Study> findAll() {
		return studyRepository.findAll();
	}
	
	public List<Study> findAll(String filterText) {
		if(filterText == null || filterText.isEmpty())
		{
			return studyRepository.findAll();
		}
		else
		{
			return studyRepository.search(filterText);
		}
	}
	
	public long count() {
		return studyRepository.count();
	}
	
	public void delete(Study study) {
		studyRepository.delete(study);
	}
	
	public void save(Study study) {
		if(study == null)
		{
			LOGGER.log(Level.SEVERE, "Study is null. Are you sure you have connected your form to the application?");
			return;
		}
		studyRepository.save(study);
	}
	
	public void updateStudy(int id, String serialNumber, String deviceName, LocalDateTime time , String duration) {
		Study p = studyRepository.findById(id);
		p.setDeviceSerialNumber(serialNumber);
		p.setDeviceName(deviceName);
		p.setStartTime(time);
		p.setDuration(duration);
		studyRepository.save(p);
	}
	
	@PostConstruct
	public void populateTestData() {
		if(studyRepository.count() == 0)
		{
			studyRepository.saveAll(Stream.of("1d;Gabrielle;sdyfg76e336eqtw1321;1;Ongoing;87,54,43,67,87,54,99,98,34,50,50,21,41,54,4,43,67,87,54,99,98,111,65,34,50,35,76,87,54,43,67,87,54,121,65,34,50,41,54,98,35,76,87,54,43,67,87,54,99,98,34,50,21,41,98,35,76,87,54,43,67;df", "27h;Brian;65ds4fsdf87wedfs;231;Ended;43,67,87,54,99,98,111,65,34,54,43,67,87,54,121,65,34,50,41,54,98,35,76,87,54,43,67,87,54,99,98,34,50,21,41,98,35,76,50,35,76,87,87,54,43,67,87,54,99,98,34,50,50,21,41,54,4;as", "12W;Eduardo;8we4fsaedfwad89f;12;Ongoing;43,67,87,54,99,98,34,50,21,41,98,35,76,50,35,76,87,87,54,43,67,87,54,87,54,43,67,87,54,99,98,34,50,50,21,41,54,4,43;kjl" , "14d;Emily;asd65fds4f5sdaf8;29;Ongoing;47,54,43,67,87,54,99,98,134,50,50,21,41,54,44,43,67,87,54,99,98,101,65,34,50,45,76,87,54,43,67,87,54,121,65,34,50,41,54,98,35,76,87,54,43,67,87,54,99,98,34,50,21,41,98,35,76,87,54,43,67;df", "3Y;Koen;sad4f86ds65f6sd;24;Ended;43,67,87,54,99,98,99,65,34,54,43,67,87,54,101,65,34,50,41,54,98,35,76,87,54,43,67,87,54,99,98,34,50,21,41,98,35,76,50,35,76,87,87,54,43,67,87,54,99,98,34,50,50,21,41,54,4;as", "24H;Yasir;5as4fdfd85s4f;27;Ended;43,67,87,54,99,98,34,50,21,41,98,35,76,50,35,76,87,87,54,43,67,87,54,87,54,43,67,87,54,99,98,34,50,50,21,41,54,4,43,99,98,134,50,50,21,41,54;kjl").map(name -> {
				        
				        String[] split = name.split(";");
				        Study study = new Study();
				        study.setDuration(split[0]);
				        study.setPatientName(split[1]);
				        study.setDeviceSerialNumber(split[2]);
				        study.setPatientId(split[3]);
				        study.setStudyStatus(split[4]);
				        study.setHeartBeatsValues(split[5]);
				        study.setStartTime(LocalDateTime.now());
				        return study;
			        }).collect(Collectors.toList()));
		}
	}
	
}