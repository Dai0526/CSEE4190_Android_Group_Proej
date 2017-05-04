# CSEE4190_Android_Group_Proej

## Introduction 
Our app is a light, convenient, integrated app (designed for New Yorkers currently). It mainly contains three parts that are daily technology news, the quote of the day, and current weather information. Daily selected article of technology news meets most New Yorkers’ curiosity about cutting edge technology during a fast-paced NYC daily life. The purpose of the quote of the day is to inspire people to be successful. Besides, we also implemented weather information into our app to provide more daily functions since many android mobile devices does not have a built-in weather app. Our goal is to design an app that only takes a little time of our users but provides just enough crucial information and our purpose is to show that “one is enough for a day”. Note that our app is currently designed for New Yorkers only, but some future works will be discussed in the later part.

## App Description
Our app can be separated into three main parts besides the starting screen: the weather information from Yahoo API, the selected technology news from TechCrunch, and the quote of the day. We implemented a starting screen that includes our purpose “one is enough for a day” and a background picture of New York City. The starting screen is shown below.

### Figure 1. Welcome Screen
We implemented a left-right sliding function that allows the user to check the daily technology article, the quote of the day, or the weather information. By sliding left, the user can check the daily tech news, and by sliding right, the user can check the quote of the day. By further sliding, the user can check the weather information. The indication of how the sliding function works is shown below. The double-headed arrow means left-right sliding. 

### Figure 2. Sliding Function

By sliding the starting page to the right, our app will navigate to the daily technology article section. This part is the selected technology news from Techcrunch. TechCrunch is an online publisher of technology news. Their technology articles cover businesses ranging from startups to established firms. It is considered as the number one guide for all things tech. By using with newsapi.org’s free JSON API, 
https://newsapi.org/v1/articles?source=techcrunch&apiKey=642658eb4ebf432aaee64b1bc62913c1
we could get live headlines, images, and selected article metadata from TechCrunch. We created author, title, and description TextViews, and ImageView for the news to display the daily technology news on our user interface.

```au=(TextView)findViewById(R.id.au_text);
title=(TextView)findViewById(R.id.titleText);
desc=(TextView)findViewById(R.id.despText);
img=(ImageView)findViewById(R.id.newsImg);
Our user interface can be seen below.```

Figure 3. User Interface of Daily Technology News
By further sliding the page to the right, we will see the weather information. The user interface of the weather section is shown below. 

Figure 4. User Interface of Weather Information
We can see both the weather information and an icon that indicates the weather. We used yahoo API and the following command to get the weather forecast information. 
"Select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")"
The icons of the weather condition areobtained from the internet. By creating weather icon ImageView and several weather information Textview, we were able to display both the icon of the current weather and the weather information such as temperature, weather condition, date, daily high and daily low in our user interface. 
weatherIcon = (ImageView)findViewById(R.id.weather_icon);
temperature = (TextView) findViewById(R.id.temperatureText);
condition = (TextView) findViewById(R.id.conditionText);
location = (TextView) findViewById(R.id.locationText);
The blurred background is a picture of New York City’s sky. Since currently our app is designed for New Yorkers, so it will only show the weather of New York City area.


By further sliding the page to the right, or sliding the starting page to the left, we will see the quote of the day. We collected more than 27000 inspiring quotes from the internet into our MySQL database and converted all the information into a csv file as shown below.

Figure 5. Our csv File of Quotes
Then we imported the csv file into our app. We used the following method to select one quote for each day and then displayed both the quote and author name in the quote of the day part using TextView.

```HashMap<String,String> hm_author=new HashMap<String,String>();
HashMap<String,String> hm_quote=new HashMap<String,String>();
Calendar c = Calendar.getInstance();
c.set(Calendar.YEAR, 2017);
c.set(Calendar.MONTH, Calendar.MAY);
c.set(Calendar.DAY_OF_MONTH, 1);
Date temp_date = c.getTime();
DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
String temp_hash_date = outputFormatter.format(temp_date);
Log.d("hash",temp_hash_date);

for (int i = 0; i < (quoteList.size()/4); i=i+4) {
    String quoteID = quoteList.get(i);
    String category = quoteList.get(i+1);
    String author = quoteList.get(i+2);
    String quote = quoteList.get(i+3);

    temp_hash_date = outputFormatter.format(temp_date);
    hm_author.put(temp_hash_date,author);
    hm_quote.put(temp_hash_date,quote);

    c.setTime(temp_date);
    c.add(Calendar.DATE, 1);
    temp_date = c.getTime();
}```

Note that we chose a background picture of Columbia University’s Butler Library in this part to indicate our purpose of the inspiration and the user interface of the quote of the day part is shown below.

Figure 6. User Interface of the Quote of the Day

Future Work
Since currently our app is designed for New Yorkers so it has limited functions like the weather information and background pictures are all related to New York City only. In the future, we want to implement this app into a global app. We want to make the background pictures to automatically change depending on the user’s location. For the weather section, we can add more detailed information such as weather forecast and search by location function to meet more users’ requirements. We also want to implement some user preference settings to make the app more personalized. For the technology news part, we are thinking of adding more sources for the daily technology news instead of just getting news from one source. Also, by implementing certain selection algorithm and adding news filter options, we might able to display different articles to each different user depending on their interests.

Acknowledgement
We want to express our thanks to Professor Meikang Qiu for all the work he has done during this semester. All the class slides are helpful. Also, we would like to thank our teaching assistant Richard Dai who helped us to analyze homework problems. We have encountered various problems during this project, but all those thinking and debugging process not only gave us deeper understanding of mobile app development, but also taught us how to cooperate with each other





## yahoo api YQL
https://developer.yahoo.com/yql/console/#h=Select+*+from+weather.forecast+where+woeid+in+(select+woeid+from+geo.places(1)+where+text%3D%22newyork%2C+ny%22)

### api developer page
https://developer.yahoo.com/apps/1a0Wqr6k/
