package strongshine.base;

public class Const
{
    public static final String SESS_UID = "SESS_UID";
    public static final String SESS_USER_INFO = "SESS_USER_INFO";
    public static final String SESS_LOCALE = "SESS_LOCALE";
    public static final String SESS_CAPCHA_TOKEN = "SESS_CAPCHA_TOKEN";
    public static final String SESS_SIGNIN_COUNT = "SESS_SIGNIN_COUNT";
    public static final String SESS_TIMEOFFSET = "SESS_TIMEOFFSET";
    public static final String SESS_ADMIN_UID = "SESS_ADMIN_UID";
    public static final String SESS_ADMIN_INFO = "SESS_ADMIN_INFO";
    public static final int MAX_SIGNIN_COUNT = 3;
    public static final String C_COOKIE_PATH = "/";
    public static final String C_COOKIE_APPKEY = "app.key";
    public static final String C_COOKIE_VID = "vid";
    public static final String CONST_THIRDPARTY_QQ = "qq";
    public static final String CONST_THIRDPARTY_RENREN = "renren";
    public static final String CONST_THIRDPARTY_SINAWEIBO = "sinaweibo";
    public static final String CONST_THIRDPARTY_WECHAT = "wechat";
    public static final String CONST_TEMPLATE_RESORUCE_DIR = "Resource";
    public static final Integer CONST_INC_USER_POINT = Integer.valueOf(10);
    public static final Long CONST_CHATTING_SYSID = new Long(0L);
    public static final Integer CONST_CHATTING_KIND_TEXT = Integer.valueOf(1);
    public static final Integer CONST_CHATTING_KIND_AUDIO = Integer.valueOf(2);
    public static final Integer CONST_CHATTING_KIND_SCREENCAPTURE = Integer.valueOf(3);
    public static final Integer CONST_CHATTING_KIND_UNLOCK_TEMPLATE = Integer.valueOf(4);
    public static final Integer CONST_CHATTING_STATE_COMMON = Integer.valueOf(0);
    public static final Integer CONST_CHATTING_STATE_READ = Integer.valueOf(1);
    public static final Integer CONST_CHATTING_STATE_NOT_PLAY = Integer.valueOf(2);
    public static final Integer CONST_CHATTING_DEL_COMMON = Integer.valueOf(0);
    public static final Integer CONST_CHATTING_DEL_FOR_SENDER = Integer.valueOf(1);
    public static final Integer CONST_CHATTING_DEL_FOR_RECEIVER = Integer.valueOf(2);
    public static final String SYSMSG_CREATE_INVITATION = "SYSMSG_CREATE_INVITATION";
    public static final Integer IDX_SYSMSG_CREATE_INVITATION = Integer.valueOf(100);
    public static final String SYSMSG_RENEWAL_INVITATION = "SYSMSG_RENEWAL_INVITATION";
    public static final Integer IDX_SYSMSG_RENEWAL_INVITATION = Integer.valueOf(101);
    public static final String SYSMSG_CANCEL_INVITATION = "SYSMSG_CANCEL_INVITATION";
    public static final Integer IDX_SYSMSG_CANCEL_INVITATION = Integer.valueOf(102);
    public static final String SYSMSG_ACCEPT_INVITATION = "SYSMSG_ACCEPT_INVITATION";
    public static final Integer IDX_SYSMSG_ACCEPT_INVITATION = Integer.valueOf(103);
    public static final String SYSMSG_DECLINE_INVITATION = "SYSMSG_DECLINE_INVITATION";
    public static final Integer IDX_SYSMSG_DECLINE_INVITATION = Integer.valueOf(104);
    public static final String SYSMSG_COMPLETE_INVITATION = "SYSMSG_COMPLETE_INVITATION";
    public static final Integer IDX_SYSMSG_COMPLETE_INVITATION = Integer.valueOf(105);
    public static final String SYSMSG_CREATE_EVENT_INVITATION = "SYSMSG_CREATE_EVENT_INVITATION";
    public static final Integer IDX_SYSMSG_CREATE_EVENT_INVITATION = Integer.valueOf(106);
    public static final String SYSMSG_RENEWAL_EVENT_INVITATION = "SYSMSG_RENEWAL_EVENT_INVITATION";
    public static final Integer IDX_SYSMSG_RENEWAL_EVENT_INVITATION = Integer.valueOf(107);
    public static final String SYSMSG_CANCEL_EVENT_INVITATION = "SYSMSG_CANCEL_EVENT_INVITATION";
    public static final Integer IDX_SYSMSG_CANCEL_EVENT_INVITATION = Integer.valueOf(108);
    public static final String SYSMSG_ACCEPT_EVENT_INVITATION = "SYSMSG_ACCEPT_EVENT_INVITATION";
    public static final Integer IDX_SYSMSG_ACCEPT_EVENT_INVITATION = Integer.valueOf(109);
    public static final String SYSMSG_DECLINE_EVENT_INVITATION = "SYSMSG_DECLINE_EVENT_INVITATION";
    public static final Integer IDX_SYSMSG_DECLINE_EVENT_INVITATION = Integer.valueOf(110);
    public static final String SYSMSG_COMPLETE_EVENT_INVITATION = "SYSMSG_COMPLETE_EVENT_INVITATION";
    public static final Integer IDX_SYSMSG_COMPLETE_EVENT_INVITATION = Integer.valueOf(111);
    public static final String SYSMSG_CREATE_PRODUCT_OFFER = "SYSMSG_CREATE_PRODUCT_OFFER";
    public static final Integer IDX_SYSMSG_CREATE_PRODUCT_OFFER = Integer.valueOf(112);
    public static final String SYSMSG_RENEWAL_PRODUCT_OFFER = "SYSMSG_RENEWAL_PRODUCT_OFFER";
    public static final Integer IDX_SYSMSG_RENEWAL_PRODUCT_OFFER = Integer.valueOf(113);
    public static final String SYSMSG_CANCEL_PRODUCT_OFFER = "SYSMSG_CANCEL_PRODUCT_OFFER";
    public static final Integer IDX_SYSMSG_CANCEL_PRODUCT_OFFER = Integer.valueOf(114);
    public static final String SYSMSG_ACCEPT_PRODUCT_OFFER = "SYSMSG_ACCEPT_PRODUCT_OFFER";
    public static final Integer IDX_SYSMSG_ACCEPT_PRODUCT_OFFER = Integer.valueOf(115);
    public static final String SYSMSG_DECLINE_PRODUCT_OFFER = "SYSMSG_DECLINE_PRODUCT_OFFER";
    public static final Integer IDX_SYSMSG_DECLINE_PRODUCT_OFFER = Integer.valueOf(116);
    public static final String SYSMSG_COMPLETE_PRODUCT_OFFER = "SYSMSG_COMPLETE_PRODUCT_OFFER";
    public static final Integer IDX_SYSMSG_COMPLETE_PRODUCT_OFFER = Integer.valueOf(117);
    public static final String SYSMSG_CREATE_ACTIVITY_OFFER = "SYSMSG_CREATE_ACTIVITY_OFFER";
    public static final Integer IDX_SYSMSG_CREATE_ACTIVITY_OFFER = Integer.valueOf(118);
    public static final String SYSMSG_RENEWAL_ACTIVITY_OFFER = "SYSMSG_RENEWAL_ACTIVITY_OFFER";
    public static final Integer IDX_SYSMSG_RENEWAL_ACTIVITY_OFFER = Integer.valueOf(119);
    public static final String SYSMSG_CANCEL_ACTIVITY_OFFER = "SYSMSG_CANCEL_ACTIVITY_OFFER";
    public static final Integer IDX_SYSMSG_CANCEL_ACTIVITY_OFFER = Integer.valueOf(120);
    public static final String SYSMSG_ACCEPT_ACTIVITY_OFFER = "SYSMSG_ACCEPT_ACTIVITY_OFFER";
    public static final Integer IDX_SYSMSG_ACCEPT_ACTIVITY_OFFER = Integer.valueOf(121);
    public static final String SYSMSG_DECLINE_ACTIVITY_OFFER = "SYSMSG_DECLINE_ACTIVITY_OFFER";
    public static final Integer IDX_SYSMSG_DECLINE_ACTIVITY_OFFER = Integer.valueOf(122);
    public static final String SYSMSG_COMPLETE_ACTIVITY_OFFER = "SYSMSG_COMPLETE_ACTIVITY_OFFER";
    public static final Integer IDX_SYSMSG_COMPLETE_ACTIVITY_OFFER = Integer.valueOf(123);
    public static final String SYSMSG_COMPLETE_PRODUCT_OFFER_AFTER_PAY = "SYSMSG_COMPLETE_PRODUCT_OFFER_AFTER_PAY";
    public static final Integer IDX_SYSMSG_COMPLETE_PRODUCT_OFFER_AFTER_PAY = Integer.valueOf(124);
    public static final String SYSMSG_COMPLETE_ACTIVITY_OFFER_AFTER_PAY = "SYSMSG_COMPLETE_ACTIVITY_OFFER_AFTER_PAY";
    public static final Integer IDX_SYSMSG_COMPLETE_ACTIVITY_OFFER_AFTER_PAY = Integer.valueOf(125);
    public static final String SYSMSG_PAY_FOR_OFFER_EMPLOYER = "SYSMSG_PAY_FOR_OFFER_EMPLOYER";
    public static final String SYSMSG_PAY_FOR_OFFER_ARTIST = "SYSMSG_PAY_FOR_OFFER_ARTIST";
    public static final Integer IDX_SYSMSG_PAY_FOR_OFFER = Integer.valueOf(126);
    public static final String SYSMSG_PAY_FOR_EXTRA_SERVICE_EMPLOYER = "SYSMSG_PAY_FOR_EXTRA_SERVICE_EMPLOYER";
    public static final String SYSMSG_PAY_FOR_EXTRA_SERVICE_ARTIST = "SYSMSG_PAY_FOR_EXTRA_SERVICE_ARTIST";
    public static final Integer IDX_SYSMSG_PAY_FOR_EXTRA_SERVICE = Integer.valueOf(127);
    public static final String SYSMSG_CREATE_EXTRA_SERVICE_EMPLOYER = "SYSMSG_CREATE_EXTRA_SERVICE_EMPLOYER";
    public static final String SYSMSG_CREATE_EXTRA_SERVICE_ARTIST = "SYSMSG_CREATE_EXTRA_SERVICE_ARTIST";
    public static final Integer IDX_SYSMSG_CREATE_EXTRA_SERVICE = Integer.valueOf(128);
    public static final String SYSMSG_VERIFY_ARTIST_SHARE = "SYSMSG_VERIFY_ARTIST_SHARE";
    public static final Integer IDX_SYSMSG_VERIFY_ARTIST_SHARE = Integer.valueOf(129);
    public static final String SYSMSG_OFFER_EXTRA_PAYMENT_TIMEOUT = "SYSMSG_OFFER_EXTRA_PAYMENT_TIMEOUT";
    public static final Integer IDX_SYSMSG_OFFER_EXTRA_PAYMENT_TIMEOUT = Integer.valueOf(130);
    public static final String SYSMSG_COMPLETE_HTML5_SHARE = "SYSMSG_COMPLETE_HTML5_SHARE";
    public static final Integer IDX_SYSMSG_COMPLETE_HTML5_SHARE = Integer.valueOf(12);
    public static final String SYSMSG_USE_COOPON = "SYSMSG_USE_COOPON";
    public static final Integer IDX_SYSMSG_USE_COOPON = Integer.valueOf(13);
    public static final String SYSMSG_NEED_TO_VERIFY = "SYSMSG_NEED_TO_VERIFY";
    public static final Integer IDX_SYSMSG_NEED_TO_VERIFY = Integer.valueOf(14);
    public static final String SYSMSG_CONGRATULATE_SELF_EMPLOYER = "SYSMSG_CONGRATULATE_SELF_EMPLOYER";
    public static final Integer IDX_SYSMSG_CONGRATULATE_SELF_EMPLOYER = Integer.valueOf(15);
    public static final String SYSMSG_CONGRATULATE_BUSINESS_EMPLOYER = "SYSMSG_CONGRATULATE_BUSINESS_EMPLOYER";
    public static final Integer IDX_SYSMSG_CONGRATULATE_BUSINESS_EMPLOYER = Integer.valueOf(16);
    public static final String SYSMSG_CONGRATULATE_ARTIST = "SYSMSG_CONGRATULATE_ARTIST";
    public static final Integer IDX_SYSMSG_CONGRATULATE_ARTIST = Integer.valueOf(17);
    public static final String SYSMSG_CONTRACT_ALMOST_TIME_LIMITED = "SYSMSG_CONTRACT_ALMOST_TIME_LIMITED";
    public static final Integer IDX_SYSMSG_CONTRACT_ALMOST_TIME_LIMITED = Integer.valueOf(18);
    public static final String SYSMSG_CONTRACT_TIME_EXPIRED = "SYSMSG_CONTRACT_TIME_EXPIRED";
    public static final Integer IDX_SYSMSG_CONTRACT_TIME_EXPIRED = Integer.valueOf(19);
    public static final String SYSMSG_PASS_TEMPLATE = "SYSMSG_PASS_TEMPLATE";
    public static final Integer IDX_SYSMSG_PASS_TEMPLATE = Integer.valueOf(20);
    public static final String SYSMSG_H5SHARE_TEMPLATE = "SYSMSG_H5SHARE_TEMPLATE";
    public static final Integer IDX_SYSMSG_H5SHARE_TEMPLATE = Integer.valueOf(21);
    public static final String SYSMSG_FAVOURITE_TEMPLATE = "SYSMSG_FAVOURITE_TEMPLATE";
    public static final Integer IDX_SYSMSG_FAVOURITE_TEMPLATE = Integer.valueOf(22);
    public static final String SYSMSG_FOLLOW_TEMPLATE = "SYSMSG_FOLLOW_TEMPLATE";
    public static final Integer IDX_SYSMSG_FOLLOW_TEMPLATE = Integer.valueOf(23);
    public static final String SYSMSG_WRITE_COMMENT = "SYSMSG_WRITE_COMMENT";
    public static final Integer IDX_SYSMSG_WRITE_COMMENT = Integer.valueOf(24);
    public static final String SYSMSG_SET_VIP = "SYSMSG_SET_VIP";
    public static final Integer IDX_SYSMSG_SET_VIP = Integer.valueOf(25);
    public static final String SYSMSG_DECLINE_ARTIST_VERIFY = "SYSMSG_DECLINE_ARTIST_VERIFY";
    public static final Integer IDX_SYSMSG_DECLINE_ARTIST_VERIFY = Integer.valueOf(26);
    public static final String SYSMSG_DECLINE_SELF_VERIFY = "SYSMSG_DECLINE_SELF_VERIFY";
    public static final Integer IDX_SYSMSG_DECLINE_SELF_VERIFY = Integer.valueOf(27);
    public static final String SYSMSG_DECLINE_BUSINESS_VERIFY = "SYSMSG_DECLINE_BUSINESS_VERIFY";
    public static final Integer IDX_SYSMSG_DECLINE_BUSINESS_VERIFY = Integer.valueOf(28);
    public static final String SYSMSG_DECLINE_FENSI_REQUEST = "SYSMSG_DECLINE_FENSI_REQUEST";
    public static final Integer IDX_SYSMSG_DECLINE_FENSI_REQUEST = Integer.valueOf(29);
    public static final String SYSMSG_APPLY_FENSI_REQUEST = "SYSMSG_APPLY_FENSI_REQUEST";
    public static final Integer IDX_SYSMSG_APPLY_FENSI_REQUEST = Integer.valueOf(30);
    public static final String SYSMSG_UNFOLLOW_TEMPLATE = "SYSMSG_UNFOLLOW_TEMPLATE";
    public static final Integer IDX_SYSMSG_UNFOLLOW_TEMPLATE = Integer.valueOf(31);
    public static final String SYSMSG_INCPOINT_ON_FIRST_LOGIN = "SYSMSG_INCPOINT_ON_FIRST_LOGIN";
    public static final Integer IDX_SYSMSG_INCPOINT_ON_FIRST_LOGIN = Integer.valueOf(32);
    public static final String SYSMSG_PSM_BUY_PRODUCT = "SYSMSG_PSM_BUY_PRODUCT";
    public static final Integer IDX_SYSMSG_PSM_BUY_PRODUCT = Integer.valueOf(33);
    public static final String SYSMSG_PSM_BUY_PRODUCT_TO_ALL = "SYSMSG_PSM_BUY_PRODUCT_TO_ALL";
    public static final Integer IDX_SYSMSG_PSM_BUY_PRODUCT_TO_ALL = Integer.valueOf(34);
    public static final String SYSMSG_CONGRATULATE_DAU = "SYSMSG_CONGRATULATE_DAU";
    public static final Integer IDX_SYSMSG_CONGRATULATE_DAU = Integer.valueOf(35);
    public static final String SYSMSG_ZHUBO_SUBSCRIBE = "SYSMSG_SUBSCRIBE_ZHUBO";
    public static final Integer IDX_SYSMSG_ZHUBO_SUBSCRIBE = Integer.valueOf(36);
    public static final String SYSMSG_ZHUBO_ADD_ACTIVITY = "SYSMSG_ZHUBO_ADD_ACTIVITY";
    public static final Integer IDX_SYSMSG_ZHUBO_ADD_ACTIVITY = Integer.valueOf(37);
    public static final String SYSMSG_ZHUBO_BEFORE_START_ACTIVITY = "SYSMSG_ZHUBO_BEFORE_START_ACTIVITY";
    public static final Integer IDX_SYSMSG_ZHUBO_BEFORE_START_ACTIVITY = Integer.valueOf(39);
    public static final String SYSMSG_ZHUBO_CANCEL_ACTIVITY = "SYSMSG_ZHUBO_CANCEL_ACTIVITY";
    public static final Integer IDX_SYSMSG_ZHUBO_CANCEL_ACTIVITY = Integer.valueOf(40);
    public static final Integer IDX_SYSMSG_FORCE_LOGOUT = Integer.valueOf(900);
    public static final String EMAIL_VERIFY_TITLE = "EMAIL_VERIFY_TITLE";
    public static final String EMAIL_VERIFY_BODY = "EMAIL_VERIFY_BODY";
    public static final String FRONT_EMAIL_VERIFY_BODY = "FRONT_EMAIL_VERIFY_BODY";
    public static final String MSG_USER_SIGNUP = "MSG_USER_SIGNUP";
    public static final String MSG_USER_THIRDPARTY_SIGNUP = "MSG_USER_THIRDPARTY_SIGNUP";
    public static final String MSG_USER_REQUEST_VERIFY = "MSG_USER_REQUEST_VERIFY";
    public static final String MSG_USER_VERIFIED_JOGACCOUNT = "MSG_USER_VERIFIED_JOGACCOUNT";
    public static final String MSG_USER_SUBMITTED_FEEDBACK = "MSG_USER_SUBMITTED_FEEDBACK";
    public static final String MSG_ANONYMOUS_SUBMITTED_FEEDBACK = "MSG_ANONYMOUS_SUBMITTED_FEEDBACK";
    public static final String MSG_USER_UPLOAD_TEMPLATE = "MSG_USER_UPLOAD_TEMPLATE";
    public static final String PRODUCT_TITLE_BY_CONTRACT = "PRODUCT_TITLE_BY_CONTRACT";
    public static final Integer BANNER_IMG_WIDTH = Integer.valueOf(1300);
    public static final Integer BANNER_IMG_HEIGHT = Integer.valueOf(463);
    public static final Integer PRODUCT_IMG_WIDTH = Integer.valueOf(768);
    public static final Integer PRODUCT_IMG_HEIGHT = Integer.valueOf(450);
    public static final Integer ABOUT_IMG_WIDTH = Integer.valueOf(768);
    public static final Integer ABOUT_IMG_HEIGHT = Integer.valueOf(450);
}
