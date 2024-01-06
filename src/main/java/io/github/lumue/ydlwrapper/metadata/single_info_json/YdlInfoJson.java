package io.github.lumue.ydlwrapper.metadata.single_info_json;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "extractor",
        "_type",
        "title",
        "extractor_key",
        "webpage_url",
        "entries",
        "id",
        "requested_formats",
        "webpage_url_basename",
        "upload_date",
        "protocol",
        "creator",
        "Formatnote",
        "height",
        "like_count",
        "duration",
        "player_url",
        "view_count",
        "playlist",
        "format",
        "ext",
        "playlist_index",
        "dislike_count",
        "average_rating",
        "abr",
        "uploader_url",
        "categories",
        "age_limit",
        "annotations",
        "acodec",
        "display_id",
        "automatic_captions",
        "description",
        "tags",
        "requested_subtitles",
        "start_time",
        "uploader",
        "Formatid",
        "uploader_id",
        "subtitles",
        "thumbnails",
        "license",
        "alt_title",
        "url",
        "vcodec",
        "http_headers",
        "thumbnail",
        "is_live",
        "end_time",
        "formats",
        "resolution",
        "width"
})
public class YdlInfoJson {

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
    private List<Entry> entries = new ArrayList<Entry>();
    @JsonProperty("id")
    private String id;
    @JsonProperty("requested_formats")
    private List<RequestedFormat> requestedFormats = new ArrayList<RequestedFormat>();
    @JsonProperty("webpage_url_basename")
    private String webpageUrlBasename;
    @JsonProperty("upload_date")
    private String uploadDate;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("creator")
    private Object creator;
    @JsonProperty("Formatnote")
    private String formatNote;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("like_count")
    private Integer likeCount;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("player_url")
    private Object playerUrl;
    @JsonProperty("view_count")
    private Integer viewCount;
    @JsonProperty("playlist")
    private Object playlist;
    @JsonProperty("format")
    private String format;
    @JsonProperty("ext")
    private String ext;
    @JsonProperty("playlist_index")
    private Object playlistIndex;
    @JsonProperty("dislike_count")
    private Integer dislikeCount;
    @JsonProperty("average_rating")
    private Float averageRating;
    @JsonProperty("abr")
    private Integer abr;
    @JsonProperty("uploader_url")
    private String uploaderUrl;
    @JsonProperty("categories")
    private List<String> categories = new ArrayList<String>();
    @JsonProperty("age_limit")
    private Integer ageLimit;
    @JsonProperty("annotations")
    private Object annotations;
    @JsonProperty("acodec")
    private String acodec;
    @JsonProperty("display_id")
    private String displayId;
    @JsonProperty("automatic_captions")
    private AutomaticCaptions automaticCaptions;
    @JsonProperty("description")
    private String description;
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("requested_subtitles")
    private Object requestedSubtitles;
    @JsonProperty("start_time")
    private Object startTime;
    @JsonProperty("uploader")
    private String uploader;
    @JsonProperty("format_id")
    private String formatId;
    @JsonProperty("uploader_id")
    private String uploaderId;
    @JsonProperty("subtitles")
    private Subtitles subtitles;
    @JsonProperty("thumbnails")
    private List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
    @JsonProperty("license")
    private String license;
    @JsonProperty("alt_title")
    private Object altTitle;
    @JsonProperty("url")
    private String url;
    @JsonProperty("vcodec")
    private String vcodec;
    @JsonProperty("http_headers")
    private HttpHeaders httpHeaders;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("is_live")
    private Object isLive;
    @JsonProperty("end_time")
    private Object endTime;
    @JsonProperty("formats")
    private List<Format> formats = new ArrayList<Format>();
    @JsonProperty("resolution")
    private String resolution;
    @JsonProperty("width")
    private Integer width;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public YdlInfoJson() {
    }

    /**
     * @param isLive
     * @param uploaderId
     * @param endTime
     * @param extractorKey
     * @param ext
     * @param formats
     * @param abr
     * @param startTime
     * @param playlistIndex
     * @param height
     * @param extractor
     * @param description
     * @param automaticCaptions
     * @param altTitle
     * @param webpageUrlBasename
     * @param httpHeaders
     * @param requestedSubtitles
     * @param dislikeCount
     * @param width
     * @param uploader
     * @param format
     * @param requestedFormats
     * @param url
     * @param formatNote
     * @param thumbnail
     * @param formatId
     * @param viewCount
     * @param webpageUrl
     * @param averageRating
     * @param likeCount
     * @param Type
     * @param resolution
     * @param acodec
     * @param vcodec
     * @param creator
     * @param id
     * @param title
     * @param entries
     * @param playlist
     * @param license
     * @param uploadDate
     * @param playerUrl
     * @param tags
     * @param subtitles
     * @param protocol
     * @param thumbnails
     * @param uploaderUrl
     * @param duration
     * @param ageLimit
     * @param categories
     * @param annotations
     * @param displayId
     */
    public YdlInfoJson(String extractor, String Type, String title, String extractorKey, String webpageUrl, List<Entry> entries, String id, List<RequestedFormat> requestedFormats, String webpageUrlBasename, String uploadDate, String protocol, Object creator, String formatNote, Integer height, Integer likeCount, Integer duration, Object playerUrl, Integer viewCount, Object playlist, String format, String ext, Object playlistIndex, Integer dislikeCount, Float averageRating, Integer abr, String uploaderUrl, List<String> categories, Integer ageLimit, Object annotations, String acodec, String displayId, AutomaticCaptions automaticCaptions, String description, List<String> tags, Object requestedSubtitles, Object startTime, String uploader, String formatId, String uploaderId, Subtitles subtitles, List<Thumbnail> thumbnails, String license, Object altTitle, String url, String vcodec, HttpHeaders httpHeaders, String thumbnail, Object isLive, Object endTime, List<Format> formats, String resolution, Integer width) {
        this.extractor = extractor;
        this.Type = Type;
        this.title = title;
        this.extractorKey = extractorKey;
        this.webpageUrl = webpageUrl;
        this.entries = entries;
        this.id = id;
        this.requestedFormats = requestedFormats;
        this.webpageUrlBasename = webpageUrlBasename;
        this.uploadDate = uploadDate;
        this.protocol = protocol;
        this.creator = creator;
        this.formatNote = formatNote;
        this.height = height;
        this.likeCount = likeCount;
        this.duration = duration;
        this.playerUrl = playerUrl;
        this.viewCount = viewCount;
        this.playlist = playlist;
        this.format = format;
        this.ext = ext;
        this.playlistIndex = playlistIndex;
        this.dislikeCount = dislikeCount;
        this.averageRating = averageRating;
        this.abr = abr;
        this.uploaderUrl = uploaderUrl;
        this.categories = categories;
        this.ageLimit = ageLimit;
        this.annotations = annotations;
        this.acodec = acodec;
        this.displayId = displayId;
        this.automaticCaptions = automaticCaptions;
        this.description = description;
        this.tags = tags;
        this.requestedSubtitles = requestedSubtitles;
        this.startTime = startTime;
        this.uploader = uploader;
        this.formatId = formatId;
        this.uploaderId = uploaderId;
        this.subtitles = subtitles;
        this.thumbnails = thumbnails;
        this.license = license;
        this.altTitle = altTitle;
        this.url = url;
        this.vcodec = vcodec;
        this.httpHeaders = httpHeaders;
        this.thumbnail = thumbnail;
        this.isLive = isLive;
        this.endTime = endTime;
        this.formats = formats;
        this.resolution = resolution;
        this.width = width;
    }

    /**
     * @return The extractor
     */
    @JsonProperty("extractor")
    public String getExtractor() {
        return extractor;
    }

    /**
     * @param extractor The extractor
     */
    @JsonProperty("extractor")
    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }

    /**
     * @return The Type
     */
    @JsonProperty("_type")
    public String getType() {
        return Type;
    }

    /**
     * @param Type The _type
     */
    @JsonProperty("_type")
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The extractorKey
     */
    @JsonProperty("extractor_key")
    public String getExtractorKey() {
        return extractorKey;
    }

    /**
     * @param extractorKey The extractor_key
     */
    @JsonProperty("extractor_key")
    public void setExtractorKey(String extractorKey) {
        this.extractorKey = extractorKey;
    }

    /**
     * @return The webpageUrl
     */
    @JsonProperty("webpage_url")
    public String getWebpageUrl() {
        return webpageUrl;
    }

    /**
     * @param webpageUrl The webpage_url
     */
    @JsonProperty("webpage_url")
    public void setWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
    }

    /**
     * @return The entries
     */
    @JsonProperty("entries")
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * @param entries The entries
     */
    @JsonProperty("entries")
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The requestedFormats
     */
    @JsonProperty("requested_formats")
    public List<RequestedFormat> getRequestedFormats() {
        return requestedFormats;
    }

    /**
     * @param requestedFormats The requested_formats
     */
    @JsonProperty("requested_formats")
    public void setRequestedFormats(List<RequestedFormat> requestedFormats) {
        this.requestedFormats = requestedFormats;
    }

    /**
     * @return The webpageUrlBasename
     */
    @JsonProperty("webpage_url_basename")
    public String getWebpageUrlBasename() {
        return webpageUrlBasename;
    }

    /**
     * @param webpageUrlBasename The webpage_url_basename
     */
    @JsonProperty("webpage_url_basename")
    public void setWebpageUrlBasename(String webpageUrlBasename) {
        this.webpageUrlBasename = webpageUrlBasename;
    }

    /**
     * @return The uploadDate
     */
    @JsonProperty("upload_date")
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * @param uploadDate The upload_date
     */
    @JsonProperty("upload_date")
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
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
     * @return The creator
     */
    @JsonProperty("creator")
    public Object getCreator() {
        return creator;
    }

    /**
     * @param creator The creator
     */
    @JsonProperty("creator")
    public void setCreator(Object creator) {
        this.creator = creator;
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
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return The likeCount
     */
    @JsonProperty("like_count")
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * @param likeCount The like_count
     */
    @JsonProperty("like_count")
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return The duration
     */
    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    /**
     * @param duration The duration
     */
    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
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
     * @return The viewCount
     */
    @JsonProperty("view_count")
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * @param viewCount The view_count
     */
    @JsonProperty("view_count")
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * @return The playlist
     */
    @JsonProperty("playlist")
    public Object getPlaylist() {
        return playlist;
    }

    /**
     * @param playlist The playlist
     */
    @JsonProperty("playlist")
    public void setPlaylist(Object playlist) {
        this.playlist = playlist;
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
     * @return The playlistIndex
     */
    @JsonProperty("playlist_index")
    public Object getPlaylistIndex() {
        return playlistIndex;
    }

    /**
     * @param playlistIndex The playlist_index
     */
    @JsonProperty("playlist_index")
    public void setPlaylistIndex(Object playlistIndex) {
        this.playlistIndex = playlistIndex;
    }

    /**
     * @return The dislikeCount
     */
    @JsonProperty("dislike_count")
    public Integer getDislikeCount() {
        return dislikeCount;
    }

    /**
     * @param dislikeCount The dislike_count
     */
    @JsonProperty("dislike_count")
    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    /**
     * @return The averageRating
     */
    @JsonProperty("average_rating")
    public Float getAverageRating() {
        return averageRating;
    }

    /**
     * @param averageRating The average_rating
     */
    @JsonProperty("average_rating")
    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * @return The abr
     */
    @JsonProperty("abr")
    public Integer getAbr() {
        return abr;
    }

    /**
     * @param abr The abr
     */
    @JsonProperty("abr")
    public void setAbr(Integer abr) {
        this.abr = abr;
    }

    /**
     * @return The uploaderUrl
     */
    @JsonProperty("uploader_url")
    public String getUploaderUrl() {
        return uploaderUrl;
    }

    /**
     * @param uploaderUrl The uploader_url
     */
    @JsonProperty("uploader_url")
    public void setUploaderUrl(String uploaderUrl) {
        this.uploaderUrl = uploaderUrl;
    }

    /**
     * @return The categories
     */
    @JsonProperty("categories")
    public List<String> getCategories() {
        return categories;
    }

    /**
     * @param categories The categories
     */
    @JsonProperty("categories")
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * @return The ageLimit
     */
    @JsonProperty("age_limit")
    public Integer getAgeLimit() {
        return ageLimit;
    }

    /**
     * @param ageLimit The age_limit
     */
    @JsonProperty("age_limit")
    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    /**
     * @return The annotations
     */
    @JsonProperty("annotations")
    public Object getAnnotations() {
        return annotations;
    }

    /**
     * @param annotations The annotations
     */
    @JsonProperty("annotations")
    public void setAnnotations(Object annotations) {
        this.annotations = annotations;
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
     * @return The displayId
     */
    @JsonProperty("display_id")
    public String getDisplayId() {
        return displayId;
    }

    /**
     * @param displayId The display_id
     */
    @JsonProperty("display_id")
    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    /**
     * @return The automaticCaptions
     */
    @JsonProperty("automatic_captions")
    public AutomaticCaptions getAutomaticCaptions() {
        return automaticCaptions;
    }

    /**
     * @param automaticCaptions The automatic_captions
     */
    @JsonProperty("automatic_captions")
    public void setAutomaticCaptions(AutomaticCaptions automaticCaptions) {
        this.automaticCaptions = automaticCaptions;
    }

    /**
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The tags
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags The tags
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @return The requestedSubtitles
     */
    @JsonProperty("requested_subtitles")
    public Object getRequestedSubtitles() {
        return requestedSubtitles;
    }

    /**
     * @param requestedSubtitles The requested_subtitles
     */
    @JsonProperty("requested_subtitles")
    public void setRequestedSubtitles(Object requestedSubtitles) {
        this.requestedSubtitles = requestedSubtitles;
    }

    /**
     * @return The startTime
     */
    @JsonProperty("start_time")
    public Object getStartTime() {
        return startTime;
    }

    /**
     * @param startTime The start_time
     */
    @JsonProperty("start_time")
    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    /**
     * @return The uploader
     */
    @JsonProperty("uploader")
    public String getUploader() {
        return uploader;
    }

    /**
     * @param uploader The uploader
     */
    @JsonProperty("uploader")
    public void setUploader(String uploader) {
        this.uploader = uploader;
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
     * @return The uploaderId
     */
    @JsonProperty("uploader_id")
    public String getUploaderId() {
        return uploaderId;
    }

    /**
     * @param uploaderId The uploader_id
     */
    @JsonProperty("uploader_id")
    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    /**
     * @return The subtitles
     */
    @JsonProperty("subtitles")
    public Subtitles getSubtitles() {
        return subtitles;
    }

    /**
     * @param subtitles The subtitles
     */
    @JsonProperty("subtitles")
    public void setSubtitles(Subtitles subtitles) {
        this.subtitles = subtitles;
    }

    /**
     * @return The thumbnails
     */
    @JsonProperty("thumbnails")
    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    /**
     * @param thumbnails The thumbnails
     */
    @JsonProperty("thumbnails")
    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * @return The license
     */
    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    /**
     * @param license The license
     */
    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * @return The altTitle
     */
    @JsonProperty("alt_title")
    public Object getAltTitle() {
        return altTitle;
    }

    /**
     * @param altTitle The alt_title
     */
    @JsonProperty("alt_title")
    public void setAltTitle(Object altTitle) {
        this.altTitle = altTitle;
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
     * @return The thumbnail
     */
    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail The thumbnail
     */
    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return The isLive
     */
    @JsonProperty("is_live")
    public Object getIsLive() {
        return isLive;
    }

    /**
     * @param isLive The is_live
     */
    @JsonProperty("is_live")
    public void setIsLive(Object isLive) {
        this.isLive = isLive;
    }

    /**
     * @return The endTime
     */
    @JsonProperty("end_time")
    public Object getEndTime() {
        return endTime;
    }

    /**
     * @param endTime The end_time
     */
    @JsonProperty("end_time")
    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    /**
     * @return The formats
     */
    @JsonProperty("formats")
    public List<Format> getFormats() {
        return formats;
    }

    /**
     * @param formats The formats
     */
    @JsonProperty("formats")
    public void setFormats(List<Format> formats) {
        this.formats = formats;
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

    /**
     * @return The width
     */
    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
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
        return new HashCodeBuilder().append(extractor).append(Type).append(title).append(extractorKey).append(webpageUrl).append(entries).append(id).append(requestedFormats).append(webpageUrlBasename).append(uploadDate).append(protocol).append(creator).append(formatNote).append(height).append(likeCount).append(duration).append(playerUrl).append(viewCount).append(playlist).append(format).append(ext).append(playlistIndex).append(dislikeCount).append(averageRating).append(abr).append(uploaderUrl).append(categories).append(ageLimit).append(annotations).append(acodec).append(displayId).append(automaticCaptions).append(description).append(tags).append(requestedSubtitles).append(startTime).append(uploader).append(formatId).append(uploaderId).append(subtitles).append(thumbnails).append(license).append(altTitle).append(url).append(vcodec).append(httpHeaders).append(thumbnail).append(isLive).append(endTime).append(formats).append(resolution).append(width).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof YdlInfoJson) == false) {
            return false;
        }
        YdlInfoJson rhs = ((YdlInfoJson) other);
        return new EqualsBuilder().append(extractor, rhs.extractor).append(Type, rhs.Type).append(title, rhs.title).append(extractorKey, rhs.extractorKey).append(webpageUrl, rhs.webpageUrl).append(entries, rhs.entries).append(id, rhs.id).append(requestedFormats, rhs.requestedFormats).append(webpageUrlBasename, rhs.webpageUrlBasename).append(uploadDate, rhs.uploadDate).append(protocol, rhs.protocol).append(creator, rhs.creator).append(formatNote, rhs.formatNote).append(height, rhs.height).append(likeCount, rhs.likeCount).append(duration, rhs.duration).append(playerUrl, rhs.playerUrl).append(viewCount, rhs.viewCount).append(playlist, rhs.playlist).append(format, rhs.format).append(ext, rhs.ext).append(playlistIndex, rhs.playlistIndex).append(dislikeCount, rhs.dislikeCount).append(averageRating, rhs.averageRating).append(abr, rhs.abr).append(uploaderUrl, rhs.uploaderUrl).append(categories, rhs.categories).append(ageLimit, rhs.ageLimit).append(annotations, rhs.annotations).append(acodec, rhs.acodec).append(displayId, rhs.displayId).append(automaticCaptions, rhs.automaticCaptions).append(description, rhs.description).append(tags, rhs.tags).append(requestedSubtitles, rhs.requestedSubtitles).append(startTime, rhs.startTime).append(uploader, rhs.uploader).append(formatId, rhs.formatId).append(uploaderId, rhs.uploaderId).append(subtitles, rhs.subtitles).append(thumbnails, rhs.thumbnails).append(license, rhs.license).append(altTitle, rhs.altTitle).append(url, rhs.url).append(vcodec, rhs.vcodec).append(httpHeaders, rhs.httpHeaders).append(thumbnail, rhs.thumbnail).append(isLive, rhs.isLive).append(endTime, rhs.endTime).append(formats, rhs.formats).append(resolution, rhs.resolution).append(width, rhs.width).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
