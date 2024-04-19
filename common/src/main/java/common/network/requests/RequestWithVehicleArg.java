package common.network.requests;

import common.models.Vehicle;

public class RequestWithVehicleArg extends Request{
    private Vehicle vehicleArg;
    public RequestWithVehicleArg(String name, Vehicle o){
        super(name);
        vehicleArg = o;
    }

    public Vehicle getVehicleArg() {
        return vehicleArg;
    }
}
