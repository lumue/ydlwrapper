#!/bin/bash
URL="http://www.ardmediathek.de/tv/Tagesschau/tagesschau-12-00-Uhr/Das-Erste/Video?bcastId=4326&documentId=34021386"
/usr/local/bin/youtube-dl --newline --write-info-json $URL  >> output.log
