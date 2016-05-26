FROM java:8
MAINTAINER stuartleyland@gmail.com
VOLUME /tmp
EXPOSE 8080

ENV USER_NAME packedit
ENV APP_HOME /home/$USER_NAME/app

RUN useradd -ms /bin/bash $USER_NAME
RUN mkdir -p $APP_HOME/data

ADD packed-it-1.0.1.jar $APP_HOME/packed-it.jar
RUN chown -R $USER_NAME $APP_HOME/

USER $USER_NAME
WORKDIR $APP_HOME
RUN bash -c 'touch packed-it.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","packed-it.jar"]
