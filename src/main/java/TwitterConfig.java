public class TwitterConfig implements Config{

    @Override
    public String clientId() {
        return "UMV83FX5oCMIKSPOVW5yxkWwO";
    }

    @Override
    public String clientSecret() {
        return "E1dvO1nrWhPJzGgbph94DHpbOBDDB37m4thiCCCvjIl28YXCoR";
    }

    @Override
    public String baseUrl() {
        return "https://api.twitter.com";
    }

    //we move some tokens and basic url to twitter config
}
