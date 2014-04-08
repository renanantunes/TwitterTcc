package tcc.twitter.main;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter 
{
	public static void main(String[] args) 
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("mpbw8VzN1xBO4Gefd0GSpg");
        cb.setOAuthConsumerSecret("ehQnNojt7FUHLVQuGKmBlHDgo75z33ySKDEU8U");
        cb.setOAuthAccessToken("343697194-AqJGDK5eJ3a1AawDcbSb4JDysLw3kXQSpTx9QU86");
        cb.setOAuthAccessTokenSecret("dbPG40VfbeDAZ6AW6E4sQLprqg7WWjLK7o7v8WS3uRexu");

        
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();
                
                // gets Username
                String username = status.getUser().getScreenName();
                System.out.println(username);
                String profileLocation = user.getLocation();
                System.out.println(profileLocation);
                long tweetId = status.getId(); 
                System.out.println(tweetId);
                String content = status.getText();
                System.out.println(content +"\n");

            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        FilterQuery fq = new FilterQuery();
    
        String keywords[] = {"dota", "IBM", "Microsoft", "Apple", "Iphone", "Ipad"};
        String language[] = {"en", "pt"};

        fq.track(keywords);
        fq.language(language);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);
	}
}
