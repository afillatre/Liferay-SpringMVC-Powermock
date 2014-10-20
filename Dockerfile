FROM ubuntu:trusty

MAINTAINER Alexandre FILLATRE, afillatre@ippon.fr

RUN apt-get update
RUN apt-get upgrade -y
RUN apt-get install -y software-properties-common
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN echo oracle-java6-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java6-installer maven curl

# Download Liferay 6.2
WORKDIR /data
RUN wget -O liferay.zip http://softlayer-ams.dl.sourceforge.net/project/lportal/Liferay%20Portal/6.2.1%20GA2/liferay-portal-maven-6.2-ce-ga2-20140326112342532.zip
RUN unzip liferay.zip

# Install maven dependencies
WORKDIR /data/liferay-portal-maven-6.2-ce-ga2
RUN ant install

# Build a sample portlet
WORKDIR /data
RUN mvn archetype:generate -DgroupId=fr.ippon.liferay.test -DartifactId=portlet-with-tests -DarchetypeGroupId=com.liferay.maven.archetypes -DarchetypeArtifactId=liferay-portlet-archetype -DarchetypeVersion=6.2.1 -DinteractiveMode=false

# Configure maven
ADD data/pom.xml /data/portlet-with-tests/pom.xml
ADD data/src /data/portlet-with-tests/src
ADD data/dependencies /data/dependencies

WORKDIR /data/portlet-with-tests
RUN mvn install:install-file -Dfile=/data/dependencies/xdoclet-1.2.1.jar -DgroupId=xdoclet -DartifactId=xdoclet -Dversion=1.2.1 -Dpackaging=jar
RUN mvn install:install-file -Dfile=/data/dependencies/xdoclet-web-module-1.2.1.jar -DgroupId=xdoclet -DartifactId=xdoclet-web-module -Dversion=1.2.1 -Dpackaging=jar
RUN mvn install:install-file -Dfile=/data/dependencies/xpp3_min-1.1.3.4.I.jar -DgroupId=xpp3 -DartifactId=xpp3_min -Dversion=1.1.3.4.I -Dpackaging=jar
RUN mvn install:install-file -Dfile=/data/dependencies/jabsorb-1.3.1.jar -DgroupId=org.jabsorb -DartifactId=jabsorb -Dversion=1.3.1 -Dpackaging=jar
RUN mvn install:install-file -Dfile=/data/dependencies/groovy-all-1.7.5.jar -DgroupId=groovy -DartifactId=groovy-all -Dversion=1.7.5 -Dpackaging=jar
RUN mvn install:install-file -Dfile=/data/dependencies/daisydiff-1.2.jar -DgroupId=org.outerj -DartifactId=daisyfdiff -Dversion=1.2 -Dpackaging=jar
RUN mvn install:install-file -Dfile=/data/dependencies/jai_codec-1.1.3.jar -DgroupId=com.sun.media -DartifactId=jai_codec -Dversion=1.1.3 -Dpackaging=jar
RUN mvn install:install-file -Dfile=/data/dependencies/jai_core-1.1.3.jar -DgroupId=javax.media -DartifactId=jai_core -Dversion=1.1.3 -Dpackaging=jar
RUN mvn install:install-file -Dfile=/data/dependencies/springmvc-portlet-test-1.0.jar -DgroupId=fr.ippon.springmvc.test -DartifactId=springmvc-portlet-test -Dversion=1.0 -Dpackaging=jar

# Test if all is good
RUN mvn test 
