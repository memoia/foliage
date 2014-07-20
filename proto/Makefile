PYTHON := $(shell which python2.7)
ENV := $(CURDIR)/env
NOSE := $(ENV)/bin/nose2

.PHONY: all

all: | $(NOSE)
	$(NOSE) -v

$(ENV):
	virtualenv --python=$(PYTHON) $(ENV)

$(NOSE): | $(ENV)
	$(ENV)/bin/pip install nose2
