package com.gatorRider.GatorRider.Service;

import com.gatorRider.GatorRider.Model.Driver;
import com.gatorRider.GatorRider.Model.Ride;
import com.gatorRider.GatorRider.Model.RidePassenger;
import com.gatorRider.GatorRider.Model.RideRequest;
import com.gatorRider.GatorRider.Repository.DriverRepository;
import com.gatorRider.GatorRider.Repository.RidePassengerRepository;
import com.gatorRider.GatorRider.Repository.RideRepository;
import com.gatorRider.GatorRider.data.SMSMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RideService implements org.hibernate.service.Service {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RidePassengerRepository ridePassengerRepository;
    public List<RidePassenger> getAllPassRide() {
        return ridePassengerRepository.findAll();
    }
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }
    public String createRide(RideRequest rideRequest) throws Exception {
        rideRequest.validation();
        Ride ride = new Ride();
        ride.setId(UUID.randomUUID().toString());
        ride.setDateTime(rideRequest.getDateTime());
        ride.setLocation(rideRequest.getLocation());
        ride.setModelName(rideRequest.getModelName());
        ride.setModelYear(rideRequest.getModelYear());
        ride.setNumSeatAvailable(rideRequest.getNumSeatAvailable());
        ride.setRideIntro(rideRequest.getRideIntro());
        ride.setDriver(driverRepository.getOne(rideRequest.getDriverId()));
        ride.setIsOutBound(rideRequest.getIsOutBound());

        notificationService.sendSMSToOne(driverRepository.getOne(rideRequest.getDriverId()),
                SMSMessage.RIDE_CREATED_TO_DRIVER +
                        this.getMessageRideInfo(ride));
        return rideRepository.save(ride).getId();
    }

    public String getMessageRideInfo(Ride ride) {
        String direction;
        if (ride.getIsOutBound() == true) {
            direction = "Gainesville -> " + ride.getLocation();
        }
        else {
            direction = ride.getLocation() + " -> Gainesville";
        }
        String dateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(ride.getDateTime());

        return direction + "\n" +
                dateTime + "\n" +
                ride.getModelYear() + " " +
                ride.getModelName() + "\n" +
                "# seats: " + ride.getNumSeatAvailable();
    }

    public List<Ride> getMyRide(String driverId){
        return rideRepository.findByDriverId(driverId);
    }
    public String updateRide(RideRequest rideRequest) throws Exception{
        rideRequest.validation();
        Ride ride = rideRepository.getOne(rideRequest.getRideId());
        ride.setDateTime(rideRequest.getDateTime());
        ride.setLocation(rideRequest.getLocation());
        ride.setModelName(rideRequest.getModelName());
        ride.setModelYear(rideRequest.getModelYear());
        ride.setNumSeatAvailable(rideRequest.getNumSeatAvailable());
        ride.setRideIntro(rideRequest.getRideIntro());
        ride.setIsOutBound(rideRequest.getIsOutBound());

        List<Driver> ridePassengers = this.getPassOfRide(rideRequest.getRideId());

        ridePassengers.forEach(passenger -> {
            notificationService.sendSMSToOne(passenger, SMSMessage.RIDE_INFO_CHANGE_TO_PASSENGER);
        });

        notificationService.sendSMSToOne(driverRepository.getOne(rideRequest.getDriverId()),
                SMSMessage.RIDE_INFO_CHANGE_TO_DRIVER);
        return rideRepository.save(ride).getId();
    }
    public void deleteRide(String rideId){
        Driver driver = rideRepository.getOne(rideId).getDriver();
        notificationService.sendSMSToOne(driver, SMSMessage.RIDE_REMOVE_TO_DRIVER
                + this.getMessageRideInfo(rideRepository.getOne(rideId)));
        rideRepository.deleteById(rideId);

    }

    @Scheduled(cron="0 0 0 ? * *")
    public void autoDelete(){
        List<Ride> allRide = rideRepository.findAll();
        Date date = new Date();
        for(Ride i: allRide){
            if(date.after(i.getDateTime())){
                rideRepository.delete(i);
            }
        }
    }

    @Scheduled(cron="0 0 0 ? * *")
    public void autoRemind(){
        Date date = new Date();

        List<Ride> allRide = rideRepository.findAll();

        for(Ride ride: allRide){
            if(date.equals(ride.getDateTime())){
                notificationService.sendSMSToOne(ride.getDriver(), SMSMessage.REMIND_1DAY_LEFT
                + this.getMessageRideInfo(ride));
                List<RidePassenger> ridePassengers = ridePassengerRepository.findByRideId(ride.getId());
                for (RidePassenger passenger: ridePassengers) {
                    notificationService.sendSMSToOne(ride.getDriver(),
                            SMSMessage.REMIND_1DAY_LEFT
                            + this.getMessageRideInfo(ride)
                            + "\nDriver: " + ride.getDriver().getFullName()
                    );
                }
            }
        }
    }

    public String addPassenger (RidePassenger ridePassenger) throws Exception{
            List<RidePassenger> list = ridePassengerRepository.findByRideId(ridePassenger.getRideId());
            for(RidePassenger i: list){
                if(i.getPassengerId().equals(ridePassenger.getPassengerId())){
                    throw new Exception("Duplicate Reservation");
                }
            }
            Ride tempRide = rideRepository.getOne(ridePassenger.getRideId());
            if(tempRide.getNumSeatAvailable()>0) {
                ridePassenger.setId(UUID.randomUUID().toString());
                ridePassengerRepository.save(ridePassenger).getRideId();
                tempRide.setNumSeatAvailable(tempRide.getNumSeatAvailable() - 1);
                //notify passenger
                notificationService.sendSMSToOne(driverRepository.getOne(ridePassenger.getPassengerId()),
                        SMSMessage.NOTIFY_PASSENGER_WHEN_RIDE_IS_RESERVED
                                + this.getMessageRideInfo(tempRide)
                                + "\nDriver: " + tempRide.getDriver().getFullName()
                );
                //notify driver
                notificationService.sendSMSToOne(tempRide.getDriver(),
                        driverRepository.getOne(ridePassenger.getPassengerId()).getFullName()
                                + SMSMessage.NOTIFY_DRIVER_WHEN_RIDE_IS_RESERVED
                                + this.getMessageRideInfo(tempRide)
                );
                return rideRepository.save(tempRide).getId();
            }
            throw new Exception("No more available seat");
    }

    public List<Ride> getMyRideAsPass(String driverId){

        List<RidePassenger> ridePassengers = ridePassengerRepository.findByPassengerId(driverId);
        List<String> rideIdList = new ArrayList<>();
        for(RidePassenger i: ridePassengers){
            rideIdList.add(i.getRideId());
        }
        return rideRepository.findAllById(rideIdList);
    }

    public List<Driver> getPassOfRide(String rideId){
        List<RidePassenger> ridePassengers = ridePassengerRepository.findByRideId(rideId);
        List<String> driverIdList = new ArrayList<>();
        for(RidePassenger i: ridePassengers){
            driverIdList.add(i.getPassengerId());
        }
        return driverRepository.findAllById(driverIdList);

    }
    public void deleteReservation(RidePassenger ridePassenger){
        List<RidePassenger> list = ridePassengerRepository.findByRideId(ridePassenger.getRideId());

        //notify driver
        notificationService.sendSMSToOne(rideRepository.getOne(ridePassenger.getRideId()).getDriver(),
                driverRepository.getOne(ridePassenger.getPassengerId()).getFullName()
                        + SMSMessage.NOTIFY_DRIVER_WHEN_RIDERESERVE_IS_REMOVED
                        + this.getMessageRideInfo(rideRepository.getOne(ridePassenger.getRideId()))
        );

        for(RidePassenger i: list){
            //notify passenger
            notificationService.sendSMSToOne(driverRepository.getOne(ridePassenger.getPassengerId()),
                    SMSMessage.NOTIFY_PASSENGER_WHEN_RIDERESERVE_IS_REMOVED
            );
            ridePassengerRepository.deleteById(i.getId());
        }
    }
}
