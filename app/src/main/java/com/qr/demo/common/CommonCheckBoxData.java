package com.qr.demo.common;

public class CommonCheckBoxData {
        String text;
        boolean isCheck;

        public CommonCheckBoxData(String text, boolean isCheck) {
            this.text = text;
            this.isCheck = isCheck;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }