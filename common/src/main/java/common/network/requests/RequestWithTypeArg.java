package common.network.requests;

import common.models.VehicleType;

public class RequestWithTypeArg extends Request{
    VehicleType typeArg;
    public RequestWithTypeArg(String name, VehicleType o){
        super(name);
        typeArg = o;
    }

    public VehicleType getTypeArg() {
        return typeArg;
    }
}
