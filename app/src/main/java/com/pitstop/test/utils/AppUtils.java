package com.pitstop.test.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.pitstop.test.R;
import com.pitstop.test.model.Hours;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by david on 28/2/18.
 */

public class AppUtils {

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.dialog_progressbar);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

//    public static void setImage(Context context, String imageUrl, ImageView imageView) {
//
//        if (URLUtil.isValidUrl(imageUrl)) {
//
//            GlideApp.with(context)
//                    .load(imageUrl)
//                    .thumbnail(Glide.with(context).load(R.drawable.loading))
//                    .error(Glide.with(context).load(R.drawable.img_not_available2))
//                    .into(imageView);
//
//        }
//
//    }

    // for this code challenge I assume the user is in the same Time Zone as the listed locations
    public static int getCurrentState(Hours hours) {

        int actualState = R.string.closed_now;

        Calendar currentDay = Calendar.getInstance();
        int currentDayOfWeek = currentDay.get(Calendar.DAY_OF_WEEK);


        String dayRange = null;

        switch (currentDayOfWeek) {
            case 1:
                dayRange = hours.getSunday();
                break;
            case 2:
                dayRange = hours.getMonday();
                break;
            case 3:
                dayRange = hours.getTuesday();
                break;
            case 4:
                dayRange = hours.getWednesday();
                break;
            case 5:
                dayRange = hours.getThursday();
                break;
            case 6:
                dayRange = hours.getFriday();
                break;
            case 7:
                dayRange = hours.getSaturday();
                break;

            default:
                break;

        }

        if (dayRange != null && dayRange.contains("-")) {
            String[] times = dayRange.split("-");

            try {

                // Add minutes when not set
                String startTime = times[0];
                if (!startTime.contains(":")) {
                    startTime = startTime.replaceAll("AM", ":00AM").replaceAll("PM", ":00PM");
                }
                String endTime = times[1];
                if (!endTime.contains(":")) {
                    endTime = endTime.replaceAll("AM", ":00AM").replaceAll("PM", ":00PM");
                }

                // Convert 12h format to 24h format
                SimpleDateFormat date12Format = new SimpleDateFormat("hh:mma");
                SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");

                Date start = new SimpleDateFormat("HH:mm").parse(date24Format.format(date12Format.parse(startTime)));
                Calendar startCal = Calendar.getInstance();
                startCal.setTime(start);
                startCal.add(Calendar.DATE, 1);

                Date end = new SimpleDateFormat("HH:mm").parse(date24Format.format(date12Format.parse(endTime)));
                Calendar endCal = Calendar.getInstance();
                endCal.setTime(end);
                endCal.add(Calendar.DATE, 1);

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
                String currentTime = mdformat.format(calendar.getTime());
                Date aux = new SimpleDateFormat("HH:mm").parse(currentTime);
                Calendar currCal = Calendar.getInstance();
                currCal.setTime(aux);
                currCal.add(Calendar.DATE, 1);

                Date current = currCal.getTime();

                // Check if the current time is inside the range
                if (current.after(startCal.getTime()) && current.before(endCal.getTime())) {

                    actualState = R.string.open;

                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return actualState;

    }

}
