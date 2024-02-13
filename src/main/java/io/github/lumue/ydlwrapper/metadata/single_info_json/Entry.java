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
        "Formatid",
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
    private String height;
    @JsonProperty("like_count")
    private String likeCount;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("id")
    private String id;
    @JsonProperty("requested_formats")
    private List<RequestedFormat> requestedFormats = new ArrayList<RequestedFormat>();
    @JsonProperty("view_count")
    private String viewCount;
    @JsonProperty("playlist")
    private String playlist;
    @JsonProperty("title")
    private String title;
    @JsonProperty("format")
    private String format;
    @JsonProperty("ext")
    private String ext;
    @JsonProperty("playlist_index")
    private String playlistIndex;
    @JsonProperty("dislike_count")
    private String dislikeCount;
    @JsonProperty("average_rating")
    private String averageRating;
    @JsonProperty("abr")
    private String abr;
    @JsonProperty("uploader_url")
    private String uploaderUrl;
    @JsonProperty("categories")
    private List<String> categories = new ArrayList<String>();
    @JsonProperty("fps")
    private String fps;
    @JsonProperty("stretched_ratio")
    private Object stretchedRatio;
    @JsonProperty("age_limit")
    private String ageLimit;
    @JsonProperty("annotations")
    private Object annotations;
    @JsonProperty("webpage_url_basename")
    private String webpageUrlBasename;
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
    @JsonProperty("playlist_id")
    private String playlistId;
    @JsonProperty("uploader")
    private String uploader;
    @JsonProperty("format_id")
    private String formatId;
    @JsonProperty("uploader_id")
    private String uploaderId;
    @JsonProperty("subtitles")
    private Subtitles subtitles;
    @JsonProperty("playlist_title")
    private String playlistTitle;
    @JsonProperty("thumbnails")
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
    private List<Format> formats = new ArrayList<Format>();
    @JsonProperty("resolution")
    private Object resolution;
    @JsonProperty("width")
    private String width;
    @JsonProperty("n_entries")
    private String nEntries;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Entry() {
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
    public Entry(String uploadDate, Object creator, String height, String likeCount, String duration, String id, List<RequestedFormat> requestedFormats, String viewCount, String playlist, String title, String format, String ext, String playlistIndex, String dislikeCount, String averageRating, String abr, String uploaderUrl, List<String> categories, String fps, Object stretchedRatio, String ageLimit, Object annotations, String webpageUrlBasename, String acodec, String displayId, AutomaticCaptions automaticCaptions, String description, List<String> tags, Object requestedSubtitles, Object startTime, String playlistId, String uploader, String formatId, String uploaderId, Subtitles subtitles, String playlistTitle, List<Thumbnail> thumbnails, String license, Object altTitle, String extractorKey, String vcodec, String thumbnail, Object vbr, Object isLive, String extractor, Object endTime, String webpageUrl, List<Format> formats, Object resolution, String width, String nEntries) {
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
     * @return The likeCount
     */
    @JsonProperty("like_count")
    public String getLikeCount() {
        return likeCount;
    }

    /**
     * @param likeCount The like_count
     */
    @JsonProperty("like_count")
    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return The duration
     */
    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration The duration
     */
    @JsonProperty("duration")
    public void setDuration(String duration) {
        this.duration = duration;
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
     * @return The viewCount
     */
    @JsonProperty("view_count")
    public String getViewCount() {
        return viewCount;
    }

    /**
     * @param viewCount The view_count
     */
    @JsonProperty("view_count")
    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * @return The playlist
     */
    @JsonProperty("playlist")
    public String getPlaylist() {
        return playlist;
    }

    /**
     * @param playlist The playlist
     */
    @JsonProperty("playlist")
    public void setPlaylist(String playlist) {
        this.playlist = playlist;
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
    public String getPlaylistIndex() {
        return playlistIndex;
    }

    /**
     * @param playlistIndex The playlist_index
     */
    @JsonProperty("playlist_index")
    public void setPlaylistIndex(String playlistIndex) {
        this.playlistIndex = playlistIndex;
    }

    /**
     * @return The dislikeCount
     */
    @JsonProperty("dislike_count")
    public String getDislikeCount() {
        return dislikeCount;
    }

    /**
     * @param dislikeCount The dislike_count
     */
    @JsonProperty("dislike_count")
    public void setDislikeCount(String dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    /**
     * @return The averageRating
     */
    @JsonProperty("average_rating")
    public String getAverageRating() {
        return averageRating;
    }

    /**
     * @param averageRating The average_rating
     */
    @JsonProperty("average_rating")
    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
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
     * @return The stretchedRatio
     */
    @JsonProperty("stretched_ratio")
    public Object getStretchedRatio() {
        return stretchedRatio;
    }

    /**
     * @param stretchedRatio The stretched_ratio
     */
    @JsonProperty("stretched_ratio")
    public void setStretchedRatio(Object stretchedRatio) {
        this.stretchedRatio = stretchedRatio;
    }

    /**
     * @return The ageLimit
     */
    @JsonProperty("age_limit")
    public String getAgeLimit() {
        return ageLimit;
    }

    /**
     * @param ageLimit The age_limit
     */
    @JsonProperty("age_limit")
    public void setAgeLimit(String ageLimit) {
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
     * @return The playlistId
     */
    @JsonProperty("playlist_id")
    public String getPlaylistId() {
        return playlistId;
    }

    /**
     * @param playlistId The playlist_id
     */
    @JsonProperty("playlist_id")
    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
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
     * @return The playlistTitle
     */
    @JsonProperty("playlist_title")
    public String getPlaylistTitle() {
        return playlistTitle;
    }

    /**
     * @param playlistTitle The playlist_title
     */
    @JsonProperty("playlist_title")
    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
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
     * @return The vbr
     */
    @JsonProperty("vbr")
    public Object getVbr() {
        return vbr;
    }

    /**
     * @param vbr The vbr
     */
    @JsonProperty("vbr")
    public void setVbr(Object vbr) {
        this.vbr = vbr;
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
    public Object getResolution() {
        return resolution;
    }

    /**
     * @param resolution The resolution
     */
    @JsonProperty("resolution")
    public void setResolution(Object resolution) {
        this.resolution = resolution;
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
     * @return The nEntries
     */
    @JsonProperty("n_entries")
    public String getNEntries() {
        return nEntries;
    }

    /**
     * @param nEntries The n_entries
     */
    @JsonProperty("n_entries")
    public void setNEntries(String nEntries) {
        this.nEntries = nEntries;
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
