package model;

import java.util.Date;

public class UserVisit {
    private String sourceIp;
    private String destinationUrl;
    private Date visitDate;
    private double adRevenue;
    private String userAgent;
    private String countryCode;
    private String languageCode;
    private String searchWord;
    private int duration;

    public UserVisit() {
    }

    public UserVisit(String sourceIp, String destinationUrl, Date visitDate, double adRevenue, String userAgent,
                     String countryCode, String languageCode, String searchWord, int duration) {
        this.sourceIp = sourceIp;
        this.destinationUrl = destinationUrl;
        this.visitDate = visitDate;
        this.adRevenue = adRevenue;
        this.userAgent = userAgent;
        this.countryCode = countryCode;
        this.languageCode = languageCode;
        this.searchWord = searchWord;
        this.duration = duration;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public double getAdRevenue() {
        return adRevenue;
    }

    public void setAdRevenue(double adRevenue) {
        this.adRevenue = adRevenue;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}