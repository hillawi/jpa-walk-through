FROM jboss/wildfly
ENV WILDFLY_HOME /opt/jboss/wildfly/standalone
ADD target/jpa.war ${WILDFLY_HOME}/deployments/
RUN rm ${WILDFLY_HOME}/configuration/standalone.xml
ADD standalone.xml ${WILDFLY_HOME}/configuration/
RUN /opt/jboss/wildfly/bin/add-user.sh hill kassala --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
