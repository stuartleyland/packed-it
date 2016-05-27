FROM java:8
MAINTAINER stuartleyland@gmail.com
VOLUME /tmp
EXPOSE 8080

ENV USER_NAME packedit
ENV APP_HOME /home/$USER_NAME/app
ENV APP_FILENAME packed-it.jar

RUN useradd -ms /bin/bash $USER_NAME
RUN mkdir -p $APP_HOME/data

ADD $APP_FILENAME $APP_HOME/$APP_FILENAME
RUN chown -R $USER_NAME $APP_HOME/

USER $USER_NAME
WORKDIR $APP_HOME
RUN bash -c 'touch $APP_FILENAME'

# Can't use $APP_FILENAME here because ENTRYPOINT does not do ENV replacement
# See: http://stackoverflow.com/a/28854410/336752
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","packed-it.jar"]
