package remote;

import javax.ejb.Remote;
import javax.ejb.Stateless;


@Remote
@Stateless
public class RemoteClock implements ClockInterface {

	public int getTimeToMeet(){
   	    return 5;
	}
}
