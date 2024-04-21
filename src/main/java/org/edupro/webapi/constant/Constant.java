package org.edupro.webapi.constant;

public class Constant {
    public static final String TRANSACTION_ID = "TRX-ID";
    public static final String SUCCESS_STATUS = "Success";
    public static final String FAILED_STATUS = "Failed";
    public static final String BAD_REQUEST_STATUS = "Bad Request";

    public static final String GROUP_AGAMA = "AGAMA";
    public static final String GROUP_WN = "WARGA_NEGARA";
    public static final String GROUP_LEVEL_KELAS = "LEVEL_KELAS";

    public static class ResourceType {
        public static final String TYPE = "RESOURCE_TYPE";
        public static final String QUIZ = "QUIZ";
        public static final String QUESTION = "QUESTION";
        public static final String MATERIAL = "MATERIAL";
    }

    public static class AttachmentType {
        public static final String TYPE = "ATTACHMENT_TYPE";
        public static final String DRIVE = "DRIVE";
        public static final String YOUTUBE = "YOUTUBE";
        public static final String UPLOAD_FILE = "UPLOAD_FILE";
        public static final String LINK = "LINK";
    }
}
