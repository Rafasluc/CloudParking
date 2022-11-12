package one.digitalinnovationparking.service;

import one.digitalinnovationparking.model.Parking;
import org.springframework.stereotype.Service;
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

}
