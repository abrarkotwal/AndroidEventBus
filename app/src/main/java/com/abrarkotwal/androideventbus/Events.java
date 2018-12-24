package com.abrarkotwal.androideventbus;

public class Events {

    public static class MessageTransfer {
        private String message;

        public MessageTransfer(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    /*// Event used to send message from activity to fragment.
    public static class ActivityFragmentMessage {
        private String message;

        public ActivityFragmentMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    // Event used to send message from activity to activity.
    public static class ActivityActivityMessage {
        private String message;

        public ActivityActivityMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }*/
}
