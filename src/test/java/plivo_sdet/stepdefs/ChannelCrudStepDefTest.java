package plivo_sdet.stepdefs;


import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.github.javafaker.Faker;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import plivo_sdet.apiclients.ApiClient;
import plivo_sdet.logger.ApiLogger;
import plivo_sdet.mockdatagenerators.Channel;
import plivo_sdet.mockdatagenerators.ChannelUtils;

public class ChannelCrudStepDefTest<T> {

	private ApiClient apiClient;
	private ChannelUtils channelUtils;
	private JsonPath jsonPath;
	private Channel channel;
	private Faker faker;
	private String channelPayload;
	private Response response;
	private List<Map<String, T>> channelsList;

	@Before
	public void setUp() {
		apiClient =  new ApiClient();
		channelUtils =  new ChannelUtils();
		apiClient.init();
		channel = channelUtils.createChannelPayload();
		faker =  new Faker();
	}

	@Given("^I create a new Channel via \"([^\"]*)\" method$")
	public void i_create_a_new_Channel_via_method(String method)  {
		channelPayload = channelUtils.prepareChannelPayload(channel);
		response = apiClient.makePostRequest(method,channelPayload);
		jsonPath = response.jsonPath();
		ApiLogger.logInfo("Create Channel Response :: {} "+response.asString());
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(jsonPath.getBoolean("ok"), true);
	}

	@Given("^I Join the newly created Channel via \"([^\"]*)\" method$")
	public void i_Join_the_newly_created_Channel_via_method(String method) {
		response = apiClient.makePostRequest(method,channelPayload);
		jsonPath = response.jsonPath();
		ApiLogger.logInfo("Join Channel Response :: {} "+response.asString());
		channel.setChannel(jsonPath.getString("channel.id"));
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(jsonPath.getBoolean("ok"), true);
	}

	@Then("^Rename the Channel via \"([^\"]*)\" method$")
	public void rename_the_Channel_via_method(String method) {
		channel.setName(faker.name().firstName().toLowerCase());
		//Modify the pay load with new channel name
		channelPayload = channelUtils.prepareChannelPayload(channel);
		response = apiClient.makePostRequest(method,channelPayload);
		jsonPath = response.jsonPath();
		ApiLogger.logInfo("Rename Channel Response :: {} "+response.asString());
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Then("^I List all Channels via \"([^\"]*)\" method$")
	public void i_List_all_Channels_via_method(String method) throws InterruptedException {
		Thread.sleep(5000);
		response = apiClient.makeGetRequest(method);
		jsonPath = response.jsonPath();
		ApiLogger.logInfo("List Channel Response :: {} "+response.asString());
		channelsList = jsonPath.getList("channels");
	}

	@Then("^I Validate if the Channel name has changed successfully$")
	public void i_Validate_if_the_Channel_name_has_changed_successfully() {
		for(int i=0;i<channelsList.size();i++) {
			if(channelsList.get(i).get("id").equals(channel.getChannel())) {
				Assert.assertEquals(channelsList.get(i).get("name"),channel.getName());
				return;
			}
		}
		Assert.fail("Channel Rename Failed");
	}

	@Then("^I Archive the Channel via \"([^\"]*)\" method$")
	public void i_Archive_the_Channel_via_method(String method) {
		response = apiClient.makePostRequest(method,channelPayload);
		jsonPath = response.jsonPath();
		ApiLogger.logInfo("Archive Channel Response :: {} "+response.asString());
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(jsonPath.getBoolean("ok"), true);
	}

	@Then("^I Validate if the Channel is archived successfully via \"([^\"]*)\" method$")
	public void i_Validate_if_the_Channel_is_archived_successfully_via_method(String method) throws Throwable {
		response = apiClient.makeGetRequest(method);
		jsonPath = response.jsonPath();
		ApiLogger.logInfo("List Channel Response after archiving :: {} "+response.asString());
		channelsList =   jsonPath.getList("channels");
		for(int i=0;i<channelsList.size();i++) {
			if(channelsList.get(i).get("id").equals(channel.getChannel())) {
				Assert.assertEquals(channelsList.get(i).get("is_archived"),true);
				return;
			}
		}
		Assert.fail("Channel archived failed");
	}
}
