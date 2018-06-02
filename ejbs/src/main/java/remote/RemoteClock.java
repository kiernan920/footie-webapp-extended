package remote;

import javax.ejb.Remote;
import javax.ejb.Stateless;


@Remote(ClockInterface.class)
@Stateless
public class RemoteClock implements ClockInterface {

	@Override
	public int getTimeToMeet(){
   	    return 5;
	}
}
