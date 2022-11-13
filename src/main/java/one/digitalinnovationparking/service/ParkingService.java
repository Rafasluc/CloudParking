package one.digitalinnovationparking.service;

import one.digitalinnovationparking.Repository.ParkingRepository;
import one.digitalinnovationparking.exception.ParkingNotFoundException;
import one.digitalinnovationparking.model.Parking;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public List<Parking> findALL(){
        return parkingRepository.findAll();
    }

    private static String getUUIO() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
     }

    public Parking create(Parking parkingCreate) {
        String uuio = getUUIO();
        parkingCreate.setId(uuio);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }


    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    public Parking update(String id, Parking parkingCreate){
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        parkingRepository.save(parking);
        return parking;
    }




   public Parking checkOut(String id) {
           Parking parking = findById(id);
           parking.setExitDate(LocalDateTime.now());
           parking.setBill(ParkingCheckOut.getBill(parking));
           return parking;
    }
}
