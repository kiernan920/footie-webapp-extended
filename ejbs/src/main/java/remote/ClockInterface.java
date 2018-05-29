package remote;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
public interface ClockInterface {

    public int getTimeToMeet();
}