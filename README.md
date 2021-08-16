# BPM_Calculator :notes: :abacus:	
Reads data from a video that includes a flashing light and determines the BPM of that flashing

# THE GOAL :goal_net:	
The goal of this project was to have a video that includes a flashing light. Then the program calculates the beats-per-minute of the flashing. This allows the user to then search on a [song BPM database](https://getsongbpm.com/) and sync their video to the music. Technology filters are becoming easier for any person to use. On social media apps, people can easily edit videos to include complex filters and transitions that, just a few years ago, would seem impossible. A program that automatically syncs music to the video is useful to the everyday user since it is more efficient than searching through numerous songs to find one that looks correct.

# THE PARTS :books:	
### VIDEO

At the moment, to get the most accurate BPM result, the video should be a stable video that is primarily dark. 
### FFMPEG

The framework, FFMPEG, is used to retrieve information on each frame of the given video. The command ffprobe is used to create an output JSON file used for later data extraction.

```
./ffprobe -v error -hide_banner -select_streams v:0 -show_frames -print_format json INSERT_YOUR_VID_HERE.mov > original.json
```

``` -v ``` ????

```-hide_banner ``` hide printing information related to te commandline

```-select_streams v:0``` Shows ony video streams

```print_format json``` outputs the information in JSON format




### JAVA

The Java program is used to calculate the BPM of the video. This can be done by using the data extracted by the ffprobe command. The Java program accesses the outputted JSON file from the ffprobe command. Then the program accesses the variable, ```"pkt_size`"``. This variable is what visualizes the flashing light because it creates a perfect graph that shows the peaks each time the light turns on and off. An int array ```number``` is made that includes all the values from ```pkt_size```. Then in the function, calculateBeatPerMin, the peaks are determined. With the peaks found the time between each peak is calculated. Finally, the time between each peak is multiplied by the ```pkt_duration_time``` and 60. 

```"pkt_size" ``` A variable in the JSON file that reads the size of each packet.

```number``` The int array that stores all the values from ```"pkt_size"```

```"pkt_duration_time"``` or ```packetTime``` A variable in the JSON file that reads the duration between each packet.

```range``` The number of how far, in frames, the estimated peaks should be. 
### SONG BPM DATABASE

This website is a [song BPM database](https://getsongbpm.com/). So, once the value is outputted in the JAVA program the user can search their result on this website. Then they can know what songs that will match the beat of their video. 

### RESULTS

In conclusion, the program can read certain types of videos to get a precises estimation for the BPM. Using these demo videos or making your own, you can see how the program works and produces promising results. 

The next version of this project would focus on getting the range of videos to be more available. At the moment, the video is most accurate when it is darker and not moving around too much. When this issue is fixed, it will make the program more accesable to a greater amount of people. Also another feature to be worked on would allow the user to crop their video to center only the flashing light. This will help reduce chance of error.

