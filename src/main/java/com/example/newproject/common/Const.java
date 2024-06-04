package com.example.newproject.common;

public class Const{
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;

    public static final int ON = 1;
    public static final int OFF = 0;

    public static final int ID_NULL = 3;
    public static final int PW_FAIL = 2;

    public static final int PIC_MAX = 5;

    public static final int SHOP_CHECK_NUM = 0;
    public static final int BUTCHER_CHECK_NUM = 1;

    public static final int NO_SHOW_PAGE = 10;

    public static final int RESERVATION_PAGE = 5;

    public static final String REGEXP_PATTERN_SPACE_CHAR = "^[\\s]*$";

    public static final String REGEXP_PATTERN_SPACE_CHAR_3 = "^[\\S]*$";

    public static final String REGEXP_PATTERN_SPACE_CHAR_TYPE_2 = "^([ ][\\s]*)$";

    public static final String REGEXP_USER_ID = "^[a-zA-Z0-9_-]+@([a-zA-Z0-9]{3,}\\.[a-z]{2,}|[a-zA-Z0-9]{3,}\\.[a-z]{2,}\\.[a-z]{2,})$";

    public static final String REGEXP_USER_TEL = "^01[0-1|6-9]{1}[\\d]{3,4}[\\d]{4}$";

    public static final String REGEXP_USER_GENDER = "^[남|여]{1}$";

    public static final String REGEXP_PIC = "^([\\S\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)";

    public static final String REGEXP_USER_BIRTH = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";


    public static final String REGEXP_DATE_TYPE5 =
            "^[\\d]{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) ([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";

}
