MVN_VER := 3.2.2
PROJECT := amznex7
JAVA_HOME := $(shell /usr/libexec/java_home -v 1.7 || echo /usr/lib/jvm/java)
JAVA_OPTS := "-Xms64m -Xmx64m -Xincgc"
ENV := $(CURDIR)/env
MVN := JAVA_HOME=$(JAVA_HOME) MAVEN_OPTS=$(JAVA_OPTS) $(ENV)/bin/mvn -Dmaven.repo.local=$(ENV)/maven


run: test
	cd $(PROJECT) && $(MVN) compile exec:java


test: $(ENV)/bin/mvn
	cd $(PROJECT) && $(MVN) clean cobertura:cobertura


$(ENV):
	mkdir -p $(ENV) $(ENV)/bin $(ENV)/opt


$(ENV)/bin/mvn: | $(ENV)
	curl -L ftp://ftp.osuosl.org/pub/apache/maven/maven-3/$(MVN_VER)/binaries/apache-maven-$(MVN_VER)-bin.tar.gz -o - | tar -C $(ENV)/opt -xvzf -
	ln -s $(ENV)/opt/apache-maven-$(MVN_VER)/bin/* $(ENV)/bin


clean:
	rm -rf $(CURDIR)/$(PROJECT)/target/


distclean: clean
	rm -rf $(ENV)


.PHONY: test run clean distclean
