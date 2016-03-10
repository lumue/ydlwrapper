
package io.github.lumue.ydlwrapper;

import java.util.HashMap;
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
    "http_headers",
    "tbr",
    "protocol",
    "format",
    "url",
    "filesize",
    "vcodec",
    "format_note",
    "abr",
    "player_url",
    "ext",
    "preference",
    "format_id",
    "acodec",
    "asr",
    "height",
    "language",
    "width",
    "fps",
    "container",
    "resolution"
})
public class Format {

    @JsonProperty("http_headers")
    @Valid
    private HttpHeaders httpHeaders;
    @JsonProperty("tbr")
    private long tbr;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("format")
    private String format;
    @JsonProperty("url")
    private String url;
    @JsonProperty("filesize")
    private long filesize;
    @JsonProperty("vcodec")
    private String vcodec;
    @JsonProperty("format_note")
    private String formatNote;
    @JsonProperty("abr")
    private long abr;
    @JsonProperty("player_url")
    private Object playerUrl;
    @JsonProperty("ext")
    private String ext;
    @JsonProperty("preference")
    private long preference;
    @JsonProperty("format_id")
    private String formatId;
    @JsonProperty("acodec")
    private String acodec;
    @JsonProperty("asr")
    private Object asr;
    @JsonProperty("height")
    private long height;
    @JsonProperty("language")
    private Object language;
    @JsonProperty("width")
    private long width;
    @JsonProperty("fps")
    private long fps;
    @JsonProperty("container")
    private String container;
    @JsonProperty("resolution")
    private String resolution;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Format() {
    }

    /**
     * 
     * @param httpHeaders
     * @param protocol
     * @param fps
     * @param width
     * @param resolution
     * @param asr
     * @param format
     * @param acodec
     * @param url
     * @param vcodec
     * @param ext
     * @param abr
     * @param filesize
     * @param formatNote
     * @param height
     * @param formatId
     * @param preference
     * @param container
     * @param language
     * @param tbr
     * @param playerUrl
     */
    public Format(HttpHeaders httpHeaders, long tbr, String protocol, String format, String url, long filesize, String vcodec, String formatNote, long abr, Object playerUrl, String ext, long preference, String formatId, String acodec, Object asr, long height, Object language, long width, long fps, String container, String resolution) {
        this.httpHeaders = httpHeaders;
        this.tbr = tbr;
        this.protocol = protocol;
        this.format = format;
        this.url = url;
        this.filesize = filesize;
        this.vcodec = vcodec;
        this.formatNote = formatNote;
        this.abr = abr;
        this.playerUrl = playerUrl;
        this.ext = ext;
        this.preference = preference;
        this.formatId = formatId;
        this.acodec = acodec;
        this.asr = asr;
        this.height = height;
        this.language = language;
        this.width = width;
        this.fps = fps;
        this.container = container;
        this.resolution = resolution;
    }

    /**
     * 
     * @return
     *     The httpHeaders
     */
    @JsonProperty("http_headers")
    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    /**
     * 
     * @param httpHeaders
     *     The http_headers
     */
    @JsonProperty("http_headers")
    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public Format withHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
        return this;
    }

    /**
     * 
     * @return
     *     The tbr
     */
    @JsonProperty("tbr")
    public long getTbr() {
        return tbr;
    }

    /**
     * 
     * @param tbr
     *     The tbr
     */
    @JsonProperty("tbr")
    public void setTbr(long tbr) {
        this.tbr = tbr;
    }

    public Format withTbr(long tbr) {
        this.tbr = tbr;
        return this;
    }

    /**
     * 
     * @return
     *     The protocol
     */
    @JsonProperty("protocol")
    public String getProtocol() {
        return protocol;
    }

    /**
     * 
     * @param protocol
     *     The protocol
     */
    @JsonProperty("protocol")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Format withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * 
     * @return
     *     The format
     */
    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    /**
     * 
     * @param format
     *     The format
     */
    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    public Format withFormat(String format) {
        this.format = format;
        return this;
    }

    /**
     * 
     * @return
     *     The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Format withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 
     * @return
     *     The filesize
     */
    @JsonProperty("filesize")
    public long getFilesize() {
        return filesize;
    }

    /**
     * 
     * @param filesize
     *     The filesize
     */
    @JsonProperty("filesize")
    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public Format withFilesize(long filesize) {
        this.filesize = filesize;
        return this;
    }

    /**
     * 
     * @return
     *     The vcodec
     */
    @JsonProperty("vcodec")
    public String getVcodec() {
        return vcodec;
    }

    /**
     * 
     * @param vcodec
     *     The vcodec
     */
    @JsonProperty("vcodec")
    public void setVcodec(String vcodec) {
        this.vcodec = vcodec;
    }

    public Format withVcodec(String vcodec) {
        this.vcodec = vcodec;
        return this;
    }

    /**
     * 
     * @return
     *     The formatNote
     */
    @JsonProperty("format_note")
    public String getFormatNote() {
        return formatNote;
    }

    /**
     * 
     * @param formatNote
     *     The format_note
     */
    @JsonProperty("format_note")
    public void setFormatNote(String formatNote) {
        this.formatNote = formatNote;
    }

    public Format withFormatNote(String formatNote) {
        this.formatNote = formatNote;
        return this;
    }

    /**
     * 
     * @return
     *     The abr
     */
    @JsonProperty("abr")
    public long getAbr() {
        return abr;
    }

    /**
     * 
     * @param abr
     *     The abr
     */
    @JsonProperty("abr")
    public void setAbr(long abr) {
        this.abr = abr;
    }

    public Format withAbr(long abr) {
        this.abr = abr;
        return this;
    }

    /**
     * 
     * @return
     *     The playerUrl
     */
    @JsonProperty("player_url")
    public Object getPlayerUrl() {
        return playerUrl;
    }

    /**
     * 
     * @param playerUrl
     *     The player_url
     */
    @JsonProperty("player_url")
    public void setPlayerUrl(Object playerUrl) {
        this.playerUrl = playerUrl;
    }

    public Format withPlayerUrl(Object playerUrl) {
        this.playerUrl = playerUrl;
        return this;
    }

    /**
     * 
     * @return
     *     The ext
     */
    @JsonProperty("ext")
    public String getExt() {
        return ext;
    }

    /**
     * 
     * @param ext
     *     The ext
     */
    @JsonProperty("ext")
    public void setExt(String ext) {
        this.ext = ext;
    }

    public Format withExt(String ext) {
        this.ext = ext;
        return this;
    }

    /**
     * 
     * @return
     *     The preference
     */
    @JsonProperty("preference")
    public long getPreference() {
        return preference;
    }

    /**
     * 
     * @param preference
     *     The preference
     */
    @JsonProperty("preference")
    public void setPreference(long preference) {
        this.preference = preference;
    }

    public Format withPreference(long preference) {
        this.preference = preference;
        return this;
    }

    /**
     * 
     * @return
     *     The formatId
     */
    @JsonProperty("format_id")
    public String getFormatId() {
        return formatId;
    }

    /**
     * 
     * @param formatId
     *     The format_id
     */
    @JsonProperty("format_id")
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public Format withFormatId(String formatId) {
        this.formatId = formatId;
        return this;
    }

    /**
     * 
     * @return
     *     The acodec
     */
    @JsonProperty("acodec")
    public String getAcodec() {
        return acodec;
    }

    /**
     * 
     * @param acodec
     *     The acodec
     */
    @JsonProperty("acodec")
    public void setAcodec(String acodec) {
        this.acodec = acodec;
    }

    public Format withAcodec(String acodec) {
        this.acodec = acodec;
        return this;
    }

    /**
     * 
     * @return
     *     The asr
     */
    @JsonProperty("asr")
    public Object getAsr() {
        return asr;
    }

    /**
     * 
     * @param asr
     *     The asr
     */
    @JsonProperty("asr")
    public void setAsr(Object asr) {
        this.asr = asr;
    }

    public Format withAsr(Object asr) {
        this.asr = asr;
        return this;
    }

    /**
     * 
     * @return
     *     The height
     */
    @JsonProperty("height")
    public long getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    @JsonProperty("height")
    public void setHeight(long height) {
        this.height = height;
    }

    public Format withHeight(long height) {
        this.height = height;
        return this;
    }

    /**
     * 
     * @return
     *     The language
     */
    @JsonProperty("language")
    public Object getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    @JsonProperty("language")
    public void setLanguage(Object language) {
        this.language = language;
    }

    public Format withLanguage(Object language) {
        this.language = language;
        return this;
    }

    /**
     * 
     * @return
     *     The width
     */
    @JsonProperty("width")
    public long getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    @JsonProperty("width")
    public void setWidth(long width) {
        this.width = width;
    }

    public Format withWidth(long width) {
        this.width = width;
        return this;
    }

    /**
     * 
     * @return
     *     The fps
     */
    @JsonProperty("fps")
    public long getFps() {
        return fps;
    }

    /**
     * 
     * @param fps
     *     The fps
     */
    @JsonProperty("fps")
    public void setFps(long fps) {
        this.fps = fps;
    }

    public Format withFps(long fps) {
        this.fps = fps;
        return this;
    }

    /**
     * 
     * @return
     *     The container
     */
    @JsonProperty("container")
    public String getContainer() {
        return container;
    }

    /**
     * 
     * @param container
     *     The container
     */
    @JsonProperty("container")
    public void setContainer(String container) {
        this.container = container;
    }

    public Format withContainer(String container) {
        this.container = container;
        return this;
    }

    /**
     * 
     * @return
     *     The resolution
     */
    @JsonProperty("resolution")
    public String getResolution() {
        return resolution;
    }

    /**
     * 
     * @param resolution
     *     The resolution
     */
    @JsonProperty("resolution")
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Format withResolution(String resolution) {
        this.resolution = resolution;
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

    public Format withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(httpHeaders).append(tbr).append(protocol).append(format).append(url).append(filesize).append(vcodec).append(formatNote).append(abr).append(playerUrl).append(ext).append(preference).append(formatId).append(acodec).append(asr).append(height).append(language).append(width).append(fps).append(container).append(resolution).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Format) == false) {
            return false;
        }
        Format rhs = ((Format) other);
        return new EqualsBuilder().append(httpHeaders, rhs.httpHeaders).append(tbr, rhs.tbr).append(protocol, rhs.protocol).append(format, rhs.format).append(url, rhs.url).append(filesize, rhs.filesize).append(vcodec, rhs.vcodec).append(formatNote, rhs.formatNote).append(abr, rhs.abr).append(playerUrl, rhs.playerUrl).append(ext, rhs.ext).append(preference, rhs.preference).append(formatId, rhs.formatId).append(acodec, rhs.acodec).append(asr, rhs.asr).append(height, rhs.height).append(language, rhs.language).append(width, rhs.width).append(fps, rhs.fps).append(container, rhs.container).append(resolution, rhs.resolution).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
