#!/bin/bash
URL="https://www.youtube.com/watch?v=BiG6_1LS_AI"
/usr/local/bin/youtube-dl --newline --get-format $URL   > getformat.out
/usr/local/bin/youtube-dl --newline --get-filename $URL  >  getfilename.out
/usr/local/bin/youtube-dl --newline --dump-single-json $URL  > info.json
/usr/local/bin/youtube-dl --newline --write-info-json $URL  > output.log
