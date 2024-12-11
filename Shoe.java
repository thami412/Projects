package GUIs;

    class Shoe {
        private final String description;
        private final String imageURL;

        public Shoe(String description, String imageURL) {
            this.description = description;
            this.imageURL = imageURL;
        }

        public String getDescription() {
            return description;
        }

        public String getImageURL() {
            return imageURL;
        }
    }
