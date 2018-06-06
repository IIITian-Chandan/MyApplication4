package com.example.opensource.myapplication.activities.home.Forms;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionsSpreadsheetWebService {

    @POST("1FAIpQLScbccPN9s5PVCDIx01ER_rJWRUUvchRgrRNnxh5xm2vNPXC0w/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(

            @Field("entry.1937884167") String mach,
            @Field("entry.876411538") String region,
            @Field("entry.972004368") String customerName,
            @Field("entry.1500517816") String DOI,
            @Field("entry.239980105") String company,
            @Field("entry.1308533030") String sod,
            @Field("entry.303814672") String cpn,
            @Field("entry.589693400") String cpno,
            @Field("entry.1909750246") String add,
            @Field("entry.1119752967") String staff_1,
            @Field("entry.1428285345") String staff_2,
            @Field("entry.1019364375") String staff_3,
            @Field("entry.1834907541") String vacuum,
            @Field("entry.1762183027") String convey_motor,
            @Field("entry.506610063") String reverse_Roller,
            @Field("entry.391862315") String cam1,
            @Field("entry.1078837331") String cam2,
            @Field("entry.343073897") String vibrator,
            @Field("entry.1492283437") String splitcon,
            @Field("entry.686545300") String rad1,
            @Field("entry.1771356690") String rad2,
            @Field("entry.1239579172") String rad3,
            @Field("entry.909477152") String rad4,
            @Field("entry.702578252") String rad5,
            @Field("entry.1481433317") String rad6,
//            @Field("entry.966719147") String starttime,
//            @Field("entry.1168568273") String endtime,
            @Field("entry.1760858036") String bcount,
            @Field("entry.1947541575") String ccount,
            @Field("entry.596740691") String dcount,
            @Field("entry.1525341686") String ecount,
            @Field("entry.1399971229") String endbin,
            @Field("enrty.1176519109") String grade,
            @Field("entry.431985641") String feedback,
            @Field("entry.146771172") String timeStared,
            @Field("entry.1555899881") String timeEnded
    );
}

    /*Call<Void> secondQuestionnaire(

            String fieldServiceMachine, String edt_field_region, String edt_field_cust, String edt_field_per, String edt_field_perNo, String edt_field_addr, String edt_field_staff, String edt_field_operator, String edt_field_optPhone);


}*/
