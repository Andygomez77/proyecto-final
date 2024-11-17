package com.example.proyecto_final.api;

import java.util.List;

public class MarvelResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        private List<Character> results;

        public List<Character> getResults() {
            return results;
        }
    }

    public static class Character {
        private String name;
        private Thumbnail thumbnail;
        private String description;
        private ComicList comics;

        public String getName() {
            return name;
        }

        public Thumbnail getThumbnail() {
            return thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public ComicList getComics() {
            return comics;
        }

        public static class ComicList {
            private List<ComicSummary> items;

            public List<ComicSummary> getItems() {
                return items;
            }
        }

        public static class ComicSummary {
            private String name;

            public String getName() {
                return name;
            }
        }

        public static class Thumbnail {
            private String path;
            private String extension;

            public String getUrl() {
                return path + "." + extension;
            }
        }
    }

}