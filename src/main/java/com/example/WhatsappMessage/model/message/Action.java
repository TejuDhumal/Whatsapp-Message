package com.example.WhatsappMessage.model.message;

import java.util.ArrayList;


    public class Action{
        public String button;
        public ArrayList<Button> buttons;
        public ArrayList<Section> sections;

        public String getButton() {
            return button;
        }

        public void setButton(String button) {
            this.button = button;
        }

        public ArrayList<Button> getButtons() {
            return buttons;
        }

        public void setButtons(ArrayList<Button> buttons) {
            this.buttons = buttons;
        }

        public ArrayList<Section> getSections() {
            return sections;
        }

        public void setSections(ArrayList<Section> sections) {
            this.sections = sections;
        }
    }


