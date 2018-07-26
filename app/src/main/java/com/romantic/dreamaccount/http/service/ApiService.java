package com.romantic.dreamaccount.http.service;

//import com.bluemobi.mvptest.bean.ClientBean;

import com.romantic.dreamaccount.bean.ClientBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 接口
 * Created by chenMeng on 2017/9/5.
 */

public interface ApiService {

//    @POST("clients/stations.json")
//    Observable<ClientBean> register(@Body RequestBody requestBody);//设备激活
//
    @POST("app.json")
    Observable<ClientBean> registerApp(@Body RequestBody requestBody);//app激活
//
//    @PATCH("clients/{client_uuid}.json")
//    Observable<ClientsUpdateModel> updateAppInfo(@Path("client_uuid") String client_uuid, @Body RequestBody requestBody);//1.3.更新客户端信息
//
//    @POST("groups.json")
//    Observable<CreateGroupModel> createGroup(@Body RequestBody requestBody);//创建群组
//
//    @GET("clients/{client_uuid}/buddies.json")
//    Observable<GroupListDataModel> groupList(@Path("client_uuid") String client_uuid);//群组列表
//
//    @DELETE("groups/{group_uuid}.json")
//    Observable<GroupDelModel> delGroup(@Path("group_uuid") String group_uuid);//删除群组
//
//    @PUT("groups/{group_uuid}.json")
//    Observable<GroupUpdateModel> updateGroup(@Path("group_uuid") String group_uuid, @Body RequestBody requestBody);//更新群
//
//    @POST("groups/{group_uuid}/members.json")
//    Observable<MemberAddModel> addMember(@Path("group_uuid") String group_uuid, @Body RequestBody requestBody);//添加成员
//
//    @DELETE("groups/{group_uuid}/members/{member_client_id}.json")
//    Observable<MemberDelModel> deleteMember(@Path("group_uuid") String group_uuid, @Path("member_client_id") String member_client_id);//删除成员
//
//    @POST("groups/getInfo/{group_uuid}.json")
//    Observable<GroupListModel> getInfoGroup(@Path("group_uuid") String group_uuid);//获取群组信息
//
//    @POST("groups/getMembersTrack/{group_uuid}.json")
//    Observable<GroupMembersTrackModel> getMembersTrack(@Path("group_uuid") String group_uuid);//获取每个群成员的最近一个位置的列表
//
//    @POST("clients/{client_uuid}/device_events.json")
//    Observable<UpLoadLocationModel> uploadLocation(@Path("client_uuid") String client_uuid, @Body RequestBody requestBody);//位置上报
//
//    @GET("groups/{to_group_uuid}/voice_message_server.json")
//    Observable<VoiceMsgDataModel> getVoiceMsgServiceUrl(@Path("to_group_uuid") String to_group_uuid);//获取语音消息服务URL
//
//    @POST("groups/{to_group_uuid}/voice_messages.json")
//    Observable<VoiceMessageListModel> getVoiceMessageList(@Path("to_group_uuid") String to_group_uuid, @Query("lastId") String id);//获取语音消息列表
//
//    @POST("groups/{to_group_uuid}/voice_message_compleated.json")
//    Observable<SendCompletedModel> sendCompleted(@Path("to_group_uuid") String to_group_uuid, @Body RequestBody requestBody);//4.6.发送完成
//
//    @GET("device/devices.json")
//    Observable<DeviceListModel> getDevicesList();//7.1.1.设备列表
//
//    @POST("device/{serial_number}/bind.json")
//    Observable<BindDeviceModel> bindDevice(@Path("serial_number") String serial_number);//7.1.2.绑定设备
//
//    @POST("device/{device_uuid}/getInfoByUuid.json")
//    Observable<DeviceInfoModel> getDeviceInfoByUUid(@Path("device_uuid") String device_uuid);//7.1.4.设备详细信息
//
//    @POST("device/{serial_number}/getInfoBySerial.json")
//    Observable<DeviceInfoModel> getDeviceInfoBySerial(@Path("serial_number") String serial_number);//7.1.4.设备详细信息
//
//    @POST("device/{device_uuid}/deletebind.json")
//    Observable<DeviceDeleteModel> deleteDevice(@Path("device_uuid") String device_uuid);//7.1.3.删除设备
//
//    @POST("device/{device_uuid}/getTrackList.json")
//    Observable<DeviceTrackListModel> getDeviceTrackList(@Path("device_uuid") String device_uuid, @Body RequestBody requestBody);//7.2.获取设备轨迹
//
//    @POST("device/{device_uuid}/events.json")
//    Observable<DeviceEventsListModel> getDeviceEventList(@Path("device_uuid") String device_uuid, @Query("pageNum") String page, @Body RequestBody requestBody);//7.2.获取设备事件
//
//    @POST("ota/version.json")
//    Observable<UpdateModel> updateApp(@Query("appKey") String appKey, @Query("appSecret") String appSecret, @Query("ver") String ver);//app更新
//
//    @POST("groups/{group_uuid}/candidates.json")
//    Observable<GroupInvitationModel> groupInvitation(@Path("group_uuid") String group_uuid);//邀请加组
//
//    @POST("system/{client_uuid}.json")
//    Observable<SystemMessageModel> getSystemMessage(@Path("client_uuid") String client_uuid);//系统消息

}
