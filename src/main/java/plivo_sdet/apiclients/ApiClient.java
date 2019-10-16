package plivo_sdet.apiclients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import plivo_sdet.logger.ApiLogger;
import plivo_sdet.utils.PropertyUtils;

public class ApiClient {
	
	protected static  RequestSpecification requestSpecification;

	private PropertyUtils propertyUtils = new PropertyUtils();
	
	protected RequestSpecification getAuthenticatedRequestHandle() {
    	RequestSpecification requestSpecification = RestAssured.given()
    			.header("Authorization", "Bearer "+propertyUtils.getAuthToken())
    			.contentType(propertyUtils.getContentType())
    			.baseUri(propertyUtils.getBaseUrl());
    	return requestSpecification;
    }

	protected Response makeGetRequest(String endPoint) {
		requestSpecification = getAuthenticatedRequestHandle();
    	ApiLogger.logInfo("Preparing get request with endPoint as ::{} "+endPoint);
		return requestSpecification.get(endPoint);
    }

	protected Response makePostRequest(String endPoint, String payLoad) {
		requestSpecification = getAuthenticatedRequestHandle();
    	ApiLogger.logInfo("Preparing payload as ::{} "+payLoad);
    	requestSpecification.body(payLoad);
    	ApiLogger.logInfo("Making post with endPoint as ::{} "+endPoint);
		return requestSpecification.post(endPoint);
    }
}
