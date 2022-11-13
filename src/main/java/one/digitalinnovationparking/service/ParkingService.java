package one.digitalinnovationparking.service;

import one.digitalinnovationparking.exception.ParkingNotFoundException;
import one.digitalinnovationparking.model.Parking;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    static {
        var id = getUUIO();
        Parking parking= new Parking(id, "DMS-1111", "SP", "CELTA", "Azul");


        parkingMap.put(id, parking);

    }

    public List<Parking> findALL(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUIO() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuio = getUUIO();
        parkingCreate.setId(uuio);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuio, parkingCreate);
        return parkingCreate;
    }


    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate){
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        return parking;
    }




   public Parking checkOut(String id) {
           Parking parking = findById(id);
           parking.setExitDate(LocalDateTime.now());
           parking.setBill(ParkingCheckOut.getBill(parking));
           return parking;
    }
}
