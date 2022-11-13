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
        var id1 = getUUIO();
        Parking parking= new Parking(id, "DMS-1111", "SP", "CELTA", "Azul");
        Parking parking1= new Parking(id1, "KKK-1234", "PE", "BMW M3", "VERMELHO");

        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);
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
}
