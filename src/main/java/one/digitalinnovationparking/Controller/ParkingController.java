package one.digitalinnovationparking.Controller;


import one.digitalinnovationparking.model.Parking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @GetMapping
    public List<Parking> findAll(){

        var parking = new Parking();
        parking.setColor("PRETO");
        parking.setLicense("mss-1111");
        parking.setModel("VW Gol");
        parking.setState("PE");

        return Arrays.asList(parking, parking);
    }
}
