
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.ws.rs.client.Client myClient = ClientBuilder.newClient();
		WebTarget target = myClient.target("http://localhost:8080/MeetingScheduler/rest/RootService");
        Response myResponse = target.request(MediaType.TEXT_PLAIN).get();
        System.out.println(myResponse.readEntity(String.class));
	}

}
