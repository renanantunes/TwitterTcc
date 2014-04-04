package tcc.twitter.main;

public class MainApplication {
	public static void main(String args[]) throws Exception{
	    // The factory instance is re-useable and thread safe.
	    Twitter twitter = TwitterFactory.getSingleton();
	    twitter.setOAuthConsumer("mpbw8VzN1xBO4Gefd0GSpg", "ehQnNojt7FUHLVQuGKmBlHDgo75z33ySKDEU8U");
	    
	    RequestToken requestToken = twitter.getOAuthRequestToken();
	    
	    OAuth2Token oAuth2 = new OAuth2Token("bearer", requestToken.getToken());
	    System.out.println(oAuth2.getAccessToken());
	    AccessToken accessToken = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(requestToken.getAuthorizationURL());
	      System.out.println(requestToken.getToken());
	      System.out.println(requestToken.getTokenSecret());
	      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
	      //System.out.println("AccessToken: " + twitter.getOAuth2Token());
	      String pin = br.readLine();
	      try{
	         if(pin.length() > 0){
	           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	         }else{
	           accessToken = twitter.getOAuthAccessToken();
	         }
	      } catch (TwitterException te) {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	    }
	    //persist to the accessToken for future reference.
	    storeAccessToken(twitter.verifyCredentials().getId() , accessToken);
	    Status status = twitter.updateStatus(args[0]);
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	    System.exit(0);
	  }
	  private static void storeAccessToken(long useId, AccessToken accessToken){
	    //store accessToken.getToken()
	    //store accessToken.getTokenSecret()
		  System.out.println(accessToken.getToken() + "\n" + accessToken.getTokenSecret());
	  }
}
