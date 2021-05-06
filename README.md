# LanguageTranslator

This is a mobile application that is to help people who would want to learn the french language. 

http://g.recordit.co/0VQg5kpBqw.gif

# Getting Started:
- Make sure you setup https://github.com/margaretmwaura/WebScrapper project as it serves as the backend.
- Since it is a local setup , ensure you have ngrok for tunnelling
- Run ./ngrok http 192.168.10.10:80 -host-header=french.appp  in your terminal. Make sure you run it where your ngrok file is at. The french.appp is the name
of the local setup of your app, the webscrapper. One is free to change the name.

### What You will Learn
- Developing of Android Apps using Koltin
- Use of the room database
- Scheduling of tasks using the WorkManager
- Using the repository pattern
- Dependency injection using dagger

### Libraries used
- ViewModel. Manage UI related data in a lifecycle conscious way and act as a channel between use cases and ui
- ViewBinding. support library that allows binding of UI components in layouts to data sources,binds character details and search results to UI
- Retrofit. To access the Rest Api

### Work in progress
- Displaying of the words learnt with their transalations in english
- Functionality to allow a user to speak into microphone in order to match the audio played and their voices to see if they are learning pronounications

![Untitled design (1)](https://user-images.githubusercontent.com/34396651/116791958-751ccb80-aac6-11eb-9938-65d817f9e776.png)
