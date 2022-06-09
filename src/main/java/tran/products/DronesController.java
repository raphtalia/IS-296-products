package tran.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DronesController {
    private static Map<Integer, Drones> droneRepo = new HashMap<>();

    static {
        Drones droneA = new Drones();
        droneA.setId(1);
        droneA.setName("Field A Drone");
        droneA.setAvailable(false);
        droneA.setService("Water the crops");
        droneRepo.put(droneA.getId(), droneA);

        Drones droneB = new Drones();
        droneB.setId(2);
        droneB.setName("Field B Drone");
        droneB.setAvailable(true);
        droneB.setService("Take picture of corn crops");
        droneRepo.put(droneB.getId(), droneB);
    }

    // GET endpoint (R for read in CRUD)
    @RequestMapping(value = "/drones", produces = "application/json")
    public ResponseEntity<Object> getDrones() {
        return new ResponseEntity<>(droneRepo.values(), HttpStatus.OK);
    }

    // DELETE endpoint (D for delete in CRUD)
    @RequestMapping(value = "/drones{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDrone(@PathVariable("id") int id) {
        droneRepo.remove(id);
        return new ResponseEntity<>("Drone is deleted and will no longer be available", HttpStatus.OK);
    }

    // POST endpoint (C for create in CRUD)
    @RequestMapping(value = "/drones", method = RequestMethod.POST)
    public ResponseEntity<Object> addDrone(@RequestBody Drones drone) {
        droneRepo.put(drone.getId(), drone);
        return new ResponseEntity<>("Drone has been added to the list of available drones!", HttpStatus.OK);
    }

    // PUT endpoint (U for update in CRUD)
    @RequestMapping(value = "/drones{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> changeDrone(@PathVariable("id") int id, @RequestBody Drones drone) {
        droneRepo.remove(id);
        drone.setId(id);
        droneRepo.put(id, drone);
        return new ResponseEntity<>("Properties of Drone have been changed!", HttpStatus.OK);
    }
}
