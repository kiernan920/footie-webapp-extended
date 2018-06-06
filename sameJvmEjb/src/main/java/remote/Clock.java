package remote;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local(ClockInterfaceTwo.class)
public class Clock implements ClockInterfaceTwo {

    private Logger logger = LoggerFactory.getLogger(Clock.class);

    public int getTimeToMeet(){
        logger.info("This bean resides in the same JVM");
        return 10;
    }

}
