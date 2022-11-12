package one.digitalinnovationparking.service;

import one.digitalinnovationparking.model.Parking;
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
        return parkingMap.get(id);
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUIO();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }
}
