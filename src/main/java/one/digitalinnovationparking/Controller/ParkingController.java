package one.digitalinnovationparking.Controller;


import one.digitalinnovationparking.Controller.DTO.ParkingDTO;
import one.digitalinnovationparking.Controller.Mapper.ParkingMapper;
import one.digitalinnovationparking.model.Parking;
import one.digitalinnovationparking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;

    }

    @GetMapping
    public List<ParkingDTO> findAll(){

        List<Parking> parkingList = parkingService.findALL();
        List<ParkingDTO> result =  parkingMapper.toParkingDTOList(parkingList);
        return result;
     }
}
