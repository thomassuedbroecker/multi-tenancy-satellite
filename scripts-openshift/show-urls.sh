#!/bin/bash

SCRIPT_FOLDER="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
PROJECT_FOLDER="$(cd $SCRIPT_FOLDER; cd ..; pwd )"

exec 3>&1

function _out() {
  echo "$(date +'%F %H:%M:%S') $@"
}

function setup() {
  PROJECT_NAME=$1
  echo Project: $PROJECT_NAME

  _out ------------------------------------------------------------------------------------
  
  _out Postgres
  nodeport=$(oc get svc postgres -n postgres --ignore-not-found --output 'jsonpath={.spec.ports[*].port}')
  if [ -z "$nodeport" ]; then
    _out Postgres is not available. Run the command: \"sh scripts-openshift/deploy-postgres.sh\"
  else 
    _out Postgres - internal URL: postgres.postgres:5432
  fi
  _out ------------------------------------------------------------------------------------

  _out service-catalog-quarkus-reactive  
  nodeport=$(oc get svc service-catalog-quarkus-reactive -n $PROJECT_NAME --ignore-not-found --output 'jsonpath={.spec.ports[*].port}')
  if [ -z "$nodeport" ]; then
    _out service-catalog-quarkus-reactive is not available. Run the command: \"sh scripts-openshift/deploy-service-catalog-quarkus-reactive.sh\"
  else 
    ROUTE=$(oc get route service-catalog-quarkus-reactive -n $PROJECT_NAME --template='{{ .spec.host }}')
    _out \"curl http://${ROUTE}/CustomerOrderServicesWeb/jaxrs/Category\"
    _out \"curl \'http://${ROUTE}/CustomerOrderServicesWeb/jaxrs/Product/?categoryId=2\'\"
    CREATE_NEW="http://${ROUTE}/CustomerOrderServicesWeb/jaxrs/Product/1 -H 'accept: application/json' -H 'Content-Type: application/json' -d '{\"id\":1, \"price\":50}'"
    _out \"curl -X PUT ${CREATE_NEW}\"
  fi
  _out ------------------------------------------------------------------------------------

  _out frontend-single-spa
  nodeport=$(oc get svc storefront-mf-shell -n $PROJECT_NAME --ignore-not-found --output 'jsonpath={.spec.ports[*].port}')
  if [ -z "$nodeport" ]; then
    _out frontend-single-spa is not available. Run the command: \"sh scripts-openshift/deploy-storefront-mf-shell.sh\"
  else 
    ROUTE=$(oc get route storefront-mf-shell -n $PROJECT_NAME --template='{{ .spec.host }}')
  
    _out "Open the web app:"
    _out "http://${ROUTE}"
  fi
}

if [ -z "$1" ]
then
  setup "app-mod-dev"
else
  setup $1 
fi
