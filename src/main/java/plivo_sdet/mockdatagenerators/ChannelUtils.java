package plivo_sdet.mockdatagenerators;


import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ChannelUtils {
	
	Faker faker = new Faker();
	Channel channel;
	
	public String prepareChannelPayload() {
		Channel channel = createChannelPayload();
		Gson gson = new GsonBuilder().create();
		String payLoadAsString = gson.toJson(channel);
        return payLoadAsString;
	}
	
	public String prepareChannelPayload(Channel channel) {
		Gson gson = new GsonBuilder().create();
		String payLoadAsString = gson.toJson(channel);
        return payLoadAsString;
	}
	
	public Channel createChannelPayload() {
		channel = new Channel(faker.name().firstName().toLowerCase());
		return channel;
	}
}
