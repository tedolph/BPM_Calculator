import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.*;

public class calcBPM {

    public static void main(String[] args) throws JSONException, IOException {

        int[] number = null;

        String jsonString = Files.readString(Path.of(("/Users/honor2.0/Documents/pixelProject/src/thirdVid.json")), StandardCharsets.US_ASCII);

        JSONObject obj = new JSONObject(jsonString);
        JSONArray arr = obj.getJSONArray("frames");  //accessing the elements in the frames array from the JSON file
        number = new int[arr.length()];

        for (int i = 0; i < arr.length(); i++) {
            String pktSize = arr.getJSONObject(i).getString("pkt_size");
            number[i] = arr.getJSONObject(i).getInt("pkt_size"); //storing all values from "pkt_size" into int array
        }

        //User can change range to get different number of peaks
        int answer = calculateBeatPerMin(number,20, (float) arr.getJSONObject(1).getDouble("pkt_duration_time"));

        System.out.println("RESULT " + answer);

    }


    static int calculateBeatPerMin(int[] number, int range, float packetTime) {
        //System.out.println("packet time " + packetTime);

        int previousI = 0;
        // Check for special cases
        if (number == null) {
            return 0;
        }

        int result = 0, l, r;

        // Check main body
        for (int i = 0; i < number.length; i++) {
            boolean isPeak = true;
            // Check from left to right
            l = Math.max(0, i - range);
            r = Math.min(number.length - 1, i + range);
            for (int j = l; j <= r; j++) {
                // Skip if we are on current
                if (i == j) {
                    continue;
                }
                if (number[i] < number[j]) {
                    isPeak = false;
                    break;
                }
            }

            if (isPeak) {
                System.out.println("Peak at " + i + " = " + number[i]);
                if (previousI != 0) {
                    result = Math.round((i - previousI) * packetTime);
                    return result;
                }
                previousI = i;
                result++;
                i += range;
            }
        }

        return 0; //not enough peaks
    }
}

