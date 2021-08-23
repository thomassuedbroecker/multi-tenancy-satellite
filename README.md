## Multi-Tenancy Sample App

... work in progress ...

### Deployment to OpenShift on IBM Cloud with Tekton

The following scripts deploy the modernized application on Red Hat [OpenShift on IBM Cloud](https://cloud.ibm.com/kubernetes/overview?platformType=openshift). However the same instructions should work for other OpenShift and OCP deployments, for example [CodeReady Containers](https://developers.redhat.com/products/codeready-containers/overview).

First create an [IBM Cloud Account](https://cloud.ibm.com/registration). Then create an OpenShift cluster, for example via the [IBM Cloud Dashboard](https://cloud.ibm.com/kubernetes/catalog/create?platformType=openshift). I've tested classic infrastructure, single zone, OpenShift 4.6.17, b3c.8x32 and 3 worker nodes.

Additionally you need to install Tekton. The easiest option is to use the '[OpenShift Pipelines](https://docs.openshift.com/container-platform/4.6/pipelines/installing-pipelines.html)' operator from the OperatorHub view in the OpenShift Console . Simply accept all defaults. No local installations are necessary.

```
$ git clone https://github.com/nheidloff/multi-tenancy-satellite.git && cd multi-tenancy-satellite
$ ROOT_FOLDER=$(pwd)
$ sh ${ROOT_FOLDER}/scripts-openshift-tekton/check-prerequisites.sh
$ oc login ...
$ sh ${ROOT_FOLDER}/scripts-openshift/deploy-postgres.sh
$ sh ${ROOT_FOLDER}/scripts-openshift-tekton/deploy-application.sh
$ sh ${ROOT_FOLDER}/scripts-openshift-tekton/show-urls.sh
```