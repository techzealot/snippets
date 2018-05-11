import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyCase {
	@Test
	public void buf(){
		ByteBuf b=Unpooled.buffer();
		System.out.println(b);
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance(TimeZone.getTimeZone("GMT+01:00"));
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c1.getTime());
		System.out.println(c2.getTime());
	}

}
