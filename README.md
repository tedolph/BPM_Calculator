# BPM_Calculator :notes: :abacus:	
Reads data from a video that includes a flashing light and determines the BPM of that flashing

# THE GOAL :goal_net:	
The goal of this project was to have a video that includes a flashing light. Then the program calculates the beats-per-minute of the flashing. This allows the user to then search on a [song BPM database](https://getsongbpm.com/) and sync their video to the music. Technology filters are becoming easier for any person to use. On social media apps, people can easily edit videos to include complex filters and transitions that, just a few years ago, would seem impossible. A program that automatically syncs music to the video is useful to the everyday user since it is more efficient than searching through numerous songs to find one that looks correct.

# THE PARTS :books:	
### VIDEO

At the moment, to get the most accurate BPM result, the video should be a stable video that is primarily dark. 
### FFMPEG

The framework, FFMPEG, is used to retrieve the data of the video. The command

```
./ffprobe -v error -hide_banner -select_streams v:0 -show_frames -print_format json INSERT_YOUR_VID_HERE.mov > original.json
```

``` -v ``` ????

```-hide_banner ``` hide printing information related to te commandline

```-select_streams v:0``` Shows ony video streams

```print_format json``` outputs the information in JSON format




### JAVA
