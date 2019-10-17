package plivo_sdet.apiclients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import plivo_sdet.logger.ApiLogger;
import plivo_sdet.utils.PropertyUtils;

public class ApiClient {

	private static RequestSpecification requestSpecification;

	public static PropertyUtils propertyUtils = new PropertyUtils();

	private RequestSpecification getAuthenticatedRequestHandle() {
		RequestSpecification requestSpecification = RestAssured.given()
				.header("Authorization", "Bearer "+propertyUtils.getAuthToken())
				.contentType(propertyUtils.getContentType())
				.baseUri(propertyUtils.getBaseUrl());
		return requestSpecification;
	}

	public Response makeGetRequest(String endPoint) {
		requestSpecification = getAuthenticatedRequestHandle();
		ApiLogger.logInfo("Preparing get request with endPoint as ::{} "+endPoint);
		return requestSpecification.get(endPoint);
	}

	public Response makePostRequest(String endPoint, String payLoad) {
		requestSpecification = getAuthenticatedRequestHandle();
		ApiLogger.logInfo("Preparing payload as ::{} "+payLoad);
		requestSpecification.body(payLoad);
		ApiLogger.logInfo("Making post with endPoint as ::{} "+endPoint);
		return requestSpecification.post(endPoint);
	}
}
