package com.external.api.cardio.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.external.api.cardio.modals.Device;
import com.external.api.cardio.repository.DeviceRepository;

@Transactional
@Service
public class DeviceService {

	private static final Logger LOGGER = Logger.getLogger(DeviceService.class.getName());
	private DeviceRepository deviceRepository;

	public DeviceService(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}

	public List<Device> findAll() {
		return deviceRepository.findAll();
	}

	public List<Device> findAll(String filterText) {
		if (filterText == null || filterText.isEmpty()) {
			return deviceRepository.findAll();
		} else {
			return deviceRepository.search(filterText);
		}
	}

	public List<Device> findUnusedDevices(){
		List<Device> unusedDevices = new ArrayList<>();
		List<Device> allDevices = findAll();
		for (Device device : allDevices)
		{
			if (device.getAssociatedPatientId() == null || device.getAssociatedPatientId().isEmpty()) 
				unusedDevices.add(device);
		}
		return unusedDevices;
	}
	
	public long count() {
		return deviceRepository.count();
	}

	public void delete(Device device) {
		deviceRepository.delete(device);
	}

	public void save(Device device) {
		if (device == null) {
			LOGGER.log(Level.SEVERE, "Device is null. Are you sure you have connected your form to the application?");
			return;
		}
		deviceRepository.save(device);
	}

	public void updateDevice(long id, String serialNumber, String deviceName, String associatedPatientId,
			LocalDateTime time) {
		Device p = deviceRepository.findById(id);
		p.setSerialNumber(serialNumber);
		p.setDeviceName(deviceName);
		p.setAssociatedPatientId(associatedPatientId);
		p.setTime(time);
		deviceRepository.save(p);
	}

	@PostConstruct
	public void populateTestData() {
		if (deviceRepository.count() == 0) {
			deviceRepository.saveAll(Stream.of("Gabrielle ,sdyfg76e336eqtw1321, 1", "Brian, 65ds4fsdf87wedfs,, 1", "Eduardo, 8we4fsaedfwad89f, 12", "Koen ,fsad7f4ds4af8ds, 9",
					"Alejandro ,5as4fdfd85s4f, ,314", "Angel ,dsfa8f4d98sf4das,,gh ", "Yasir, asd65fds4f5sdaf8 ,2", "Hadi ,5sadf6a4sdfsadf, 3", "Emily, 85487a4sfd8694dsf ,,g ",
					"Colette, sad4f86ds65f6sd ,,s", "Rayan ,5as4d6f46ds, 43", "Yurem ,d5sa4f6d5sa4f ,3", "Kelly ,asdf47sdf8asdfsdf5,,ddf ").map(name -> {

						String[] split = name.split(",");
						Device device = new Device();
						device.setDeviceName(split[0]);
						device.setSerialNumber(split[1]);
						device.setAssociatedPatientId(split[2]);
						device.setTime(LocalDateTime.now());
						return device;
					}).collect(Collectors.toList()));
		}
	}

}