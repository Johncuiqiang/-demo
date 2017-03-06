package cn.ling.voice.entities;

import cn.ling.android.entities.BaseEntity;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class UserInfoEntity extends BaseEntity {

    public static final int TYPE_WORKER = 0;
    public static final int TYP_FOREMAN = 1;

    public static final int WORKTYP_SHUIDIANGONG = 1;
    public static final int WORKTYP_MUGONG = 2;
    public static final int WORKTYP_WAGONG = 3;
    public static final int WORKTYP_YOUGONG = 4;
    public static final int WORKTYP_XIAOGONG =5;

    private String userId;

    private String password;

    private String userType;

    private String personName;

    private String phoneNumber;

    private int age;

    private String nowCity;

    private String nowArea;

    private String nowAddress;

    private String idCard;

    private String oldProvince;

    private String oldCity;

    private String workTypeId;

    private String workType;

    private long workTime;

    private float workPrice;

    private String workNumber;

    private int userLevel;

    private String lat;

    private String lng;

    private String image;

    private int quota;

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getQuota() {
        return quota;
    }

    public String getUserId() {
        return userId;
    }

    public String getPwd() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public String getNowCity() {
        return nowCity;
    }

    public String getNowArea() {
        return nowArea;
    }

    public String getNowAddress() {
        return nowAddress;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getOldProvince() {
        return oldProvince;
    }

    public String getOldCity() {
        return oldCity;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public String getWorkType() {
        return workType;
    }

    public long getWorkTime() {
        return workTime;
    }

    public float getWorkPrice() {
        return workPrice;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getImage() {
        return image;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPwd(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNowCity(String nowCity) {
        this.nowCity = nowCity;
    }

    public void setNowArea(String nowArea) {
        this.nowArea = nowArea;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setOldProvince(String oldProvince) {
        this.oldProvince = oldProvince;
    }

    public void setOldCity(String oldCity) {
        this.oldCity = oldCity;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public void setWorkTime(long workTime) {
        this.workTime = workTime;
    }

    public void setWorkPrice(float workPrice) {
        this.workPrice = workPrice;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", personName='" + personName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", nowCity='" + nowCity + '\'' +
                ", nowArea='" + nowArea + '\'' +
                ", nowAddress='" + nowAddress + '\'' +
                ", idCard='" + idCard + '\'' +
                ", oldProvince='" + oldProvince + '\'' +
                ", oldCity='" + oldCity + '\'' +
                ", workTypeId='" + workTypeId + '\'' +
                ", workType='" + workType + '\'' +
                ", workTime=" + workTime +
                ", workPrice=" + workPrice +
                ", workNumber='" + workNumber + '\'' +
                ", userLevel=" + userLevel +
                ", lat=" + lat +
                ", lng=" + lng +
                ", image='" + image + '\'' +
                '}';
    }

}
