

package server;

import java.util.Properties;

public interface AutoServable {

	public void serve(int port);
	public void buildAuto(Properties props);
}
