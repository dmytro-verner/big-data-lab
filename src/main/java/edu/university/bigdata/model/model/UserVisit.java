package edu.university.bigdata.model.model;

import com.datastax.driver.mapping.annotations.Table;

import java.util.Arrays;
import java.util.Iterator;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Table(keyspace = "user_stats", name = "user_visits")
public class UserVisit {

    private String sourceIp;
    private String destURL;
    private String visitDate;
    private float adRevenue;
    private String userAgent;
    private String countryCode;
    private String languageCode;
    private String searchWord;
    private int duration;

    public UserVisit() {
    }

    public UserVisit(String sourceIp, String destinationUrl, String visitDate, float adRevenue, String userAgent,
                     String countryCode, String languageCode, String searchWord, int duration) {
        this.sourceIp = sourceIp;
        this.destURL = destinationUrl;
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

    public String getDestURL() {
        return destURL;
    }

    public void setDestURL(String destinationUrl) {
        this.destURL = destinationUrl;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public float getAdRevenue() {
        return adRevenue;
    }

    public void setAdRevenue(float adRevenue) {
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

    public static UserVisit toUserVisit(String record) {
        Iterator<String> values = Arrays.asList(record.split(",")).iterator();

        return new UserVisit(
                values.next(),
                values.next(),
                values.next(),
                parseFloat(values.next()),
                values.next(),
                values.next(),
                values.next(),
                values.next(),
                parseInt(values.next())
        );
    }
}