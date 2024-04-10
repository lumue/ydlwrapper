FROM alpine:edge
MAINTAINER Lutz Mueller <mueller.lutz@gmail.com>

RUN apk upgrade --repository http://dl-3.alpinelinux.org/alpine/edge/testing/ --allow-untrusted && \
    apk add --update libstdc++ \
    curl \
    ca-certificates \
    bash \
    ffmpeg \
    rtmpdump \
    aria2 \
    openjdk21-jdk \
    python3 --update-cache --repository http://dl-3.alpinelinux.org/alpine/edge/testing/ --allow-untrusted && \
    curl -L https://github.com/yt-dlp/yt-dlp/releases/latest/download/yt-dlp -o /usr/bin/youtube-dl && \
    chmod a+rx /usr/bin/youtube-dl && \
    /usr/bin/youtube-dl -U && \
    ln -s /usr/bin/youtube-dl /usr/local/bin/youtube-dl && \
    ln -s `which ffmpeg` /usr/local/bin/ffmpeg && \
    ln -s `which ffprobe` /usr/local/bin/ffprobe && \
    mkdir /getdown && mkdir /downloads

ADD *.jar /app.jar

EXPOSE 8001
EXPOSE 9090

VOLUME ["/getdown"]
VOLUME ["/downloads"]

ENTRYPOINT ["java","-Xms20M","-Xmx100M","-Dgetdown.path.home=/getdown","-Dgetdown.path.media=/downloads", "-Xdebug", "-Dcom.sun.management.jmxremote.port=9090", "-Dcom.sun.management.jmxremote.rmi.port=9090","-Djava.rmi.server.hostname=10.108.3.112","-Dcom.sun.management.jmxremote","-Dcom.sun.management.jmxremote.authenticate=false","-Dcom.sun.management.jmxremote.ssl=false","-Dcom.sun.management.jmxremote.local.only=false" ,"-jar","/app.jar"]
