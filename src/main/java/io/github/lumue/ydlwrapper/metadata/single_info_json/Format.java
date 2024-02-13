package io.github.lumue.ydlwrapper.metadata.single_info_json;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "http_headers",
        "tbr",
        "protocol",
        "format",
        "url",
        "filesize",
        "vcodec",
        "Formatnote",
        "abr",
        "player_url",
        "ext",
        "preference",
        "Formatid",
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
    private HttpHeaders httpHeaders;
    @JsonProperty("tbr")
    private String tbr;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("format")
    private String format;
    @JsonProperty("url")
    private String url;
    @JsonProperty("filesize")
    private String filesize;
    @JsonProperty("vcodec")
    private String vcodec;
    @JsonProperty("Formatnote")
    private String formatNote;
    @JsonProperty("abr")
    private String abr;
    @JsonProperty("player_url")
    private Object playerUrl;
    @JsonProperty("ext")
    private String ext;
    @JsonProperty("preference")
    private String preference;
    @JsonProperty("format_id")
    private String formatId;
    @JsonProperty("acodec")
    private String acodec;
    @JsonProperty("asr")
    private Object asr;
    @JsonProperty("height")
    private String height;
    @JsonProperty("language")
    private Object language;
    @JsonProperty("width")
    private String width;
    @JsonProperty("fps")
    private String fps;
    @JsonProperty("container")
    private String container;
    @JsonProperty("resolution")
    private String resolution;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Format() {
    }

    /**
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
    public Format(HttpHeaders httpHeaders, String tbr, String protocol, String format, String url, String filesize, String vcodec, String formatNote, String abr, Object playerUrl, String ext, String preference, String formatId, String acodec, Object asr, String height, Object language, String width, String fps, String container, String resolution) {
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
     * @return The container
     */
    @JsonProperty("container")
    public String getContainer() {
        return container;
    }

    /**
     * @param container The container
     */
    @JsonProperty("container")
    public void setContainer(String container) {
        this.container = container;
    }

    /**
     * @return The resolution
     */
    @JsonProperty("resolution")
    public String getResolution() {
        return resolution;
    }

    /**
     * @param resolution The resolution
     */
    @JsonProperty("resolution")
    public void setResolution(String resolution) {
        this.resolution = resolution;
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
