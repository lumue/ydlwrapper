#!/bin/bash
URL="https://www.youtube.com/watch?list=PLiZxWe0ejyv9aspZkVle0o8TRzEcoqT_n"
/usr/local/bin/youtube-dl --newline --get-format $URL   >> getformat.out
/usr/local/bin/youtube-dl --newline --get-filename $URL  >>  getfilename.out
/usr/local/bin/youtube-dl --newline --write-info-json $URL  >> output.log
