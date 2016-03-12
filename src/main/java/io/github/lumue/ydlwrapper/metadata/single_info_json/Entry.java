
package io.github.lumue.ydlwrapper.metadata.single_info_json;

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
    "upload_date",
    "creator",
    "height",
    "like_count",
    "duration",
    "id",
    "requested_formats",
    "view_count",
    "playlist",
    "title",
    "format",
    "ext",
    "playlist_index",
    "dislike_count",
    "average_rating",
    "abr",
    "uploader_url",
    "categories",
    "fps",
    "stretched_ratio",
    "age_limit",
    "annotations",
    "webpage_url_basename",
    "acodec",
    "display_id",
    "automatic_captions",
    "description",
    "tags",
    "requested_subtitles",
    "start_time",
    "playlist_id",
    "uploader",
    "format_id",
    "uploader_id",
    "subtitles",
    "playlist_title",
    "thumbnails",
    "license",
    "alt_title",
    "extractor_key",
    "vcodec",
    "thumbnail",
    "vbr",
    "is_live",
    "extractor",
    "end_time",
    "webpage_url",
    "formats",
    "resolution",
    "width",
    "n_entries"
})
public class Entry {

    @JsonProperty("upload_date")
    private String uploadDate;
    @JsonProperty("creator")
    private Object creator;
    @JsonProperty("height")
    private long height;
    @JsonProperty("like_count")
    private long likeCount;
    @JsonProperty("duration")
    private long duration;
    @JsonProperty("id")
    private String id;
    @JsonProperty("requested_formats")
    @Valid
    private List<RequestedFormat> requestedFormats = new ArrayList<RequestedFormat>();
    @JsonProperty("view_count")
    private long viewCount;
    @JsonProperty("playlist")
    private String playlist;
    @JsonProperty("title")
    private String title;
    @JsonProperty("format")
    private String format;
    @JsonProperty("ext")
    private String ext;
    @JsonProperty("playlist_index")
    private long playlistIndex;
    @JsonProperty("dislike_count")
    private long dislikeCount;
    @JsonProperty("average_rating")
    private float averageRating;
    @JsonProperty("abr")
    private long abr;
    @JsonProperty("uploader_url")
    private String uploaderUrl;
    @JsonProperty("categories")
    @Valid
    private List<String> categories = new ArrayList<String>();
    @JsonProperty("fps")
    private long fps;
    @JsonProperty("stretched_ratio")
    private Object stretchedRatio;
    @JsonProperty("age_limit")
    private long ageLimit;
    @JsonProperty("annotations")
    private Object annotations;
    @JsonProperty("webpage_url_basename")
    private String webpageUrlBasename;
    @JsonProperty("acodec")
    private String acodec;
    @JsonProperty("display_id")
    private String displayId;
    @JsonProperty("automatic_captions")
    @Valid
    private AutomaticCaptions automaticCaptions;
    @JsonProperty("description")
    private String description;
    @JsonProperty("tags")
    @Valid
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("requested_subtitles")
    private Object requestedSubtitles;
    @JsonProperty("start_time")
    private Object startTime;
    @JsonProperty("playlist_id")
    private String playlistId;
    @JsonProperty("uploader")
    private String uploader;
    @JsonProperty("format_id")
    private String formatId;
    @JsonProperty("uploader_id")
    private String uploaderId;
    @JsonProperty("subtitles")
    @Valid
    private Subtitles subtitles;
    @JsonProperty("playlist_title")
    private String playlistTitle;
    @JsonProperty("thumbnails")
    @Valid
    private List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
    @JsonProperty("license")
    private String license;
    @JsonProperty("alt_title")
    private Object altTitle;
    @JsonProperty("extractor_key")
    private String extractorKey;
    @JsonProperty("vcodec")
    private String vcodec;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("vbr")
    private Object vbr;
    @JsonProperty("is_live")
    private Object isLive;
    @JsonProperty("extractor")
    private String extractor;
    @JsonProperty("end_time")
    private Object endTime;
    @JsonProperty("webpage_url")
    private String webpageUrl;
    @JsonProperty("formats")
    @Valid
    private List<Format> formats = new ArrayList<Format>();
    @JsonProperty("resolution")
    private Object resolution;
    @JsonProperty("width")
    private long width;
    @JsonProperty("n_entries")
    private long nEntries;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Entry() {
    }

    /**
     * 
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
     * @param description
     * @param extractor
     * @param automaticCaptions
     * @param altTitle
     * @param webpageUrlBasename
     * @param vbr
     * @param requestedSubtitles
     * @param dislikeCount
     * @param width
     * @param stretchedRatio
     * @param fps
     * @param uploader
     * @param format
     * @param requestedFormats
     * @param thumbnail
     * @param formatId
     * @param nEntries
     * @param viewCount
     * @param webpageUrl
     * @param averageRating
     * @param likeCount
     * @param resolution
     * @param acodec
     * @param vcodec
     * @param creator
     * @param id
     * @param title
     * @param playlist
     * @param license
     * @param uploadDate
     * @param tags
     * @param subtitles
     * @param thumbnails
     * @param uploaderUrl
     * @param playlistTitle
     * @param duration
     * @param playlistId
     * @param ageLimit
     * @param categories
     * @param annotations
     * @param displayId
     */
    public Entry(String uploadDate, Object creator, long height, long likeCount, long duration, String id, List<RequestedFormat> requestedFormats, long viewCount, String playlist, String title, String format, String ext, long playlistIndex, long dislikeCount, float averageRating, long abr, String uploaderUrl, List<String> categories, long fps, Object stretchedRatio, long ageLimit, Object annotations, String webpageUrlBasename, String acodec, String displayId, AutomaticCaptions automaticCaptions, String description, List<String> tags, Object requestedSubtitles, Object startTime, String playlistId, String uploader, String formatId, String uploaderId, Subtitles subtitles, String playlistTitle, List<Thumbnail> thumbnails, String license, Object altTitle, String extractorKey, String vcodec, String thumbnail, Object vbr, Object isLive, String extractor, Object endTime, String webpageUrl, List<Format> formats, Object resolution, long width, long nEntries) {
        this.uploadDate = uploadDate;
        this.creator = creator;
        this.height = height;
        this.likeCount = likeCount;
        this.duration = duration;
        this.id = id;
        this.requestedFormats = requestedFormats;
        this.viewCount = viewCount;
        this.playlist = playlist;
        this.title = title;
        this.format = format;
        this.ext = ext;
        this.playlistIndex = playlistIndex;
        this.dislikeCount = dislikeCount;
        this.averageRating = averageRating;
        this.abr = abr;
        this.uploaderUrl = uploaderUrl;
        this.categories = categories;
        this.fps = fps;
        this.stretchedRatio = stretchedRatio;
        this.ageLimit = ageLimit;
        this.annotations = annotations;
        this.webpageUrlBasename = webpageUrlBasename;
        this.acodec = acodec;
        this.displayId = displayId;
        this.automaticCaptions = automaticCaptions;
        this.description = description;
        this.tags = tags;
        this.requestedSubtitles = requestedSubtitles;
        this.startTime = startTime;
        this.playlistId = playlistId;
        this.uploader = uploader;
        this.formatId = formatId;
        this.uploaderId = uploaderId;
        this.subtitles = subtitles;
        this.playlistTitle = playlistTitle;
        this.thumbnails = thumbnails;
        this.license = license;
        this.altTitle = altTitle;
        this.extractorKey = extractorKey;
        this.vcodec = vcodec;
        this.thumbnail = thumbnail;
        this.vbr = vbr;
        this.isLive = isLive;
        this.extractor = extractor;
        this.endTime = endTime;
        this.webpageUrl = webpageUrl;
        this.formats = formats;
        this.resolution = resolution;
        this.width = width;
        this.nEntries = nEntries;
    }

    /**
     * 
     * @return
     *     The uploadDate
     */
    @JsonProperty("upload_date")
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * 
     * @param uploadDate
     *     The upload_date
     */
    @JsonProperty("upload_date")
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Entry withUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }

    /**
     * 
     * @return
     *     The creator
     */
    @JsonProperty("creator")
    public Object getCreator() {
        return creator;
    }

    /**
     * 
     * @param creator
     *     The creator
     */
    @JsonProperty("creator")
    public void setCreator(Object creator) {
        this.creator = creator;
    }

    public Entry withCreator(Object creator) {
        this.creator = creator;
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

    public Entry withHeight(long height) {
        this.height = height;
        return this;
    }

    /**
     * 
     * @return
     *     The likeCount
     */
    @JsonProperty("like_count")
    public long getLikeCount() {
        return likeCount;
    }

    /**
     * 
     * @param likeCount
     *     The like_count
     */
    @JsonProperty("like_count")
    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public Entry withLikeCount(long likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    /**
     * 
     * @return
     *     The duration
     */
    @JsonProperty("duration")
    public long getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    @JsonProperty("duration")
    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Entry withDuration(long duration) {
        this.duration = duration;
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

    public Entry withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The requestedFormats
     */
    @JsonProperty("requested_formats")
    public List<RequestedFormat> getRequestedFormats() {
        return requestedFormats;
    }

    /**
     * 
     * @param requestedFormats
     *     The requested_formats
     */
    @JsonProperty("requested_formats")
    public void setRequestedFormats(List<RequestedFormat> requestedFormats) {
        this.requestedFormats = requestedFormats;
    }

    public Entry withRequestedFormats(List<RequestedFormat> requestedFormats) {
        this.requestedFormats = requestedFormats;
        return this;
    }

    /**
     * 
     * @return
     *     The viewCount
     */
    @JsonProperty("view_count")
    public long getViewCount() {
        return viewCount;
    }

    /**
     * 
     * @param viewCount
     *     The view_count
     */
    @JsonProperty("view_count")
    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public Entry withViewCount(long viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    /**
     * 
     * @return
     *     The playlist
     */
    @JsonProperty("playlist")
    public String getPlaylist() {
        return playlist;
    }

    /**
     * 
     * @param playlist
     *     The playlist
     */
    @JsonProperty("playlist")
    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public Entry withPlaylist(String playlist) {
        this.playlist = playlist;
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

    public Entry withTitle(String title) {
        this.title = title;
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

    public Entry withFormat(String format) {
        this.format = format;
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

    public Entry withExt(String ext) {
        this.ext = ext;
        return this;
    }

    /**
     * 
     * @return
     *     The playlistIndex
     */
    @JsonProperty("playlist_index")
    public long getPlaylistIndex() {
        return playlistIndex;
    }

    /**
     * 
     * @param playlistIndex
     *     The playlist_index
     */
    @JsonProperty("playlist_index")
    public void setPlaylistIndex(long playlistIndex) {
        this.playlistIndex = playlistIndex;
    }

    public Entry withPlaylistIndex(long playlistIndex) {
        this.playlistIndex = playlistIndex;
        return this;
    }

    /**
     * 
     * @return
     *     The dislikeCount
     */
    @JsonProperty("dislike_count")
    public long getDislikeCount() {
        return dislikeCount;
    }

    /**
     * 
     * @param dislikeCount
     *     The dislike_count
     */
    @JsonProperty("dislike_count")
    public void setDislikeCount(long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Entry withDislikeCount(long dislikeCount) {
        this.dislikeCount = dislikeCount;
        return this;
    }

    /**
     * 
     * @return
     *     The averageRating
     */
    @JsonProperty("average_rating")
    public float getAverageRating() {
        return averageRating;
    }

    /**
     * 
     * @param averageRating
     *     The average_rating
     */
    @JsonProperty("average_rating")
    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public Entry withAverageRating(float averageRating) {
        this.averageRating = averageRating;
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

    public Entry withAbr(long abr) {
        this.abr = abr;
        return this;
    }

    /**
     * 
     * @return
     *     The uploaderUrl
     */
    @JsonProperty("uploader_url")
    public String getUploaderUrl() {
        return uploaderUrl;
    }

    /**
     * 
     * @param uploaderUrl
     *     The uploader_url
     */
    @JsonProperty("uploader_url")
    public void setUploaderUrl(String uploaderUrl) {
        this.uploaderUrl = uploaderUrl;
    }

    public Entry withUploaderUrl(String uploaderUrl) {
        this.uploaderUrl = uploaderUrl;
        return this;
    }

    /**
     * 
     * @return
     *     The categories
     */
    @JsonProperty("categories")
    public List<String> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    @JsonProperty("categories")
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Entry withCategories(List<String> categories) {
        this.categories = categories;
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

    public Entry withFps(long fps) {
        this.fps = fps;
        return this;
    }

    /**
     * 
     * @return
     *     The stretchedRatio
     */
    @JsonProperty("stretched_ratio")
    public Object getStretchedRatio() {
        return stretchedRatio;
    }

    /**
     * 
     * @param stretchedRatio
     *     The stretched_ratio
     */
    @JsonProperty("stretched_ratio")
    public void setStretchedRatio(Object stretchedRatio) {
        this.stretchedRatio = stretchedRatio;
    }

    public Entry withStretchedRatio(Object stretchedRatio) {
        this.stretchedRatio = stretchedRatio;
        return this;
    }

    /**
     * 
     * @return
     *     The ageLimit
     */
    @JsonProperty("age_limit")
    public long getAgeLimit() {
        return ageLimit;
    }

    /**
     * 
     * @param ageLimit
     *     The age_limit
     */
    @JsonProperty("age_limit")
    public void setAgeLimit(long ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Entry withAgeLimit(long ageLimit) {
        this.ageLimit = ageLimit;
        return this;
    }

    /**
     * 
     * @return
     *     The annotations
     */
    @JsonProperty("annotations")
    public Object getAnnotations() {
        return annotations;
    }

    /**
     * 
     * @param annotations
     *     The annotations
     */
    @JsonProperty("annotations")
    public void setAnnotations(Object annotations) {
        this.annotations = annotations;
    }

    public Entry withAnnotations(Object annotations) {
        this.annotations = annotations;
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

    public Entry withWebpageUrlBasename(String webpageUrlBasename) {
        this.webpageUrlBasename = webpageUrlBasename;
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

    public Entry withAcodec(String acodec) {
        this.acodec = acodec;
        return this;
    }

    /**
     * 
     * @return
     *     The displayId
     */
    @JsonProperty("display_id")
    public String getDisplayId() {
        return displayId;
    }

    /**
     * 
     * @param displayId
     *     The display_id
     */
    @JsonProperty("display_id")
    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public Entry withDisplayId(String displayId) {
        this.displayId = displayId;
        return this;
    }

    /**
     * 
     * @return
     *     The automaticCaptions
     */
    @JsonProperty("automatic_captions")
    public AutomaticCaptions getAutomaticCaptions() {
        return automaticCaptions;
    }

    /**
     * 
     * @param automaticCaptions
     *     The automatic_captions
     */
    @JsonProperty("automatic_captions")
    public void setAutomaticCaptions(AutomaticCaptions automaticCaptions) {
        this.automaticCaptions = automaticCaptions;
    }

    public Entry withAutomaticCaptions(AutomaticCaptions automaticCaptions) {
        this.automaticCaptions = automaticCaptions;
        return this;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Entry withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 
     * @return
     *     The tags
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Entry withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * 
     * @return
     *     The requestedSubtitles
     */
    @JsonProperty("requested_subtitles")
    public Object getRequestedSubtitles() {
        return requestedSubtitles;
    }

    /**
     * 
     * @param requestedSubtitles
     *     The requested_subtitles
     */
    @JsonProperty("requested_subtitles")
    public void setRequestedSubtitles(Object requestedSubtitles) {
        this.requestedSubtitles = requestedSubtitles;
    }

    public Entry withRequestedSubtitles(Object requestedSubtitles) {
        this.requestedSubtitles = requestedSubtitles;
        return this;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    @JsonProperty("start_time")
    public Object getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The start_time
     */
    @JsonProperty("start_time")
    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    public Entry withStartTime(Object startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * 
     * @return
     *     The playlistId
     */
    @JsonProperty("playlist_id")
    public String getPlaylistId() {
        return playlistId;
    }

    /**
     * 
     * @param playlistId
     *     The playlist_id
     */
    @JsonProperty("playlist_id")
    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public Entry withPlaylistId(String playlistId) {
        this.playlistId = playlistId;
        return this;
    }

    /**
     * 
     * @return
     *     The uploader
     */
    @JsonProperty("uploader")
    public String getUploader() {
        return uploader;
    }

    /**
     * 
     * @param uploader
     *     The uploader
     */
    @JsonProperty("uploader")
    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Entry withUploader(String uploader) {
        this.uploader = uploader;
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

    public Entry withFormatId(String formatId) {
        this.formatId = formatId;
        return this;
    }

    /**
     * 
     * @return
     *     The uploaderId
     */
    @JsonProperty("uploader_id")
    public String getUploaderId() {
        return uploaderId;
    }

    /**
     * 
     * @param uploaderId
     *     The uploader_id
     */
    @JsonProperty("uploader_id")
    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public Entry withUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
        return this;
    }

    /**
     * 
     * @return
     *     The subtitles
     */
    @JsonProperty("subtitles")
    public Subtitles getSubtitles() {
        return subtitles;
    }

    /**
     * 
     * @param subtitles
     *     The subtitles
     */
    @JsonProperty("subtitles")
    public void setSubtitles(Subtitles subtitles) {
        this.subtitles = subtitles;
    }

    public Entry withSubtitles(Subtitles subtitles) {
        this.subtitles = subtitles;
        return this;
    }

    /**
     * 
     * @return
     *     The playlistTitle
     */
    @JsonProperty("playlist_title")
    public String getPlaylistTitle() {
        return playlistTitle;
    }

    /**
     * 
     * @param playlistTitle
     *     The playlist_title
     */
    @JsonProperty("playlist_title")
    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public Entry withPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
        return this;
    }

    /**
     * 
     * @return
     *     The thumbnails
     */
    @JsonProperty("thumbnails")
    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    /**
     * 
     * @param thumbnails
     *     The thumbnails
     */
    @JsonProperty("thumbnails")
    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Entry withThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
        return this;
    }

    /**
     * 
     * @return
     *     The license
     */
    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    /**
     * 
     * @param license
     *     The license
     */
    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    public Entry withLicense(String license) {
        this.license = license;
        return this;
    }

    /**
     * 
     * @return
     *     The altTitle
     */
    @JsonProperty("alt_title")
    public Object getAltTitle() {
        return altTitle;
    }

    /**
     * 
     * @param altTitle
     *     The alt_title
     */
    @JsonProperty("alt_title")
    public void setAltTitle(Object altTitle) {
        this.altTitle = altTitle;
    }

    public Entry withAltTitle(Object altTitle) {
        this.altTitle = altTitle;
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

    public Entry withExtractorKey(String extractorKey) {
        this.extractorKey = extractorKey;
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

    public Entry withVcodec(String vcodec) {
        this.vcodec = vcodec;
        return this;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Entry withThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    /**
     * 
     * @return
     *     The vbr
     */
    @JsonProperty("vbr")
    public Object getVbr() {
        return vbr;
    }

    /**
     * 
     * @param vbr
     *     The vbr
     */
    @JsonProperty("vbr")
    public void setVbr(Object vbr) {
        this.vbr = vbr;
    }

    public Entry withVbr(Object vbr) {
        this.vbr = vbr;
        return this;
    }

    /**
     * 
     * @return
     *     The isLive
     */
    @JsonProperty("is_live")
    public Object getIsLive() {
        return isLive;
    }

    /**
     * 
     * @param isLive
     *     The is_live
     */
    @JsonProperty("is_live")
    public void setIsLive(Object isLive) {
        this.isLive = isLive;
    }

    public Entry withIsLive(Object isLive) {
        this.isLive = isLive;
        return this;
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

    public Entry withExtractor(String extractor) {
        this.extractor = extractor;
        return this;
    }

    /**
     * 
     * @return
     *     The endTime
     */
    @JsonProperty("end_time")
    public Object getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The end_time
     */
    @JsonProperty("end_time")
    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public Entry withEndTime(Object endTime) {
        this.endTime = endTime;
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

    public Entry withWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
        return this;
    }

    /**
     * 
     * @return
     *     The formats
     */
    @JsonProperty("formats")
    public List<Format> getFormats() {
        return formats;
    }

    /**
     * 
     * @param formats
     *     The formats
     */
    @JsonProperty("formats")
    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    public Entry withFormats(List<Format> formats) {
        this.formats = formats;
        return this;
    }

    /**
     * 
     * @return
     *     The resolution
     */
    @JsonProperty("resolution")
    public Object getResolution() {
        return resolution;
    }

    /**
     * 
     * @param resolution
     *     The resolution
     */
    @JsonProperty("resolution")
    public void setResolution(Object resolution) {
        this.resolution = resolution;
    }

    public Entry withResolution(Object resolution) {
        this.resolution = resolution;
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

    public Entry withWidth(long width) {
        this.width = width;
        return this;
    }

    /**
     * 
     * @return
     *     The nEntries
     */
    @JsonProperty("n_entries")
    public long getNEntries() {
        return nEntries;
    }

    /**
     * 
     * @param nEntries
     *     The n_entries
     */
    @JsonProperty("n_entries")
    public void setNEntries(long nEntries) {
        this.nEntries = nEntries;
    }

    public Entry withNEntries(long nEntries) {
        this.nEntries = nEntries;
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

    public Entry withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(uploadDate).append(creator).append(height).append(likeCount).append(duration).append(id).append(requestedFormats).append(viewCount).append(playlist).append(title).append(format).append(ext).append(playlistIndex).append(dislikeCount).append(averageRating).append(abr).append(uploaderUrl).append(categories).append(fps).append(stretchedRatio).append(ageLimit).append(annotations).append(webpageUrlBasename).append(acodec).append(displayId).append(automaticCaptions).append(description).append(tags).append(requestedSubtitles).append(startTime).append(playlistId).append(uploader).append(formatId).append(uploaderId).append(subtitles).append(playlistTitle).append(thumbnails).append(license).append(altTitle).append(extractorKey).append(vcodec).append(thumbnail).append(vbr).append(isLive).append(extractor).append(endTime).append(webpageUrl).append(formats).append(resolution).append(width).append(nEntries).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Entry) == false) {
            return false;
        }
        Entry rhs = ((Entry) other);
        return new EqualsBuilder().append(uploadDate, rhs.uploadDate).append(creator, rhs.creator).append(height, rhs.height).append(likeCount, rhs.likeCount).append(duration, rhs.duration).append(id, rhs.id).append(requestedFormats, rhs.requestedFormats).append(viewCount, rhs.viewCount).append(playlist, rhs.playlist).append(title, rhs.title).append(format, rhs.format).append(ext, rhs.ext).append(playlistIndex, rhs.playlistIndex).append(dislikeCount, rhs.dislikeCount).append(averageRating, rhs.averageRating).append(abr, rhs.abr).append(uploaderUrl, rhs.uploaderUrl).append(categories, rhs.categories).append(fps, rhs.fps).append(stretchedRatio, rhs.stretchedRatio).append(ageLimit, rhs.ageLimit).append(annotations, rhs.annotations).append(webpageUrlBasename, rhs.webpageUrlBasename).append(acodec, rhs.acodec).append(displayId, rhs.displayId).append(automaticCaptions, rhs.automaticCaptions).append(description, rhs.description).append(tags, rhs.tags).append(requestedSubtitles, rhs.requestedSubtitles).append(startTime, rhs.startTime).append(playlistId, rhs.playlistId).append(uploader, rhs.uploader).append(formatId, rhs.formatId).append(uploaderId, rhs.uploaderId).append(subtitles, rhs.subtitles).append(playlistTitle, rhs.playlistTitle).append(thumbnails, rhs.thumbnails).append(license, rhs.license).append(altTitle, rhs.altTitle).append(extractorKey, rhs.extractorKey).append(vcodec, rhs.vcodec).append(thumbnail, rhs.thumbnail).append(vbr, rhs.vbr).append(isLive, rhs.isLive).append(extractor, rhs.extractor).append(endTime, rhs.endTime).append(webpageUrl, rhs.webpageUrl).append(formats, rhs.formats).append(resolution, rhs.resolution).append(width, rhs.width).append(nEntries, rhs.nEntries).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
