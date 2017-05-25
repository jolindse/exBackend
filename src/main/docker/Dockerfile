FROM java:8
ADD exbackend.jar app.jar
ADD wait.sh wait.sh
RUN bash -c 'chmod +x /wait.sh'
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["/bin/bash","/wait.sh"]