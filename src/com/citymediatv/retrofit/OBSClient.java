package com.citymediatv.retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

import com.citymediatv.data.ActivePlanDatum;
import com.citymediatv.data.ClientDatum;
import com.citymediatv.data.ClientnConfigDatum;
import com.citymediatv.data.DeviceDatum;
import com.citymediatv.data.EPGData;
import com.citymediatv.data.MediaDetailRes;
import com.citymediatv.data.MediaDetailsResDatum;
import com.citymediatv.data.OrderDatum;
import com.citymediatv.data.PlanDatum;
import com.citymediatv.data.ResourceIdentifier;
import com.citymediatv.data.ServiceDatum;
import com.citymediatv.data.StatusReqDatum;
import com.citymediatv.data.TemplateDatum;

public interface OBSClient {

	@GET("/mediadevices/client/{clientId}")
	ClientnConfigDatum getClientnConfigDataSync(
			@Path("clientId") String clientId);
	
	@GET("/mediadevices/{device}")
	void getMediaDevice(@Path("device") String device, Callback<DeviceDatum> cb);

	@GET("/orders/{clientId}/activeplans")
	void getActivePlans(@Path("clientId") String clientId,
			Callback<List<ActivePlanDatum>> cb);

	@GET("/clients/template")
	void getTemplate(Callback<TemplateDatum> cb);

	@GET("/plans?planType=prepaid")
	void getPrepaidPlans(Callback<List<PlanDatum>> cb);

	@GET("/planservices/{clientId}?serviceType=IPTV")
	ArrayList<ServiceDatum> getPlanServicesSync(
			@Path("clientId") String clientId);

	@GET("/planservices/{clientId}?serviceType=IPTV")
	void getPlanServices(@Path("clientId") String clientId,
			Callback<ArrayList<ServiceDatum>> cb);

	@GET("/epgprogramguide/{channelName}/{reqDate}")
	void getEPGDetails(@Path("channelName") String channelName,
			@Path("reqDate") String reqDate, Callback<EPGData> cb);

	@GET("/assets")
	void getPageCountAndMediaDetails(@Query("filterType") String category,
			@Query("pageNo") String pageNo, @Query("deviceId") String deviceId,
			Callback<MediaDetailRes> cb);

	@GET("/assetdetails/{mediaId}")
	void getMediaDetails(@Path("mediaId") String mediaId,
			@Query("eventId") String eventId,
			@Query("deviceId") String deviceId,
			Callback<MediaDetailsResDatum> cb);

	@GET("/clients/{clientId}")
	void getClinetDetails(@Path("clientId") String clientId,
			Callback<ClientDatum> cb);

	@GET("/orders/{clientId}/orders")
	void getClinetPackageDetails(@Path("clientId") String clientId,
			Callback<List<OrderDatum>> cb);
	
	@PUT("/mediadevices/{device}")
	ResourceIdentifier updateAppStatus(@Path("device") String device,@Body StatusReqDatum request);
}
