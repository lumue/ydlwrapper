package io.github.lumue.ydlwrapper.metadata.single_info_json;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "asr",
        "tbr",
        "protocol",
        "format",
        "Formatnote",
        "height",
        "preference",
        "Formatid",
        "language",
        "http_headers",
        "url",
        "vcodec",
        "width",
        "ext",
        "filesize",
        "fps",
        "acodec",
        "abr",
        "player_url"
})
public class RequestedFormat {

    @JsonProperty("asr")
    private Object asr;
    @JsonProperty("tbr")
    private String tbr;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("format")
    private String format;
    @JsonProperty("Formatnote")
    private String formatNote;
    @JsonProperty("height")
    private String height;
    @JsonProperty("preference")
    private String preference;
    @JsonProperty("format_id")
    private String formatId;
    @JsonProperty("language")
    private Object language;
    @JsonProperty("http_headers")
    private HttpHeaders httpHeaders;
    @JsonProperty("url")
    private String url;
    @JsonProperty("vcodec")
    private String vcodec;
    @JsonProperty("width")
    private String width;
    @JsonProperty("ext")
    private String ext;
    @JsonProperty("filesize")
    private String filesize;
    @JsonProperty("fps")
    private String fps;
    @JsonProperty("acodec")
    private String acodec;
    @JsonProperty("abr")
    private String abr;
    @JsonProperty("player_url")
    private Object playerUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public RequestedFormat() {
    }

    /**
     * @param httpHeaders
     * @param protocol
     * @param fps
     * @param width
     * @param asr
     * @param format
     * @param acodec
     * @param ext
     * @param url
     * @param vcodec
     * @param abr
     * @param filesize
     * @param formatNote
     * @param height
     * @param formatId
     * @param preference
     * @param language
     * @param tbr
     * @param playerUrl
     */
    public RequestedFormat(Object asr, String tbr, String protocol, String format, String formatNote, String height, String preference, String formatId, Object language, HttpHeaders httpHeaders, String url, String vcodec, String width, String ext, String filesize, String fps, String acodec, String abr, Object playerUrl) {
        this.asr = asr;
        this.tbr = tbr;
        this.protocol = protocol;
        this.format = format;
        this.formatNote = formatNote;
        this.height = height;
        this.preference = preference;
        this.formatId = formatId;
        this.language = language;
        this.httpHeaders = httpHeaders;
        this.url = url;
        this.vcodec = vcodec;
        this.width = width;
        this.ext = ext;
        this.filesize = filesize;
        this.fps = fps;
        this.acodec = acodec;
        this.abr = abr;
        this.playerUrl = playerUrl;
    }

    /**
     * @return The asr
     */
    @JsonProperty("asr")
    public Object getAsr() {
        return asr;
    }

    /**
     * @param asr The asr
     */
    @JsonProperty("asr")
    public void setAsr(Object asr) {
        this.asr = asr;
    }

    /**
     * @return The tbr
     */
    @JsonProperty("tbr")
    public String getTbr() {
        return tbr;
    }

    /**
     * @param tbr The tbr
     */
    @JsonProperty("tbr")
    public void setTbr(String tbr) {
        this.tbr = tbr;
    }

    /**
     * @return The protocol
     */
    @JsonProperty("protocol")
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol The protocol
     */
    @JsonProperty("protocol")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return The format
     */
    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    /**
     * @param format The format
     */
    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return The formatNote
     */
    @JsonProperty("Formatnote")
    public String getFormatNote() {
        return formatNote;
    }

    /**
     * @param formatNote The Formatnote
     */
    @JsonProperty("Formatnote")
    public void setFormatNote(String formatNote) {
        this.formatNote = formatNote;
    }

    /**
     * @return The height
     */
    @JsonProperty("height")
    public String getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    @JsonProperty("height")
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * @return The preference
     */
    @JsonProperty("preference")
    public String getPreference() {
        return preference;
    }

    /**
     * @param preference The preference
     */
    @JsonProperty("preference")
    public void setPreference(String preference) {
        this.preference = preference;
    }

    /**
     * @return The formatId
     */
    @JsonProperty("format_id")
    public String getFormatId() {
        return formatId;
    }

    /**
     * @param formatId The Formatid
     */
    @JsonProperty("format_id")
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    /**
     * @return The language
     */
    @JsonProperty("language")
    public Object getLanguage() {
        return language;
    }

    /**
     * @param language The language
     */
    @JsonProperty("language")
    public void setLanguage(Object language) {
        this.language = language;
    }

    /**
     * @return The httpHeaders
     */
    @JsonProperty("http_headers")
    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    /**
     * @param httpHeaders The http_headers
     */
    @JsonProperty("http_headers")
    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    /**
     * @return The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The vcodec
     */
    @JsonProperty("vcodec")
    public String getVcodec() {
        return vcodec;
    }

    /**
     * @param vcodec The vcodec
     */
    @JsonProperty("vcodec")
    public void setVcodec(String vcodec) {
        this.vcodec = vcodec;
    }

    /**
     * @return The width
     */
    @JsonProperty("width")
    public String getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    @JsonProperty("width")
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * @return The ext
     */
    @JsonProperty("ext")
    public String getExt() {
        return ext;
    }

    /**
     * @param ext The ext
     */
    @JsonProperty("ext")
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * @return The filesize
     */
    @JsonProperty("filesize")
    public String getFilesize() {
        return filesize;
    }

    /**
     * @param filesize The filesize
     */
    @JsonProperty("filesize")
    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    /**
     * @return The fps
     */
    @JsonProperty("fps")
    public String getFps() {
        return fps;
    }

    /**
     * @param fps The fps
     */
    @JsonProperty("fps")
    public void setFps(String fps) {
        this.fps = fps;
    }

    /**
     * @return The acodec
     */
    @JsonProperty("acodec")
    public String getAcodec() {
        return acodec;
    }

    /**
     * @param acodec The acodec
     */
    @JsonProperty("acodec")
    public void setAcodec(String acodec) {
        this.acodec = acodec;
    }

    /**
     * @return The abr
     */
    @JsonProperty("abr")
    public String getAbr() {
        return abr;
    }

    /**
     * @param abr The abr
     */
    @JsonProperty("abr")
    public void setAbr(String abr) {
        this.abr = abr;
    }

    /**
     * @return The playerUrl
     */
    @JsonProperty("player_url")
    public Object getPlayerUrl() {
        return playerUrl;
    }

    /**
     * @param playerUrl The player_url
     */
    @JsonProperty("player_url")
    public void setPlayerUrl(Object playerUrl) {
        this.playerUrl = playerUrl;
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
        return new HashCodeBuilder().append(asr).append(tbr).append(protocol).append(format).append(formatNote).append(height).append(preference).append(formatId).append(language).append(httpHeaders).append(url).append(vcodec).append(width).append(ext).append(filesize).append(fps).append(acodec).append(abr).append(playerUrl).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RequestedFormat) == false) {
            return false;
        }
        RequestedFormat rhs = ((RequestedFormat) other);
        return new EqualsBuilder().append(asr, rhs.asr).append(tbr, rhs.tbr).append(protocol, rhs.protocol).append(format, rhs.format).append(formatNote, rhs.formatNote).append(height, rhs.height).append(preference, rhs.preference).append(formatId, rhs.formatId).append(language, rhs.language).append(httpHeaders, rhs.httpHeaders).append(url, rhs.url).append(vcodec, rhs.vcodec).append(width, rhs.width).append(ext, rhs.ext).append(filesize, rhs.filesize).append(fps, rhs.fps).append(acodec, rhs.acodec).append(abr, rhs.abr).append(playerUrl, rhs.playerUrl).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
