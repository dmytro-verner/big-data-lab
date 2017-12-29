package edu.university.bigdata.model.model;

import com.datastax.driver.mapping.annotations.Table;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Table(keyspace = "user_stats", name = "user_visits")
public class UserVisit implements Serializable {

    private String sourceip;
    private String desturl;
    private String visitdate;
    private Float adrevenue;
    private String useragent;
    private String countrycode;
    private String languagecode;
    private String searchword;
    private Integer duration;

    public UserVisit() {
    }

    public UserVisit(String sourceip, String desturl, String visitdate, float adrevenue, String useragent,
                     String countrycode, String languagecode, String searchword, int duration) {
        this.sourceip = sourceip;
        this.desturl = desturl;
        this.visitdate = visitdate;
        this.adrevenue = adrevenue;
        this.useragent = useragent;
        this.countrycode = countrycode;
        this.languagecode = languagecode;
        this.searchword = searchword;
        this.duration = duration;
    }

    public String getSourceip() {
        return sourceip;
    }

    public void setSourceip(String sourceip) {
        this.sourceip = sourceip;
    }

    public String getDesturl() {
        return desturl;
    }

    public void setDesturl(String destinationUrl) {
        this.desturl = destinationUrl;
    }

    public String getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(String visitdate) {
        this.visitdate = visitdate;
    }

    public Float getAdrevenue() {
        return adrevenue;
    }

    public void setAdrevenue(Float adrevenue) {
        this.adrevenue = adrevenue;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getLanguagecode() {
        return languagecode;
    }

    public void setLanguagecode(String languagecode) {
        this.languagecode = languagecode;
    }

    public String getSearchword() {
        return searchword;
    }

    public void setSearchword(String searchword) {
        this.searchword = searchword;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVisit userVisit = (UserVisit) o;
        return Objects.equals(sourceip, userVisit.sourceip) &&
                Objects.equals(desturl, userVisit.desturl) &&
                Objects.equals(visitdate, userVisit.visitdate) &&
                Objects.equals(adrevenue, userVisit.adrevenue) &&
                Objects.equals(useragent, userVisit.useragent) &&
                Objects.equals(countrycode, userVisit.countrycode) &&
                Objects.equals(languagecode, userVisit.languagecode) &&
                Objects.equals(searchword, userVisit.searchword) &&
                Objects.equals(duration, userVisit.duration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sourceip, desturl, visitdate, adrevenue, useragent, countrycode, languagecode, searchword, duration);
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