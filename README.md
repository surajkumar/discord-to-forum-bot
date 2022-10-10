# Discord to Forum Bot

<img src="https://i.imgur.com/utLkeXR.png" width="300" height="auto" alt="logo">

## Introduction
The Discord to Forum Bot (DTFB) is an application that translates Discord messages and displays them in a website in the style of a forum. This 1-2-1 mapping allows users to post in sections via the DTFB forum and thus those messages will be posted by the bot into the corresponding Discord channel.

The motivation behind such an application came from a discussion in the "Together Java" Discord server. Information is now commonly posted on closed platforms and the internet has little to no expose to the content shared. The Together Java server is commonly used by Java developers to share resources, ask questions and have tech discussions. A number of questions and material shared here has no internet presence and with the trend towards applications like Discord, information is becoming less available to everybody else on the internet. In addition to this, information posted on Discord gets lost once enough messages have piled up underneath it. By having an application like the DTFB, search engines would be able to index web pages and make access to information and resources more accessible. 

## The Tech Stack
The following technologies will be present in this application:

1. [Java Discord API](https://github.com/DV8FromTheWorld/JDA)
2. [Spring](https://spring.io/)
3. [Elasticsearch](https://www.elastic.co/)
4. [MariaDB](https://mariadb.org/)
5. Java 18
6. Gradle 7.4

## Contributing
Contributions are welcome whether it is for small bug fixes or new pieces of major functionality. To contribute changes, you should first fork the upstream repository to your own GitHub account. You can then add a new remote for `upstream` and rebase any changes you make and keep up-to-date with the upstream.

`git remote add upstream https://github.com/surajkumar/discord-to-forum-bot.git`