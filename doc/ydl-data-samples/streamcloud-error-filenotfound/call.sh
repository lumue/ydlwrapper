#!/bin/bash
URL="http://streamcloud.eu/awj41hqtpklv/itg-simpsons-s25e11.mkv.html"
/usr/local/bin/youtube-dl --newline --write-info-json $URL  >> output.log
