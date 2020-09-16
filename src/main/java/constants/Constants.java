package constants;

public class Constants {

    public static class RunVariables {
        public static String server = Servers.jsonPlaceHolderUrl;
        public static String path = "";
    }

    public static class Servers {
        public static String swapiUrl = "https://swapi.dev/";
        public static String jsonPlaceHolderUrl = "https://jsonplaceholder.typicode.com/";
        public static String requestbinUrl = "https://9db8ced490b197bf2da1c66fc369cb1b.m.pipedream.net";
    }

    public static class Paths {
        public static String swapiPath = "api/";
    }

    public static class Actions {
        public static String swapiGetPeople = "people/";
        public static String jsonPlaceHolderGet = "comments/";
        public static String jsonPlaceHolderPut = "posts/1/";
        public static String jsonPlaceHolderDelete = "posts/1/";
        public static String jsonPlaceHolderPost = "posts/";
    }
}
