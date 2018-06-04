package remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;


@Remote(ClockInterface.class)
@Stateless
public class RemoteClock implements ClockInterface {

	Logger logger = LoggerFactory.getLogger(RemoteClock.class);

	@Override
	public int getTimeToMeet(){
		logger.info("Remote call received");
   	    return 5;
	}
}
