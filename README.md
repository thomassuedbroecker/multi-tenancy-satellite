## Multi-Tenancy Sample App

... work in progress ...

Objectives for week 41

| Objectives |  Status |  Priority |  Notes | 
| --- | --- | --- | --- | --- |
| 1 | Running simple ecommerce application including Quarkus on Code Engine |  open | high |   |
| 1.1 | - AppID setup |  done | high |  |
| 1.2 | - AppID integation to frontend |  done | high |  |
| 1.3 | - AppID integration to Backend |  open | high |  |
| 1.4 | - Backend database postgres integration |  open | high |  |
| 1.5 | - Deploy to Code Engine |  open | high |  |
| 2 | Automation of the deployment | open | high |  |
| 2.1 | - Create containers and save them in a public container registry | open | high |  |
| 2.2 | - Create a bash automation for the creation and configuration of AppID | open | high |  |
| 2.3 | - Create a bash automation for the creation and configuration of postgres | open | high |  |
| 2.4 | - Create a bash automation for deployment to Code Engine | open | high |  |
| 2.5 | - Setup tekton using the IBM Cloud toolchain | open | high |  |
| 2.6 | - Integrate exiting bash automationto tektion | open | high |  |
| 2.7 | - Add an admin UI for onboarding of new tenant roberts application | open | low |  |
| 3 | Documenation of the setup | open | high | We should use mkdocs |  
| 3.1 | - Manual setup | open | high |  |  
| 3.2 | - Automation setup | open | high |  |
| 3.3 | - Workshop  | open | low |  |


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
