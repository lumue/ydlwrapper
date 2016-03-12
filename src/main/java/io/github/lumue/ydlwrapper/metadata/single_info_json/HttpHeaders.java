
package io.github.lumue.ydlwrapper.metadata.single_info_json;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "Accept-Charset",
    "Accept-Language",
    "Accept-Encoding",
    "Accept",
    "User-Agent"
})
public class HttpHeaders {

    @JsonProperty("Accept-Charset")
    private String AcceptCharset;
    @JsonProperty("Accept-Language")
    private String AcceptLanguage;
    @JsonProperty("Accept-Encoding")
    private String AcceptEncoding;
    @JsonProperty("Accept")
    private String Accept;
    @JsonProperty("User-Agent")
    private String UserAgent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public HttpHeaders() {
    }

    /**
     * 
     * @param AcceptLanguage
     * @param AcceptEncoding
     * @param UserAgent
     * @param AcceptCharset
     * @param Accept
     */
    public HttpHeaders(String AcceptCharset, String AcceptLanguage, String AcceptEncoding, String Accept, String UserAgent) {
        this.AcceptCharset = AcceptCharset;
        this.AcceptLanguage = AcceptLanguage;
        this.AcceptEncoding = AcceptEncoding;
        this.Accept = Accept;
        this.UserAgent = UserAgent;
    }

    /**
     * 
     * @return
     *     The AcceptCharset
     */
    @JsonProperty("Accept-Charset")
    public String getAcceptCharset() {
        return AcceptCharset;
    }

    /**
     * 
     * @param AcceptCharset
     *     The Accept-Charset
     */
    @JsonProperty("Accept-Charset")
    public void setAcceptCharset(String AcceptCharset) {
        this.AcceptCharset = AcceptCharset;
    }

    /**
     * 
     * @return
     *     The AcceptLanguage
     */
    @JsonProperty("Accept-Language")
    public String getAcceptLanguage() {
        return AcceptLanguage;
    }

    /**
     * 
     * @param AcceptLanguage
     *     The Accept-Language
     */
    @JsonProperty("Accept-Language")
    public void setAcceptLanguage(String AcceptLanguage) {
        this.AcceptLanguage = AcceptLanguage;
    }

    /**
     * 
     * @return
     *     The AcceptEncoding
     */
    @JsonProperty("Accept-Encoding")
    public String getAcceptEncoding() {
        return AcceptEncoding;
    }

    /**
     * 
     * @param AcceptEncoding
     *     The Accept-Encoding
     */
    @JsonProperty("Accept-Encoding")
    public void setAcceptEncoding(String AcceptEncoding) {
        this.AcceptEncoding = AcceptEncoding;
    }

    /**
     * 
     * @return
     *     The Accept
     */
    @JsonProperty("Accept")
    public String getAccept() {
        return Accept;
    }

    /**
     * 
     * @param Accept
     *     The Accept
     */
    @JsonProperty("Accept")
    public void setAccept(String Accept) {
        this.Accept = Accept;
    }

    /**
     * 
     * @return
     *     The UserAgent
     */
    @JsonProperty("User-Agent")
    public String getUserAgent() {
        return UserAgent;
    }

    /**
     * 
     * @param UserAgent
     *     The User-Agent
     */
    @JsonProperty("User-Agent")
    public void setUserAgent(String UserAgent) {
        this.UserAgent = UserAgent;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(AcceptCharset).append(AcceptLanguage).append(AcceptEncoding).append(Accept).append(UserAgent).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HttpHeaders) == false) {
            return false;
        }
        HttpHeaders rhs = ((HttpHeaders) other);
        return new EqualsBuilder().append(AcceptCharset, rhs.AcceptCharset).append(AcceptLanguage, rhs.AcceptLanguage).append(AcceptEncoding, rhs.AcceptEncoding).append(Accept, rhs.Accept).append(UserAgent, rhs.UserAgent).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
