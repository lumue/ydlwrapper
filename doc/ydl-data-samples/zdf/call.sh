#!/bin/bash
URL="http://www.zdf.de/ZDFmediathek/hauptnavigation/startseite/#/beitrag/video/257404/heute-Xpress"
/usr/local/bin/youtube-dl --newline --write-info-json $URL  >> output.log
