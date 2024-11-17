package com.example.proyecto_final.api;

import java.util.List;

public class ComicResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        private List<Comic> results;

        public List<Comic> getResults() {
            return results;
        }
    }

    public static class Comic {
        private String title;
        private Thumbnail thumbnail;
        private String description;
        private List<Date> dates;

        public String getTitle() {
            return title;
        }

        public Thumbnail getThumbnail() {
            return thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public List<Date> getDates() {
            return dates;
        }

        public static class Thumbnail {
            private String path;
            private String extension;

            public String getUrl() {
                return path + "." + extension;
            }
        }

        public static class Date {
            private String type;
            private String date;

            public String getType() {
                return type;
            }

            public String getDate() {
                return date;
            }
        }
    }
}

