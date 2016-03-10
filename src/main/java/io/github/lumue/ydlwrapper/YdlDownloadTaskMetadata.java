
package io.github.lumue.ydlwrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
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
    "extractor",
    "_type",
    "title",
    "extractor_key",
    "webpage_url",
    "entries",
    "id",
    "webpage_url_basename"
})
public class YdlDownloadTaskMetadata {

    @JsonProperty("extractor")
    private String extractor;
    @JsonProperty("_type")
    private String Type;
    @JsonProperty("title")
    private String title;
    @JsonProperty("extractor_key")
    private String extractorKey;
    @JsonProperty("webpage_url")
    private String webpageUrl;
    @JsonProperty("entries")
    @Valid
    private List<Entry> entries = new ArrayList<Entry>();
    @JsonProperty("id")
    private String id;
    @JsonProperty("webpage_url_basename")
    private String webpageUrlBasename;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public YdlDownloadTaskMetadata() {
    }

    /**
     * 
     * @param id
     * @param title
     * @param Type
     * @param extractor
     * @param entries
     * @param webpageUrlBasename
     * @param extractorKey
     * @param webpageUrl
     */
    public YdlDownloadTaskMetadata(String extractor, String Type, String title, String extractorKey, String webpageUrl, List<Entry> entries, String id, String webpageUrlBasename) {
        this.extractor = extractor;
        this.Type = Type;
        this.title = title;
        this.extractorKey = extractorKey;
        this.webpageUrl = webpageUrl;
        this.entries = entries;
        this.id = id;
        this.webpageUrlBasename = webpageUrlBasename;
    }

    /**
     * 
     * @return
     *     The extractor
     */
    @JsonProperty("extractor")
    public String getExtractor() {
        return extractor;
    }

    /**
     * 
     * @param extractor
     *     The extractor
     */
    @JsonProperty("extractor")
    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }

    public YdlDownloadTaskMetadata withExtractor(String extractor) {
        this.extractor = extractor;
        return this;
    }

    /**
     * 
     * @return
     *     The Type
     */
    @JsonProperty("_type")
    public String getType() {
        return Type;
    }

    /**
     * 
     * @param Type
     *     The _type
     */
    @JsonProperty("_type")
    public void setType(String Type) {
        this.Type = Type;
    }

    public YdlDownloadTaskMetadata withType(String Type) {
        this.Type = Type;
        return this;
    }

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public YdlDownloadTaskMetadata withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 
     * @return
     *     The extractorKey
     */
    @JsonProperty("extractor_key")
    public String getExtractorKey() {
        return extractorKey;
    }

    /**
     * 
     * @param extractorKey
     *     The extractor_key
     */
    @JsonProperty("extractor_key")
    public void setExtractorKey(String extractorKey) {
        this.extractorKey = extractorKey;
    }

    public YdlDownloadTaskMetadata withExtractorKey(String extractorKey) {
        this.extractorKey = extractorKey;
        return this;
    }

    /**
     * 
     * @return
     *     The webpageUrl
     */
    @JsonProperty("webpage_url")
    public String getWebpageUrl() {
        return webpageUrl;
    }

    /**
     * 
     * @param webpageUrl
     *     The webpage_url
     */
    @JsonProperty("webpage_url")
    public void setWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
    }

    public YdlDownloadTaskMetadata withWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
        return this;
    }

    /**
     * 
     * @return
     *     The entries
     */
    @JsonProperty("entries")
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * 
     * @param entries
     *     The entries
     */
    @JsonProperty("entries")
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public YdlDownloadTaskMetadata withEntries(List<Entry> entries) {
        this.entries = entries;
        return this;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public YdlDownloadTaskMetadata withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The webpageUrlBasename
     */
    @JsonProperty("webpage_url_basename")
    public String getWebpageUrlBasename() {
        return webpageUrlBasename;
    }

    /**
     * 
     * @param webpageUrlBasename
     *     The webpage_url_basename
     */
    @JsonProperty("webpage_url_basename")
    public void setWebpageUrlBasename(String webpageUrlBasename) {
        this.webpageUrlBasename = webpageUrlBasename;
    }

    public YdlDownloadTaskMetadata withWebpageUrlBasename(String webpageUrlBasename) {
        this.webpageUrlBasename = webpageUrlBasename;
        return this;
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

    public YdlDownloadTaskMetadata withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(extractor).append(Type).append(title).append(extractorKey).append(webpageUrl).append(entries).append(id).append(webpageUrlBasename).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof YdlDownloadTaskMetadata) == false) {
            return false;
        }
        YdlDownloadTaskMetadata rhs = ((YdlDownloadTaskMetadata) other);
        return new EqualsBuilder().append(extractor, rhs.extractor).append(Type, rhs.Type).append(title, rhs.title).append(extractorKey, rhs.extractorKey).append(webpageUrl, rhs.webpageUrl).append(entries, rhs.entries).append(id, rhs.id).append(webpageUrlBasename, rhs.webpageUrlBasename).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
