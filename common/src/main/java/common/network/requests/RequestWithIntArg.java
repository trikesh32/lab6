package common.network.requests;

public class RequestWithIntArg extends Request{
    private Integer integerArg;
    public RequestWithIntArg(String name, Integer a){
        super(name);
        this.integerArg = a;
    }

    public Integer getIntegerArg() {
        return integerArg;
    }
}
